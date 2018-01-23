package com.joe.JoeNDI;

import java.io.IOException;
import com.ibm.mq.*;

public class MQClient {
	public static void main(String[] args) 
	{
			System.out.println("yo");
			clientConnection();

	}
	 public static void clientConnection() 
	 {
		 JoeNDIClient connection = new JoeNDIClient();
		 connection.setConnStr("JOENDI.CHL,JOENDI.QUEUE,,172.16.223.132,1414");
		 MQQueue queue1;
		try {
			queue1 = connection.getQueue(MQC.MQOO_OUTPUT|MQC.MQOO_INPUT_SHARED);




			System.out.println("yo");

		 MQMessage msg = new MQMessage();
		 msg.writeString("yo");
		 long i=0;
		 while (true)
		 {
	   		 queue1.put(msg);
	   		 queue1.get(msg);
	        	//queue.get(msg);
	        	System.out.println(i++);
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
		} catch (MQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 }	       
}

