package com.ezen.WeSee.dto;

public class BoardDTO {
	private int boardnum;		//��Ϲ�ȣ
	private int star;			//����. ������ ������ ���ڸ� ������Ŵ
	private String boardtitle;	//�Խñ� ����
	private String boardname;	//�ۼ��� �̸�
	private String writedate;	//�ۼ���. sysdate�� ������, �ð����� �־ ����. ���� ���� �ʿ�.
	private String boardcontents;//�� ����
	private int readcount;		//��ȸ��
	private int good;			//��õ��
	private String titlecate;
	
	public String getTitlecate() {
		return titlecate;
	}
	public void setTitlecate(String titlecate) {
		this.titlecate = titlecate;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getBoardcontents() {
		return boardcontents;
	}
	public void setBoardcontents(String boardcontents) {
		this.boardcontents = boardcontents;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
}
