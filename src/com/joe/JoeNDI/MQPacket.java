package com.joe.JoeNDI;

import java.lang.Math.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MQPacket {

	/*
	 * private int headerLength=36; private int payloadLength; private int
	 * packetLength; private String structId; private int conversId; private int
	 * requestId; private byte byteOrder; private byte segmentTypeByte; private
	 * String segmentTypeStr; private String luwIndent; private int ccsid;
	 * private byte [] reserved= new byte[2]; private byte [] encoding= new
	 * byte[4];
	 */
	private String headerType = "";
	private static boolean isBigEndian = true;
	protected int offset=0;
	protected byte[] payload = null;
	protected byte[] headers = null;
	protected byte[] headersReply = null;
	protected byte[] payloadReply = null;

	public MQPacket(DataInputStream in) throws IOException {
		setHeaders(in);
		setPayload(in);

	}
	public MQPacket(byte [] headers,byte [] payload) {
		this.headers=headers;
		this.payload=payload;
		
	}
	private void setEndianness() {

	}

	public byte getEndianness() {
		if (isBigEndian) {
			return (byte) 1;
		} else {
			return (byte) 2;
		}
	}

	public int getSegmentLength() {
		// TODO Auto-generated method stub
		return toInt(headers, 4, 8);

	}

	public String getStructId() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return toString(headers, 0, 4);
	}

	public int getSegmentType() {
		// TODO Auto-generated method stub
		//byte[] b = toByteArr(headers, 16, 20);
		return toInt(headers, 9+offset, 10+offset);
		//return toHexStr(b);
	}

	public byte[] getPayload() {
		return payload;
	}

	public byte[] getHeaders() {
		return headers;
	}

	public void setPayload(DataInputStream in) throws IOException {
		// TODO Auto-generated method stub
		payload = new byte[getSegmentLength() - headers.length];
		in.readFully(payload);
	}

	public byte[] getReplyPacket() {
		// TODO Auto-generated method stub
		return ArrayUtils.addAll(headers, payload);
	}

	public byte[] getByteArray(MQPacket replyPacket) {
		// TODO Auto-generated method stub

		return replyPacket.getByteArray();
	}

	public byte[] getByteArray() {
		return ArrayUtils.addAll(headers, payload);
	}

	public String getHeaderType() {
		return headerType;
	}

	public void setHeaders(DataInputStream in) throws IOException {
		// TODO Auto-generated method stub
		byte[] structId = new byte[4];
		byte[] tempbyteArr = null;
		in.readFully(structId);

		headerType = toString(structId, 0, structId.length);
		
		if (headerType.equals("TSH ") || headerType.equals("TSHC")) {
			headers = new byte[28];
			tempbyteArr = new byte[24];
			in.readFully(tempbyteArr);
			headers = ArrayUtils.addAll(structId, tempbyteArr);
			if (headers[8] == 2) {
				isBigEndian = false;
			}
		} else if (headerType.equals("TSHM")) {
			offset=8;
			headers = new byte[36];
			tempbyteArr = new byte[32];
			in.readFully(tempbyteArr);
			headers = ArrayUtils.addAll(structId, tempbyteArr);
			if (headers[8] == 2) {
				isBigEndian = false;
			}
			// 36 bytes
			
			else if (headerType.equals("   <"))
			{
				System.out.println("STATUS");
			}
			else if (headerType.equals("    0"))
			{
				System.out.println("STATUS0");
			}
		} else {
			System.out.println("Header Type not recignized");

			System.out.println("Header: " + headerType + "StructHex: "+ toHex(structId,0,4));

		}
	}

	protected static String toString(byte[] b, int start, int end) {
		String returnStr = null;

		try {
			returnStr = new String(b, start, end-start, "ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnStr;
	}

	public byte[] toByteArr(byte[] b, int start, int end) {
		return Arrays.copyOfRange(b, start, end);
	}

	public static String toHexStr(byte[] b) {
		String returnStr = "";
		for (int i = 0; i < b.length; i++) {
			returnStr += String.format("%02x", b[i]);
		}
		return returnStr;
	}

	public int toInt(byte[] b, int start, int end) {
		int length=end-start;
		String temp="";
		byte[] cacheByteArr=new byte[8];
		int index=0;
		if (isBigEndian) {
			if (length<8)				
			{
				if (length<4)
				{
					for (int i=length; i<4;i++)
					{
//						System.out.println("0index: " + index+ " i: "+ i+" : "+length);
						cacheByteArr[index]=(byte) 0;
						index++;
					}
				}
				for (int i=start; i<end;i++)
				{
//					System.out.println("1index: " + index+ " i: "+ i+" : "+length+" byte "+b[i]);
					cacheByteArr[index]=b[i];
					index++;
				}

			}
			for (int i=index;i<8;i++)
			{
//				System.out.println("2index: " + index+ " i: "+ i+" : "+length+" byte "+(byte) 0);
				cacheByteArr[index]=(byte) 0;
				index++;
			}
			return java.nio.ByteBuffer.wrap(cacheByteArr).getInt();

		} else {
			for (int i=(end-1); i>=start;i--)
			{
				cacheByteArr[index]=b[i];
				index++;
			}
			for (;index<8;index++)
			{
				cacheByteArr[index]=(byte) 0;
			}
			return java.nio.ByteBuffer.wrap(cacheByteArr).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
		}

	}

	public String toHex(byte[] b, int start, int end) {
		String hexStr = "";
		String cacheHexStr = null;
		for (int i = start; i < end; i++) {
			cacheHexStr = String.format("%02x", b[i]);
			if (cacheHexStr.length() == 1) {
				cacheHexStr = "0" + cacheHexStr;
			}
			hexStr += cacheHexStr;
		}
		return hexStr;
	}

	public long toLong(byte[] b, int start, int end) {
		if (isBigEndian) {
			return java.nio.ByteBuffer.wrap(Arrays.copyOfRange(b, start, end)).getLong();

		} else {
			return java.nio.ByteBuffer.wrap(b, start, end).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
		}
	}

	public byte[] intToByteArr(int number, int length) {
		
		//	System.out.println("int: " + number + " Length: "+ length);
		
		return ByteBuffer.allocate(length).putInt(number).array();
	}

	public byte[] longToByteArr(long number, int length) {
		return ByteBuffer.allocate(length).putLong(number).array();
	}

}
