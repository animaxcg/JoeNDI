package com.joe.JoeNDI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.ibm.mq.*;

public class JoeNDIClient {
	
	private String queueName;
	private String qmgrName;
	private String hostname;
	private int port;
	private static String channel;
	
	private void getConnStr(String hostname, int port, String connFactory)
	{
		String connStr=null;
		Socket replySocket;
		BufferedReader inFromServer;
		try 
		{
			replySocket = new Socket (hostname,port);
			PrintWriter pwReply;
			pwReply = new PrintWriter(replySocket.getOutputStream(),true);
			pwReply.println(connFactory);
			inFromServer = new BufferedReader(new InputStreamReader(replySocket.getInputStream()));
			connStr = inFromServer.readLine();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
		}
		setConnStr(connStr);
	}
	public void setConnStr(String connStr)
	{
		String [] connArr=connStr.split(",");
		channel=connArr[0];
		queueName=connArr[1];
		qmgrName=connArr[2];
		hostname=connArr[3];
		port=Integer.parseInt(connArr[4]);
	}
	public MQQueueManager getQmgrConnection() throws MQException 
	{
		MQEnvironment.channel=channel;
		MQEnvironment.port=port;
		MQEnvironment.hostname=hostname;
		return (new MQQueueManager(qmgrName));
	}
	public MQQueue getQueue(int openOption) throws MQException
	{
		return getQmgrConnection().accessQueue(queueName, openOption);
	}
	public MQQueue queueLookup(String hostname, int port, String connFactory, int openOption) throws MQException
	{
		getConnStr(hostname,port,connFactory);
		return getQueue(openOption);
	}
	
}
