package com.ezen.WeSee.dto;

public class GoodsDTO {
	private int gnum;		//등록번호
	private String gname;	//굿즈이름
	private String gimage;	//굿즈 이미지
	private String gimage2;
	private String gimage3;
	private String gimage4;
	private String gimage5;
	private int gprice;		//굿즈 가격
	private int gqty;		//굿즈 재고수량
	private int gmovie;//관련영화. 외래키 참조를 불러와서 영화 등록번호와 영화제목만 입력해서 사용.
	private String gcategory;//굿즈 카테고리
	private String gspec;	//스펙(new, best 등등)
	private String gcontents;//굿즈 소개글
	
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGimage() {
		return gimage;
	}
	public void setGimage(String gimage) {
		this.gimage = gimage;
	}
	public int getGprice() {
		return gprice;
	}
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}
	public int getGqty() {
		return gqty;
	}
	public void setGqty(int gqty) {
		this.gqty = gqty;
	}
	public int getGmovie() {
		return gmovie;
	}
	public void setGmovie(int gmovie) {
		this.gmovie = gmovie;
	}
	public String getGcategory() {
		return gcategory;
	}
	public void setGcategory(String gcategory) {
		this.gcategory = gcategory;
	}
	public String getGspec() {
		return gspec;
	}
	public void setGspec(String gspec) {
		this.gspec = gspec;
	}
	public String getGcontents() {
		return gcontents;
	}
	public void setGcontents(String gcontents) {
		this.gcontents = gcontents;
	}
	public String getGimage2() {
		return gimage2;
	}
	public void setGimage2(String gimage2) {
		this.gimage2 = gimage2;
	}
	public String getGimage3() {
		return gimage3;
	}
	public void setGimage3(String gimage3) {
		this.gimage3 = gimage3;
	}
	public String getGimage4() {
		return gimage4;
	}
	public void setGimage4(String gimage4) {
		this.gimage4 = gimage4;
	}
	public String getGimage5() {
		return gimage5;
	}
	public void setGimage5(String gimage5) {
		this.gimage5 = gimage5;
	}
	
}
