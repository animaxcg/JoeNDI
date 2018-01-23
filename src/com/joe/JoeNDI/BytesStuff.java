package com.joe.JoeNDI;

public class BytesStuff {
	public static void main(String [] args) {
		byte [] b = new byte[4];
		b[0]= (byte) 20;
		b[1]= (byte) 1;
		b[2]= (byte) 0;
		b[3]= (byte) 0;
		System.out.println(java.nio.ByteBuffer.wrap(b).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt());
		
	}
}
