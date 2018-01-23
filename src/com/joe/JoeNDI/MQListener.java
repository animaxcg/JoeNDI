package com.joe.JoeNDI;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MQListener {
	public final static int COMM_PORT = 1500; // socket port for client comms

	private ServerSocket serverSocket;
	private InetSocketAddress inboundAddr;
	private TcpPayload payload;

	private int pow(int base, int power) {
		for (int i = 1; i < power; i++) {
			base *= base;
		}
		return base;
	}

	/** Default constructor. */
	public MQListener() {
		System.out.println("" + this.pow(2, 3));
//		this.payload = new TcpPayload();
		DataOutputStream dataOut=null;
		DataInputStream dataIn=null;
		Socket socket=null;
		while (true) {
			ServerSocket server;
			try {
				server = new ServerSocket(1500);
				socket = server.accept();
				new ConnectionHandler(socket).start();
//				dataIn = new DataInputStream(socket.getInputStream());
//				dataOut = new DataOutputStream(socket.getOutputStream());
//				MQPacket packet = new MQInitialData(dataIn);
//				dataOut.write(((MQInitialData) packet).getReplyPacket());
//				dataIn.close();
//				dataOut.close();
//				System.out.println("Endianness " + packet.getEndianness());
//				System.out.println("packet: " + packet.getSegmentLength() + " " + packet.getStructId() + " "
//						+ packet.getSegmentType());
//				System.out.println("getHeaderStructIdStr: " + ((MQInitialData) packet).getHeaderStructIdStr());
//				System.out.println("getHeaderStructIdArr: " + ((MQInitialData) packet).getHeaderStructIdArr());
//				System.out.println("getMqSegmLenInt: " + ((MQInitialData) packet).getMqSegmLenInt());
//				System.out.println("getMqSegmLenArr: " + ((MQInitialData) packet).getMqSegmLenArr());
//				System.out.println("getConversIdInt: " + ((MQInitialData) packet).getConversIdInt());
//				System.out.println("getConversIdArr: " + ((MQInitialData) packet).getConversIdArr());
//				System.out.println("getRequestIdInt: " + ((MQInitialData) packet).getRequestIdInt());
//				System.out.println("getRequestIdArr: " + ((MQInitialData) packet).getRequestIdArr());
//				System.out.println("getByteOrderByte: " + ((MQInitialData) packet).getByteOrderByte());
//				System.out.println("getByteOrderArr: " + ((MQInitialData) packet).getByteOrderArr());
//				System.out.println("getSegmTypeInt: " + ((MQInitialData) packet).getSegmTypeInt());
//				System.out.println("getSegmTypeArr: " + ((MQInitialData) packet).getSegmTypeArr());
//				System.out.println("getCtlFlag1Int: " + ((MQInitialData) packet).getCtlFlag1Int());
//				System.out.println("getCtlFlag1Arr: " + ((MQInitialData) packet).getCtlFlag1Arr());
//				System.out.println("getCtlFlag2Int: " + ((MQInitialData) packet).getCtlFlag2Int());
//				System.out.println("getCtlFlag2Arr: " + ((MQInitialData) packet).getCtlFlag2Arr());
//				System.out.println("getLuwIdentInt: " + ((MQInitialData) packet).getLuwIdentInt());
//				System.out.println("getLuwIdentArr: " + ((MQInitialData) packet).getLuwIdentArr());

				System.out.println("after sent");
//				for (int i = 0; i < packet.getSegmentLength(); i++) {
//					String yo = String.format("%02x", packet.getByteArray()[i]);
					// Integer.parseInt(yo.substring(0,1))*pow(16,2);
					// i++
					// Integer.parseInt(yo.substring(1,2);

//					System.out.println(i + ": " + yo);
//				}

			} catch (IOException e) {
			//	 e.printStackTrace();
			}

			// ooStream.writeObject(this.payload); // send serilized payload
			// ooStream.close();
			// Thread.sleep(1000);
		}

	}

	public static void main(String[] args) {
		MQListener tcpServer = new MQListener();
	}
}