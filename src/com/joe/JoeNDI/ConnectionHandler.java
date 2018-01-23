package com.joe.JoeNDI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler extends Thread{
    protected Socket socket;

	public ConnectionHandler(Socket clientSocket)
	{
        this.socket = clientSocket;
	}
    public void run() {
		DataOutputStream dataOut=null;
		DataInputStream dataIn=null;
		
		while (true)
		{
			try {
			dataIn = new DataInputStream(socket.getInputStream());
			dataOut = new DataOutputStream(socket.getOutputStream());
			MQPacket packet = new MQPacket(dataIn);
			int segmentType=packet.getSegmentType();
			System.out.println("SegTypeNum" + segmentType + " ");
			if (segmentType ==  1) {
				MQInitialData mqInit=new MQInitialData(packet.getHeaders(),packet.getPayload());
				dataOut.write(mqInit.getReplyPacket());
				//dataOut.write(((MQInitialData) packet).getReplyPacket());
			}
			//dataIn.close();
			//dataOut.close();
			}
			catch (IOException e)
			{
				//e.printStackTrace();
			}
		}
    }
}
