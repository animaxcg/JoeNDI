package com.joe.JoeNDI;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class MQInitialData extends MQPacket implements TransmisstionSegmentHeader {



	private static final Exception Exception = null;

	private byte[] headerStructIdArr = new byte[4];
	private String headerStructIdStr = "";

	private byte[] mqSegmLenArr = new byte[4];
	private int mqSegmLenInt = 0;

	private byte[] conversIdArr = new byte[4];
	private int conversIdInt = 0;

	private byte[] requestIdArr = new byte[4];
	private int requestIdInt = 0;

	private byte[] byteOrderArr = new byte[1];
	private byte byteOrderByte = (byte) 1;

	private byte[] segmTypeArr = new byte[1];
	private byte segmTypeByte = (byte) 1;

	private byte[] ctlFlag1Arr = new byte[1];
	private byte ctlFlag1Byte = (byte) 1;

	private byte[] ctlFlag2Arr = new byte[1];
	private byte ctlFlag2Byte = (byte) 1;

	private byte[] luwIdentArr = new byte[8];
	private long luwIdentLong = 0;

	private byte[] encodingArr = new byte[4];
	private String encodingStr = "";

	private byte[] headerCcsidArr = new byte[2];
	int headerCcsidInt = 0;

	private byte[] headerReservedArr = new byte[2];
	private String headerReservedStr = "";

	private byte[] initStructIdArr = new byte[4];
	private String initStructIdStr = "";

	private byte[] fapLevelArr = new byte[1];
	private int fapLevelInt = 0;

	private byte[] capFlag1Arr = new byte[1];
	private byte capFlag1Byte = (byte) 1;
	private byte[] eCapFlag1Arr = new byte[1];
	private byte eCapFlag1Byte = (byte) 1;
	private byte[] intiErrFlag1Arr = new byte[1];
	private byte intiErrFlag1Byte = (byte) 1;

	private byte[] initReservedArr = new byte[2];
	private String initReservedStr = "";

	private byte[] maxMsgBatchArr = new byte[2];
	private int maxMsgBatchInt = 0;

	private byte[] maxTrSizeArr = new byte[4];
	private int maxTrSizeInt = 0;

	private byte[] maMsgSizeArr = new byte[4];
	private int maMsgSizeInt = 0;

	private byte[] seqWrapValueArr = new byte[4];
	private int seqWrapValueInt = 0;

	private byte[] channelNameArr = new byte[20];
	private String channelNameStr = "";

	private byte[] capFlag2Arr = new byte[1];
	private byte capFlag2Byte = (byte) 1;
	private byte[] eCapFlag2Arr = new byte[1];
	private byte eCapFlag2Byte = (byte) 1;

	private byte[] initCcsidArr = new byte[2];
	int initCcsidInt = 0;

	private byte[] qmgrNameArr = new byte[48];
	private String qmgrNameStr = "";

	private byte[] heartBeatIntervalArr = new byte[4];
	private int heartBeatIntervalInt = 0;

	private byte[] eflLengthArr = new byte[2];
	private int eflLengthint = 0;

	private byte[] intiErrFlag2Arr = new byte[1];
	private byte intiErrFlag2Byte = (byte) 1;

	private byte[] initReserved1Arr = new byte[1];
	private String initReserved1Str = "";

	private byte[] hdrCprsLstArr = new byte[2];
	private String hdrCprsLstStr = "";

	private byte[] msgCprsLstArr = new byte[16];
	private String msgCprsLstStr = "";

	private byte[] initReserved2Arr = new byte[2];
	private String initReserved2Str = "";

	private byte[] sslKeyRstArr = new byte[4];
	private int sslKeyRstInt = 0;

	private byte[] convBySktArr = new byte[4];
	private int convBySktInt = 0;

	private byte[] capFlag3Arr = new byte[1];
	private byte capFlag3Byte = (byte) 1;
	private byte[] eCapFlag3Arr = new byte[1];
	private byte eCapFlag3Byte = (byte) 1;

	private byte[] initReserved3Arr = new byte[2];
	private String initReserved3Str = "";

	private byte[] processIdArr = new byte[4];
	private int processIdInt = 0;

	private byte[] threadIdArr = new byte[4];
	private int threadIdInt = 0;

	private byte[] traceIdArr = new byte[4];
	private int traceIdInt = 0;

	private byte[] prodIdArr = new byte[12];
	private String prodIdInt = "";

	private byte[] mqmIdArr = new byte[48];
	private String mqmIdInt = "";

	private byte[] unknown = new byte[8];
	private String replyChannel;
	public MQInitialData(DataInputStream in) throws IOException {
		super(in);


		// TODO Auto-generated constructor stub
	}

	public MQInitialData(byte [] headers,byte [] payload) throws IOException {
		super(headers,payload);


		// TODO Auto-generated constructor stub
	}

	public void setHeaderStructIdArr(byte[] b) {
		headerStructIdArr = b;
		headerStructIdStr = toString(b, 0, b.length);
	}

	public void setHeaderStructIdStr(String headerStructId) {
		headerStructIdArr = headerStructId.getBytes();
		headerStructIdStr = headerStructId;
	}

	public String getHeaderStructIdStr() {
		return toString(headers, 0, 4);
		// return headerStructIdStr;
	}

	public byte[] getHeaderStructIdArr() {
		return toByteArr(headers, 0, 4);
	}

	public void setMqSegmLenArr(byte[] b) {
		mqSegmLenArr = b;
		mqSegmLenInt = toInt(b, 0, b.length);
	}

	public void setMqSegmLenInt(int mqSegmLen) {
		mqSegmLenArr = intToByteArr(mqSegmLen, 4);
		mqSegmLenInt = mqSegmLen;
	}

	public int getMqSegmLenInt() {
		return toInt(headers, 4, 8);
		// return mqSegmLenInt;
	}

	public byte[] getMqSegmLenArr() {
		return toByteArr(headers, 4, 8);
		// return mqSegmLenArr;
	}

	public void setConversIdArr(byte[] b) {
		conversIdArr = b;
		conversIdInt = toInt(b, 0, b.length);
	}

	public void setConversIdStr(int conversId) {
		conversIdArr = intToByteArr(conversId, 4);
		conversIdInt = conversId;
	}

	public int getConversIdInt() {
		if (super.offset != 0) {
			return toInt(headers, 8, 12);
		} else {
			// throw exception
			System.out.println("Error");
			return -1;
		}

	}

	public byte[] getConversIdArr() {
		if (super.offset != 0) {
			return toByteArr(headers, 8, 12);
		} else {
			// throw exception
			System.out.println("Error");
			return null;
		}
	}

	public void setRequestIdArr(byte[] b) {
		requestIdArr = b;
		requestIdInt = toInt(b, 0, b.length);
	}

	public void setRequestIdInt(int requestId) {
		requestIdArr = intToByteArr(requestId, 4);
		requestIdInt = requestId;
	}

	public int getRequestIdInt() {
		if (super.offset != 0) {
			return toInt(headers, 8, 12);
		} else {
			// throw exception
			System.out.println("Error");
			return -1;
		}
	}

	public byte[] getRequestIdArr() {
		if (super.offset != 0) {
			return toByteArr(headers, 8, 12);
		} else {
			// throw exception
			System.out.println("Error");
			return null;
		}
	}

	public void setByteOrderArr(byte[] b) {
		byteOrderArr = b;
		byteOrderByte = b[0];
	}

	public void setByteOrderByte(byte byteOrder) {
		byteOrderArr[0] = byteOrder;
		byteOrderByte = byteOrder;
	}

	public byte getByteOrderByte() {
		return toByteArr(headers, 9 + super.offset, 10 + super.offset)[0];
	}

	public byte[] getByteOrderArr() {
		return toByteArr(headers, 9 + super.offset, 10 + super.offset);
	}

	public void setSegmTypeArr(byte[] b) {
		segmTypeArr = b;
		segmTypeByte = b[0];
	}

	public void setSegmTypeStr(byte segmType) {
		segmTypeArr[0] = segmType;
		segmTypeByte = segmType;
	}

	public byte getSegmTypeInt() {
		return toByteArr(headers, 9 + super.offset, 10 + super.offset)[0];
	}

	public byte[] getSegmTypeArr() {
		return toByteArr(headers, 9 + super.offset, 10 + super.offset);
	}

	public void setCtlFlag1Arr(byte[] b) {
		ctlFlag1Arr = b;
		ctlFlag1Byte = b[0];
	}

	public void setCtlFlag1Int(byte ctlFlag1) {
		ctlFlag1Arr[0] = ctlFlag1;
		ctlFlag1Byte = ctlFlag1;
	}

	public int getCtlFlag1Int() {
		return headers[10 + super.offset];
	}

	public byte[] getCtlFlag1Arr() {
		return toByteArr(headers, 10 + super.offset, 11 + super.offset);
	}

	public void setCtlFlag2Arr(byte[] b) {
		ctlFlag2Arr = b;
		ctlFlag2Byte = b[0];
	}

	public void setCtlFlag2Str(byte ctlFlag2) {
		ctlFlag1Arr[0] = ctlFlag2;
		ctlFlag1Byte = ctlFlag2;
	}

	public int getCtlFlag2Int() {
		return headers[11 + super.offset];
	}

	public byte[] getCtlFlag2Arr() {
		return toByteArr(headers, 11 + super.offset, 12 + super.offset);
	}

	public void setLuwIdentArr(byte[] b) {
		luwIdentArr = b;
		luwIdentLong = toLong(b, 0, b.length);
	}

	public void setLuwIdentStr(long luwIdent) {
		luwIdentArr = longToByteArr(luwIdent, 8);
		luwIdentLong = luwIdent;
	}

	public long getLuwIdentInt() {
		return toLong(headers, 12 + super.offset, 20 + super.offset);
	}

	public byte[] getLuwIdentArr() {
		return toByteArr(headers, 12 + super.offset, 20 + super.offset);
	}

	public void setEncodingArr(byte[] b) {
		encodingArr = b;
		encodingStr = toString(b, 0, b.length);
	}

	public void setEncodingStr(String encoding) {
		encodingArr = encoding.getBytes();
		encodingStr = encoding;
	}

	public String getEncodingInt() {
		return encodingStr;
	}

	public byte[] getEncodingArr() {
		return encodingArr;
	}

	public void setHeaderCcsidArr(byte[] b) {
		headerCcsidArr = b;
		headerCcsidInt = toInt(b, 0, b.length);
	}

	public void setHeaderCcsidInt(int ccsid) {
		headerCcsidArr = intToByteArr(ccsid, 2);
		headerCcsidInt = ccsid;
	}

	public int getHeaderCcsidInt() {
		return headerCcsidInt;
	}

	public byte[] getHeaderCcsidArr() {
		return headerCcsidArr;
	}

	public void setHeaderReservedArr(byte[] b) {
		headerReservedArr = b;
		headerReservedStr = toString(b, 0, b.length);
	}

	public void setHeaderReservedInt(String headerReserved) {
		headerReservedArr = headerReserved.getBytes();
		headerReservedStr = headerReserved;
	}

	public String getHeaderReservedInt() {
		return headerReservedStr;
	}

	public byte[] getHeaderReservedArr() {
		return headerReservedArr;
	}

	public void setInitStructIdArr(byte[] b) {
		initStructIdArr = b;
		initStructIdStr = toString(b, 0, b.length);
	}

	public void setInitStructIdStr(String structId) {
		initStructIdArr = structId.getBytes();
		initStructIdStr = structId;
	}

	public String getInitStructIdStr() {
		return initStructIdStr;
	}

	public byte[] getInitStructIdArr() {
		return initStructIdArr;
	}

	public void setStructId() {
		headerStructIdArr[0] = (byte) 0;
		headerStructIdArr[1] = (byte) 0;
		headerStructIdArr[2] = (byte) 0;
		headerStructIdArr[3] = (byte) 0;
	}
	
	public void setReplyInt(byte[] b, int value, int start, int end) {
		int length=end-start;
		int paddedLength=length;
		int padding=0;
		if (length<4)
		{
			paddedLength=4;
			padding=Math.abs(4-length);
		}
		else
		{
			
		}
		byte [] returnBytes = new byte[paddedLength];
		//returnBytes=intToByteArr(value,length);
		returnBytes=ArrayUtils.addAll(intToByteArr(value,paddedLength),new byte[0]);
		for (int i=0;i<length;i++)
		{
//			System.out.println(returnBytes[i+padding]);
			b[start+i]=returnBytes[i+padding];
		}
	}
	public void setReplyString(byte[] b, String value, int start, int end) {
		int length=end-start;
		int paddedLength=length;
		int channelNameLength=value.getBytes().length;
		byte [] returnBytes = new byte[paddedLength];
		//returnBytes=intToByteArr(value,length);
		returnBytes=ArrayUtils.addAll(value.getBytes(),new byte[length-channelNameLength]);
		for (int i=0;i<channelNameLength;i++)
		{
//			System.out.println(returnBytes[i+padding]);
			b[start+i]=returnBytes[i];
		}
		for (int i=channelNameLength+start;i< end;i++){
			b[i]= (byte) 32;
		}
	}
	
	
	public void setReplyHbInterval(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getHbInterval(), 96,100);
	}
	public void setReplySeqWrapVal(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getSeqWrapVal(), 20,24);
	}
	public void setReplyMaxBatchSize(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getMaxBatchSize(), 10,12);
	}
	public void setReplyMsgSize(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getMsgSize(), 16,20);
	}
	public void setReplyFapLevel(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getFapLevel(), 4,5);
	}	
	public void setReplyMaxTransferSize(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getMaxTransferSize(), 12,16);
	}
	public void setReplyCcisd(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getCcisd(), 46,48);
	}
	public void setEFLLength(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getEFLLength(), 100,102);
	}
	
	public void setCapFlag3(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getCapFlag3(), 132,133);
	}
	public void setECapFlag3(QueueManger qmgr)
	{
		setReplyInt(payloadReply,qmgr.getECapFlag3(), 133,134);
	}
	
	public void setChannelName(String channelName)
	{
		setReplyString(payload,channelName, 24,44);
	}
	

	public int getHbInterval() {
		//return java.nio.ByteBuffer.wrap(payload, 96, 100).getInt();
		return toInt(payload, 96, 100);
	}

	public int getSeqWrapVal() {
		return toInt(payload, 20, 24);
	}

	public int getMaxBatchSize() {
		return toInt(payload, 10, 12);
	}

	public int getMsgSize() {
		return toInt(payload, 16, 20);
	}

	public int getFapLevel() {
		return toInt(payload, 4, 5);
	}

	public int getMaxTransferSize() {
		return toInt(payload, 12, 16);
	}

	public int getCcisd() {
		return toInt(payload, 46, 48);
	}
	
	public int getEFLLength() {
		return toInt(payload, 100, 102);
	}
	
	public int getCapFlag3() {
		return toInt(payload, 120, 121);
	}	
	
	public int getECapFlag3() {
		return toInt(payload, 121, 122);
	}
	
	public String getChlannelName() {
		return toString(payload, 24, 44).replaceAll(" ", "");
	}
	

	public byte[] getReplyPacket() {
		headersReply = new byte[super.getHeaders().length];
		payloadReply = new byte[super.getPayload().length];
		byte[] replyPacket = new byte[super.getSegmentLength()];
		headersReply = super.getHeaders();
		payloadReply = super.getPayload();
		QueueManger dJoe01 = new QueueManger("DJOE01");
//		System.out.println("getHbInterval "+getHbInterval());
//		System.out.println("getSeqWrapVal "+getSeqWrapVal());
//		System.out.println("getMaxBatchSize "+getMaxBatchSize());
//		System.out.println("getMsgSize "+getMsgSize());
//		System.out.println("getFapLevel "+getFapLevel());
//		System.out.println("getMaxTransferSize "+getMaxTransferSize());
		int flg=dJoe01.iniErrFlg(getHbInterval(), getSeqWrapVal(), getMaxBatchSize(), getMsgSize(), getFapLevel(),
				getMaxTransferSize(), getCcisd());
		if (flg!=0)
		{
			headersReply[10+super.offset]= (byte) 2;
		}
		payloadReply[7+super.offset]=(byte) flg;
		setReplyHbInterval(dJoe01);
		setReplySeqWrapVal(dJoe01);
		setReplyMaxBatchSize(dJoe01);
		setReplyMsgSize(dJoe01);
		setReplyFapLevel(dJoe01);
		setReplyMaxTransferSize(dJoe01);
		setReplyCcisd(dJoe01);
		setEFLLength(dJoe01);
		setCapFlag3(dJoe01);
		setECapFlag3(dJoe01);
		setChannelName(replyChannel);
		replyPacket=ArrayUtils.addAll(headersReply, payloadReply);
		return replyPacket;
	}
	public byte[] getFowardPacket(String replyChannel) {
		//setReplyChannel(replyChannel);
		setChannelName(replyChannel);
		byte[] replyPacket = new byte[super.getSegmentLength()];
		replyPacket=ArrayUtils.addAll(headers, payload);
		return replyPacket;
	}
	public void setReplyChannel(String replyChannel) {
		this.replyChannel=replyChannel;	
	}

}
