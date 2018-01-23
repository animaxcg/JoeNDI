package com.joe.JoeNDI;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.protocol7.remotemqsc.*;
import com.ibm.mq.*;

public class ConnectionBalancer {
	private static HashMap<String,String> connFactoryMap;
	private static ArrayList<String> connInfoList;
	public static void main(String [] args)
	{
		System.out.println("starting");
		setChannels();
		while (true){
			checkConnections("JOENDI.CHL");
			try {
				System.out.println("Sleeping");
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static void setChannels()
	{

		connFactoryMap=new HashMap<String,String>();
		connInfoList=new ArrayList<String>();
		try 
		{
			BufferedReader bfConnFactory = new BufferedReader(new FileReader("/Users/mylopage/Documents/workspace/MQJoeNDI/config/channel.ini"));
			String line;
			while ((line = bfConnFactory.readLine()) != null) 
			{
				connFactoryMap.put(line.split(",")[0],line.split(",")[1]);
		    }
			line=null;
			bfConnFactory.close();
			BufferedReader brConnFactory = new BufferedReader(new FileReader("/Users/mylopage/Documents/workspace/MQJoeNDI/config/qmgr.ini"));
			while ((line = brConnFactory.readLine()) != null) 
			{
				connInfoList.add(line);
		    }
			brConnFactory.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Integer>  countConnection(String connFactory)
	{
		ArrayList<Integer> numConnList = new ArrayList<Integer>();
		//loop through each queue manager in cluster
		
		for (int i=0;i<connInfoList.size();i++)
		{
			String qmgrname=connInfoList.get(i).split(",")[0];
			String hostname=connInfoList.get(i).split(",")[1];
			int port = Integer.parseInt(connInfoList.get(i).split(",")[2]);
			MQEnvironment.hostname = hostname;
			//Change to Admin config channel
		    MQEnvironment.channel = "MFTVIEWER";
		    MQEnvironment.port = port;
		    MQQueueManager qmgr = null;
			try {
				qmgr = new MQQueueManager(qmgrname);
			} catch (MQException e) {
				//continues to next iteration if Qmgr is unreachable
				if (2538==e.reasonCode || 2059==e.reasonCode){
					continue;
				}
				else
				{
					e.printStackTrace();
				}
			}

			MqscRunner mqsc = new MqscRunner();
			String mqscReturnStr="";
			try {
				mqscReturnStr=mqsc.runCommand(qmgr,"DIS CONN(*) ALL WHERE (CHANNEL EQ '"+ connFactory + "')");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			numConnList.add(StringUtils.countMatches(mqscReturnStr, "AMQ8276"));   
		}
		return numConnList;
	}
	
	private static void checkConnections(String connFactory)
	{
		setChannels();
		ArrayList<Integer> numConnList = countConnection(connFactory);
		int totalConn = 0;
		for (int numConn : numConnList)
		{
			totalConn+=numConn;
		}	
		int correctNumConn = totalConn/numConnList.size();
		int allowedErr=totalConn%numConnList.size();
		for (int i = 0;i<numConnList.size();i++)
		{
			System.out.println(numConnList.get(i)+" : " + correctNumConn + " : " + allowedErr);
			if (numConnList.get(i)-(correctNumConn)>0 && numConnList.get(i)>0)
			{
				closeConn(i, numConnList.get(i)-(correctNumConn), connFactory);
			}
		}
	}

	private static void closeConn(int index, int numConn, String connFactory)
	{
		ArrayList<Integer> numConnList = new ArrayList<Integer>();
		//loop through each queue manager in cluster
			String qmgrname=connInfoList.get(index).split(",")[0];
			String hostname=connInfoList.get(index).split(",")[1];
			int port = Integer.parseInt(connInfoList.get(index).split(",")[2]);
			MQEnvironment.hostname = hostname;
			//Change to Admin config channel
		    MQEnvironment.channel = "MFTVIEWER";
		    MQEnvironment.port = port;
		    MQQueueManager qmgr = null;
	

			String mqscReturnStr="";
			try {
				MqscRunner mqsc = new MqscRunner(hostname,port,qmgrname,"MFTVIEWER");
				mqscReturnStr=mqsc.runCommand("DIS CONN(*) ALL WHERE (CHANNEL EQ '"+ connFactory + "')");
			mqscReturnStr=mqscReturnStr.replaceAll("AMQ8276.*?CONN","CONN");   
			mqscReturnStr=mqscReturnStr.replaceAll("EXTCONN\\(.*","");
			String [] mqscReturnArr=mqscReturnStr.split("\n");
			for (int i=0;i<numConn;i++ )
			{
				System.out.println(mqsc.runCommand("STOP "+ mqscReturnArr[i]));
			}

			} catch (MQException e) {
				// TODO Auto-generated catch block
				if (2538==e.reasonCode || 2009==e.reasonCode|| 2059==e.reasonCode)
				{
					System.out.println("Ignoring MQRC");
				}
				else 
				{
				e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
