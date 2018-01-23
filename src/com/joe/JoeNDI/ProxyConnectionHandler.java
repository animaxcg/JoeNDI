package com.joe.JoeNDI;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.protocol7.remotemqsc.MqscRunner;

public class ProxyConnectionHandler extends Thread {
	protected Socket socket;
	protected Socket clientSocket;
	private static ArrayList<String[]> qmgrList;
	private static ArrayList<String[]> chlList;
	boolean launchThread = true;
	String channel;

	public ProxyConnectionHandler(Socket socket, Socket clientSocket, boolean launchThread) {
		this.launchThread = launchThread;
		this.socket = socket;
		this.clientSocket = clientSocket;
	}

	public void run() {
		DataOutputStream dataOut = null;
		DataInputStream dataIn = null;

		while (true) {

			try {
				dataIn = new DataInputStream(socket.getInputStream());
				MQPacket packet = new MQPacket(dataIn);

				int segmentType = packet.getSegmentType();
				if (segmentType == 1) {
					MQInitialData mqInit = new MQInitialData(packet.getHeaders(), packet.getPayload());
					// mqInit.setReplyChannel("MFTVIEWER");
					listInit();
					channel=getChannelReply(mqInit.getChlannelName());
//					System.out.println("index" + cfIndex);
//					System.out.println("channel" + (mqInit.getChlannelName()));
//					System.out.println("channelReplay" + channel);

					if (launchThread) {
						int cfIndex = getNextConn(channel);
						System.out.println("Connecting to: " + qmgrList.get(cfIndex)[1] + " "
								+ Integer.parseInt(qmgrList.get(cfIndex)[2])+ " " + qmgrList.get(cfIndex)[0]+ " " + channel);
						clientSocket = new Socket(qmgrList.get(cfIndex)[1], Integer.parseInt(qmgrList.get(cfIndex)[2]));
						new ProxyConnectionHandler(clientSocket, socket, false).start();
						launchThread=false;
						Thread.sleep(500);

					}
					dataOut = new DataOutputStream(clientSocket.getOutputStream());
					dataOut.write(mqInit.getFowardPacket(channel));

					

				} else {
					dataOut.write(packet.getByteArray());
				}
				dataOut.flush();


			} catch (IOException e) {
				// e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static String getChannelReply(String channel) {
		for (int i = 0; i < chlList.size(); i++) {
			if (chlList.get(i)[0].equals(channel)) {
				return chlList.get(i)[1];
			}
		}
		return null;
	}

	private static void listInit() {
		qmgrList = new ArrayList<String[]>();
		chlList = new ArrayList<String[]>();
		String line = null;
		BufferedReader brConnFactory;
		try {
			brConnFactory = new BufferedReader(
					new FileReader("/Users/mylopage/Documents/workspace/mq-joendi/config/qmgr.ini"));

			while ((line = brConnFactory.readLine()) != null) {

				qmgrList.add(line.split(","));
			}
			line = null;
			brConnFactory.close();
			brConnFactory = new BufferedReader(
					new FileReader("/Users/mylopage/Documents/workspace/mq-joendi/config/channel.ini"));
			while ((line = brConnFactory.readLine()) != null) {
				chlList.add(line.split(","));
			}
			brConnFactory.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static ArrayList<Integer> countConnection(String channel) {
		ArrayList<Integer> numConnList = new ArrayList<Integer>();
		// loop through each queue manager in cluster

		for (int i = 0; i < qmgrList.size(); i++) {
			String qmgrname = qmgrList.get(i)[0];
			String hostname = qmgrList.get(i)[1];
			int port = Integer.parseInt(qmgrList.get(i)[2]);
			MQEnvironment.hostname = hostname;
			// Change to Admin config channel
			MQEnvironment.channel = "MFTVIEWER";
			MQEnvironment.port = port;
			MQQueueManager qmgr = null;
			try {
				qmgr = new MQQueueManager(qmgrname);
			} catch (MQException e) {
				// continues to next iteration if Qmgr is unreachable
				if (2538 == e.reasonCode) {
					continue;
				}
			}

			MqscRunner mqsc = new MqscRunner();
			String mqscReturnStr = "";
			try {
				mqscReturnStr = mqsc.runCommand(qmgr, "DIS CONN(*) ALL WHERE (CHANNEL EQ '" + channel + "')");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			numConnList.add(StringUtils.countMatches(mqscReturnStr, "AMQ8276"));
		//	System.out.println("Qmgr: " + qmgrname + " Host: " + hostname + " Connections: " + numConnList.get(i));

		}
		return numConnList;
	}

	private static int getNextConn(String channel) {
		listInit();
		ArrayList<Integer> numConnList = countConnection(channel);
		int minConnIndex = 0;
		for (int i = 1; i < numConnList.size(); i++) {
			//System.out.println("i: " +  numConnList.get(minConnIndex) + ">=" +  numConnList.get(i));
			if (numConnList.get(minConnIndex) >= numConnList.get(i)) {
				minConnIndex = i;
			}
		}
		return minConnIndex;
	}
}
