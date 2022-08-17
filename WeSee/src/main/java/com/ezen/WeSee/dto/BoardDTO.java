package com.ezen.WeSee.dto;

public class BoardDTO {
	private int boardnum;		//등록번호
	private int star;			//별점. 선택한 별점과 숫자를 대응시킴
	private String boardtitle;	//게시글 제목
	private String boardname;	//작성자 이름
	private String writedate;	//작성일. sysdate도 괜찮고, 시간까지 넣어도 좋음. 추후 협의 필요.
	private String boardcontents;//글 내용
	private int readcount;		//조회수
	private int good;			//추천수
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
