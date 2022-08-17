package com.ezen.WeSee.dto;

public class OrderlistDTO {
	private int ordernum;		//등록번호
	private int ordermember;	//주문한 회원.
	private String ordergoods;	//주문한 굿즈. 굿즈번호 받아서 _를 사이에 넣고 입력.
	private int stage;			//주문단계. (결제대기중, 결제완료, 배송중 등등)
	private String memo;		//배송메모. 택배기사님께 남길 말 등
	private String payment;		//주문방식. 카드결제, 휴대폰결제 등
	private int totalpay;		//총 구매금액
	private String orderdate;	//주문일자
	private String ordercode;
	private String orderaddress;
	
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public int getOrdermember() {
		return ordermember;
	}
	public void setOrdermember(int ordermember) {
		this.ordermember = ordermember;
	}
	public String getOrdergoods() {
		return ordergoods;
	}
	public void setOrdergoods(String ordergoods) {
		this.ordergoods = ordergoods;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getTotalpay() {
		return totalpay;
	}
	public void setTotalpay(int totalpay) {
		this.totalpay = totalpay;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getOrderaddress() {
		return orderaddress;
	}
	public void setOrderaddress(String orderaddress) {
		this.orderaddress = orderaddress;
	}
	
}
