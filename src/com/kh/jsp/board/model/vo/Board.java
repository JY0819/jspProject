package com.kh.jsp.board.model.vo;

import java.sql.Date;

public class Board implements java.io.Serializable{
	private int bid;
	private int bType;
	private int bno;
	// join하려고 String - category
	private String category;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private int bCount;
	private int refBid;
	private int replyLevel;
	private Date bDate;
	private Date modifyDate;
	private String status;

	public Board() {
	}

	public Board(int bid, int bType, int bno, String category, String bTitle, String bContent, String bWriter,
			int bCount, int refBid, int replyLevel, Date bDate, Date modifyDate, String status) {
		super();
		this.bid = bid;
		this.bType = bType;
		this.bno = bno;
		this.category = category;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bCount = bCount;
		this.refBid = refBid;
		this.replyLevel = replyLevel;
		this.bDate = bDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getbType() {
		return bType;
	}

	public void setbType(int bType) {
		this.bType = bType;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public int getRefBid() {
		return refBid;
	}

	public void setRefBid(int refBid) {
		this.refBid = refBid;
	}

	public int getReplyLevel() {
		return replyLevel;
	}

	public void setReplyLevel(int replyLevel) {
		this.replyLevel = replyLevel;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", bType=" + bType + ", bno=" + bno + ", category=" + category + ", bTitle="
				+ bTitle + ", bContent=" + bContent + ", bWriter=" + bWriter + ", bCount=" + bCount + ", refBid="
				+ refBid + ", replyLevel=" + replyLevel + ", bDate=" + bDate + ", modifyDate=" + modifyDate
				+ ", status=" + status + "]";
	}

}
