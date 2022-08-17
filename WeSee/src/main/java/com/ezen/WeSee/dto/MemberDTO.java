package com.ezen.WeSee.dto;

public class MemberDTO {
	private int membernum;			//등록번호
	private String birth;			//생년월일(yyyy-mm-dd)
	private String rate;			//관람제한가(현재 연도에서 생년 빼서 계산)
	private String name;			//이름
	private String hp1;				//전화번호
	private String hp2;
	private String hp3;
	private String email;			//이메일. 로그인용 아이디 역할도 함.
	private String passwd;			//비밀번호
	private String address;			//집주소. null가능
	private String memberimage;		//등록 이미지. 등록하지 않으면 기본값으로
	private String wish;			//찜 영상 목록
	private String watch;			//본 영상 목록
	private int fe;					//가입한 요금제. 기본은 0
	private String orderlist;		//주문내역
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
