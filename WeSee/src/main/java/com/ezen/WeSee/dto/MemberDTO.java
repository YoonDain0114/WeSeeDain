package com.ezen.WeSee.dto;

public class MemberDTO {
	private int membernum;			//��Ϲ�ȣ
	private String birth;			//�������(yyyy-mm-dd)
	private String rate;			//�������Ѱ�(���� �������� ���� ���� ���)
	private String name;			//�̸�
	private String hp1;				//��ȭ��ȣ
	private String hp2;
	private String hp3;
	private String email;			//�̸���. �α��ο� ���̵� ���ҵ� ��.
	private String passwd;			//��й�ȣ
	private String address;			//���ּ�. null����
	private String memberimage;		//��� �̹���. ������� ������ �⺻������
	private String wish;			//�� ���� ���
	private String watch;			//�� ���� ���
	private int fe;					//������ �����. �⺻�� 0
	private String orderlist;		//�ֹ�����
	private String goodswish;
	private int autopay;
	private String paydate;
	
	public int getMembernum() {
		return membernum;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String getAllhp() {
		return hp1+"-"+hp2+"-"+hp3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemberimage() {
		return memberimage;
	}
	public void setMemberimage(String memberimage) {
		this.memberimage = memberimage;
	}
	public String getWish() {
		return wish;
	}
	public void setWish(String wish) {
		this.wish = wish;
	}
	public String getWatch() {
		return watch;
	}
	public void setWatch(String watch) {
		this.watch = watch;
	}
	public int getFe() {
		return fe;
	}
	public void setFe(int fe) {
		this.fe = fe;
	}
	public String getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(String orderlist) {
		this.orderlist = orderlist;
	}
	public String getGoodsWish() {
		return goodswish;
	}
	public void setGoodsWish(String goodswish) {
		this.goodswish = goodswish;
	}
	public String getGoodswish() {
		return goodswish;
	}
	public void setGoodswish(String goodswish) {
		this.goodswish = goodswish;
	}
	public int getAutopay() {
		return autopay;
	}
	public void setAutopay(int autopay) {
		this.autopay = autopay;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	
}
