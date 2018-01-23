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

public class JoeNDIListener {
	private static HashMap<String,String> connFactoryMap;
	private static ArrayList<String> connInfoList;
	public static void main(String [] args)
	{
		setChannels();
		serverlistener(1500);
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
	private static void serverlistener(int port)
	{
		BufferedReader connFactory;
		ServerSocket serverSocket;
		Socket listenerSocket;
		String temp="";
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Starting Listener");
			while(true)
			{
				listenerSocket=serverSocket.accept();
				connFactory=new BufferedReader(new InputStreamReader(listenerSocket.getInputStream ()));
				temp = connFactory.readLine();
	            
				DataOutputStream outToClient = new DataOutputStream(listenerSocket.getOutputStream());
	            outToClient.writeBytes(cfLookup(temp));
	            outToClient.close();
			}

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
				if (2538==e.reasonCode){
					continue;
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
	
	private static int getNextConn(String connFactory)
	{
		setChannels();
		ArrayList<Integer> numConnList = countConnection(connFactory);
		int minConnIndex=0;
		for (int i =1;i<numConnList.size();i++)
		{
			if (numConnList.get(minConnIndex)>=numConnList.get(i))
			{
				minConnIndex=i;
			}
		}
		 return minConnIndex;	
	}
	

	public static String cfLookup(String channel)
	{

		String connInfo = channel +","+ connFactoryMap.get(channel) + "," +connInfoList.get(getNextConn(channel));
		System.out.println(connInfo);
		return connInfo;
	}

}
