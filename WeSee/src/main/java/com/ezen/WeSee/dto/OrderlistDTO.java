package com.ezen.WeSee.dto;

public class OrderlistDTO {
	private int ordernum;		//��Ϲ�ȣ
	private int ordermember;	//�ֹ��� ȸ��.
	private String ordergoods;	//�ֹ��� ����. �����ȣ �޾Ƽ� _�� ���̿� �ְ� �Է�.
	private int stage;			//�ֹ��ܰ�. (���������, �����Ϸ�, ����� ���)
	private String memo;		//��۸޸�. �ù���Բ� ���� �� ��
	private String payment;		//�ֹ����. ī�����, �޴������� ��
	private int totalpay;		//�� ���űݾ�
	private String orderdate;	//�ֹ�����
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
