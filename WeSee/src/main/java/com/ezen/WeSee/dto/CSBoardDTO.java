package com.ezen.WeSee.dto;

public class CSBoardDTO {
	private int csboardnum;      //��Ϲ�ȣ
	private String csoption;   //���Ǳ� �ɼ�(�����, �����, ������� ���)
	private String csboardtitle;//���Ǳ�����
	private String csboardname; //�ۼ���
	private String cswritedate;   //�ۼ���. �Խñ۰� ����.
	private String csboardcontents;//�۳���
	private String csimage;      //÷���̹���. null����
	private int secret;  
   //��б� ����. �⺻���� 0, 1�̸� ��б�ó��
	private int recomment;
	
	
	

	public int getRecomment() {
		return recomment;
	}
	public void setRecomment(int recomment) {
		this.recomment = recomment;
	}
	public int getCsboardnum() {
		return csboardnum;
	}
	public void setCsboardnum(int csboardnum) {
		this.csboardnum = csboardnum;
	}
	public String getCsoption() {
		return csoption;
	}
	public void setCsoption(String csoption) {
		this.csoption = csoption;
	}
	public String getCsboardtitle() {
		return csboardtitle;
	}
	public void setCsboardtitle(String csboardtitle) {
		this.csboardtitle = csboardtitle;
	}
	public String getCsboardname() {
		return csboardname;
	}
	public void setCsboardname(String csboardname) {
		this.csboardname = csboardname;
	}
	public String getCswritedate() {
		return cswritedate;
	}
	public void setCswritedate(String cswritedate) {
		this.cswritedate = cswritedate;
	}
	public String getCsboardcontents() {
		return csboardcontents;
	}
	public void setCsboardcontents(String csboardcontents) {
		this.csboardcontents = csboardcontents;
	}
	public String getCsimage() {
		return csimage;
	}
	public void setCsimage(String csimage) {
		this.csimage = csimage;
	}
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
}
