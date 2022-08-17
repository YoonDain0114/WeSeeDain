package com.ezen.WeSee.dto;

public class CSBoardDTO {
	private int csboardnum;      //등록번호
	private String csoption;   //문의글 옵션(요금제, 굿즈구매, 영상관련 등등)
	private String csboardtitle;//문의글제목
	private String csboardname; //작성자
	private String cswritedate;   //작성일. 게시글과 동일.
	private String csboardcontents;//글내용
	private String csimage;      //첨부이미지. null가능
	private int secret;  
   //비밀글 여부. 기본값은 0, 1이면 비밀글처리
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
