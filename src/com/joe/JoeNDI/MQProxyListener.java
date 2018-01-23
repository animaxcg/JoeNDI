package com.joe.JoeNDI;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.protocol7.remotemqsc.MqscRunner;

public class MQProxyListener {
	public final static int COMM_PORT = 1500; // socket port for client comms


	/** Default constructor. */
	public MQProxyListener() {
		System.out.println("yo");
		Socket socket = null;
		Socket clientSocket = null;
		ServerSocket server;
//		qmgrListInit();
//		ArrayList<Integer> arrList=countConnection("HELLO");
//		cfLookup("JOENDI.CHL");
//		for (int i: arrList)
//		{
//			System.out.println("joe: "+ i);
//		}
		try {

			server = new ServerSocket(1500);
			while (true) {
				socket = server.accept();
				new ProxyConnectionHandler(socket, clientSocket, true).start();
				//new ProxyConnectionHandler(clientSocket,socket, true, "JOENDI.CHL").start();
			}
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}

	
	public static void main(String[] args) {
		MQProxyListener tcpServer = new MQProxyListener();
	}
}