package com.joe.JoeNDI;

public class QueueManger {
	private String name;
	private int port = 1414;
	private int hbInterval = 300;
	private int seqWrapVal = 0;
	private int maxBatchSize = 50;
	private int msgSize = 4194304;
	private int fapLevel = 13;
	private int maxTransferSize = 32748;
	private byte[] encoding;
	private int ccisd = 819;
	private String prodId="MQMM09000000";
	private int eFLLength=0;
	private int capFlag3=0;
	private int eCapFlag3=8;

	public QueueManger() {

	}

	public QueueManger(String name) {
		this.setName(name);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getHbInterval() {
		return hbInterval;
	}

	public void setHbInterval(int hbInterval) {
		this.hbInterval = hbInterval;
	}

	public int getSeqWrapVal() {
		return seqWrapVal;
	}

	public void setSeqWrapVal(int seqWrapVal) {
		this.seqWrapVal = seqWrapVal;
	}

	public int getMaxBatchSize() {
		return maxBatchSize;
	}

	public void setMaxBatchSize(int maxBatchSize) {
		this.maxBatchSize = maxBatchSize;
	}

	public int getMsgSize() {
		return msgSize;
	}

	public void setMsgSize(int msgSize) {
		this.msgSize = msgSize;
	}

	public int getFapLevel() {
		return fapLevel;
	}

	public void setFapLevel(int fapLevel) {
		this.fapLevel = fapLevel;
	}

	public int getMaxTransferSize() {
		return maxTransferSize;
	}

	public void setMaxTransferSize(int maxTransferSize) {
		this.maxTransferSize = maxTransferSize;
	}
	
	public int getCcisd() {
		return ccisd;
	}

	public void setCcisd(int ccisd) {
		this.ccisd = ccisd;
	}

	public byte[] getEncoding() {
		return encoding;
	}

	public void setEncoding(byte[] encoding) {
		encoding = encoding;
	}
	
	public int getProdId() {
		return port;
	}

	public void setProdId(int prodId) {
		this.port = port;
	}

	public int iniErrFlg(int hbInterval, int seqWrapVal, int maxBatchSize, int msgSize, int fapLevel,
			int maxTransferSize, int ccisd) {
		int errorCode = 0;
		if (this.hbInterval != hbInterval) {
			errorCode += 128;
//			System.out.println("hbInterval failed");
		}
		if (this.seqWrapVal != seqWrapVal) {
			errorCode += 64;
//			System.out.println("seqWrapVal failed");
		}
		if (this.maxBatchSize < maxBatchSize) {
			errorCode += 32;
//			System.out.println("maxBatchSize failed");
		}
		if (this.msgSize < msgSize) {
			errorCode += 16;
//			System.out.println("msgSize failed");
		}
		if (this.fapLevel != fapLevel) {
			errorCode += 8;
//			System.out.println("fapLevel failed");
		}
		if (this.maxTransferSize != maxTransferSize) {
			errorCode += 4;
//			System.out.println("maxTransferSize failed");
		}
		if (this.ccisd != ccisd) {
			errorCode += 2;
//			System.out.println("ccisd failed");
		}
		if (false) {
			errorCode += 1;
//			System.out.println("ccisd failed");
		}

		return errorCode;
	}

	public int getEFLLength() {
		return eFLLength;
	}

	public void setEFLLength(int eFLLength) {
		this.eFLLength = eFLLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getCapFlag3() {
		return capFlag3;
	}

	public void setCapFlag3(int captFlag3) {
		this.capFlag3 = captFlag3;
	}

	public int getECapFlag3() {
		return eCapFlag3;
	}

	public void setECapFlag3(int eCapFalg3) {
		this.eCapFlag3 = eCapFalg3;
	}

}
