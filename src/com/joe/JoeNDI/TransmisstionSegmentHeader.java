package com.joe.JoeNDI;

public interface TransmisstionSegmentHeader {
	static int offset=0;
	void setHeaderStructIdArr(byte[] b);

	void setHeaderStructIdStr(String headerStructId);

	String getHeaderStructIdStr();

	byte[] getHeaderStructIdArr();

	void setMqSegmLenArr(byte[] b);

	void setMqSegmLenInt(int mqSegmLen);

	int getMqSegmLenInt();

	byte[] getMqSegmLenArr();

	void setConversIdArr(byte[] b);

	void setConversIdStr(int conversId);

	int getConversIdInt();

	byte[] getConversIdArr();

	void setRequestIdArr(byte[] b);

	void setRequestIdInt(int requestId);

	int getRequestIdInt();

	byte[] getRequestIdArr();

	void setByteOrderArr(byte[] b);

	void setByteOrderByte(byte byteOrder);

	byte getByteOrderByte();

	byte[] getByteOrderArr();

	void setSegmTypeArr(byte[] b);

	void setSegmTypeStr(byte segmType);

	byte getSegmTypeInt();

	byte[] getSegmTypeArr();

	void setCtlFlag1Arr(byte[] b);

	void setCtlFlag1Int(byte ctlFlag1);

	int getCtlFlag1Int();

	byte[] getCtlFlag1Arr();

	void setCtlFlag2Arr(byte[] b);

	void setCtlFlag2Str(byte ctlFlag2);

	int getCtlFlag2Int();

	byte[] getCtlFlag2Arr();

	void setLuwIdentArr(byte[] b);

	void setLuwIdentStr(long luwIdent);

	long getLuwIdentInt();

	byte[] getLuwIdentArr();

	void setEncodingArr(byte[] b);

	void setEncodingStr(String encoding);

	String getEncodingInt();

	byte[] getEncodingArr();

	void setHeaderCcsidArr(byte[] b);

	void setHeaderCcsidInt(int ccsid);

	int getHeaderCcsidInt();

	byte[] getHeaderCcsidArr();

	void setHeaderReservedArr(byte[] b);

	void setHeaderReservedInt(String headerReserved);

	String getHeaderReservedInt();

	byte[] getHeaderReservedArr();

	void setInitStructIdArr(byte[] b);
}
