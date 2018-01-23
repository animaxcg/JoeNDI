package com.joe.JoeNDI;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;

public class MQConnection {
	public static void main(String[] args)
	{
		MQEnvironment.hostname = "127.0.0.1";
		//Change to Admin config channel
	    MQEnvironment.channel = "MFTVIEWER";
	    MQEnvironment.port = 1500;
	    try {
			MQQueueManager qmgr = new MQQueueManager("DJOE01");
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
