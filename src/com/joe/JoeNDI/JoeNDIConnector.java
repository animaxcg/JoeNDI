package com.joe.JoeNDI;

import java.io.IOException;
import com.ibm.mq.*;

public class JoeNDIConnector {
	public static void main(String[] args) 
	{
			System.out.println("yo");
			clientConnection();

	}
	 public static void clientConnection() 
	 {
		 JoeNDIClient connection = new JoeNDIClient();

		 try {

		 MQQueue queue1 = connection.queueLookup("localhost", 1500, "JOENDI.CHL", MQC.MQOO_OUTPUT|MQC.MQOO_INPUT_SHARED);

			System.out.println("yo");

		 MQMessage msg = new MQMessage();
		 msg.writeString("yo");
	        for (int i=0; i <100000;i++)
	        {
	   		 queue1.put(msg);
	   		 queue1.get(msg);
	        	//queue.get(msg);
	        	System.out.println(i);
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	        
	    } catch (MQException e)
		 {
	    	System.out.println("sleeping");
	    	try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	    	clientConnection();
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 }
}

