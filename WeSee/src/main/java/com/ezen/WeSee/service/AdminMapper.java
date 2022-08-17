package com.ezen.WeSee.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.WeSee.dto.*;

@Service
public class AdminMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int IdPwCheck(String email, String passwd) {
		List<MemberDTO> list = sqlSession.selectList("IdPwCheck", email);
		
		if(list == null || list.size() == 0) return -1;	//-1은 일치하는 이메일이 없는 경우
		else {
			if(email.equals("admin"/*"관리자계정명"*/) && passwd.equals("admin"/*"관리자계정비밀번호"*/)) {
				return 0;
			}else if(list.get(0).getPasswd().equals(passwd)) return list.get(0).getMembernum();
			//이메일과 비밀번호가 일치시 해당 멤버의 회원번호를 반환
			else return -2;	//-2는 이메일은 있으나 비밀번호가 일치하지 않을 경우.
		}
	}
	
	public int newMember(MemberDTO dto) {
		return sqlSession.update("newMember", dto);
	}
	
	public MemberDTO getMember(int membernum) {
		MemberDTO dto = sqlSession.selectOne("getMember", membernum);
		return dto;
	}
	
	public MemberDTO searchMember(MemberDTO dto) {
		MemberDTO mdto = null;
		if(dto.getEmail() == null || dto.getEmail().equals("")) {
			mdto = sqlSession.selectOne("searchMemberEmail", dto); 
		}else {
			mdto = sqlSession.selectOne("searchMemberPasswd", dto);
		}
		return mdto;
	}
	
	public int CheckEmail(String email) {
		List<MemberDTO> list = sqlSession.selectList("checkEmail", email);
		if(list == null || list.size() == 0) return 0;
		else return 1;
	}
	
	public int ChangePasswd(String passwd, String email) {
		Map<String, String> values = new Hashtable<>();
		values.put("passwd", passwd);
		values.put("email", email);
		return sqlSession.update("changePasswd", values);
	}
	
	
	//위 : 김형준 || 아래 : 김한슬
	
	
		
	//관리자 영상 리스트 (listMovie로 부르기)
	public List<MovieDTO> AdminMovieList(int start, int end){
		Map<String, String> values = new Hashtable<String, String>();
		values.put("start", String.valueOf(start));
		values.put("end", String.valueOf(end));
		List<MovieDTO> list = sqlSession.selectList("listAdmin", values);
		return list;
	}
	public List<MovieDTO> MovieList(){
		List<MovieDTO> list = sqlSession.selectList("listMovie");
		return list;
	}
	
	//관리자 등록된 영상 찾기(찾을 영상 title을 findMovie로 부르기)
	public List<MovieDTO> findMovie(String title, int start, int end){
		Map<String, String> values = new Hashtable<String, String>();
		values.put("start", String.valueOf(start));
		values.put("end", String.valueOf(end));
		//title = "%"+title+"%";
		values.put("title", title);
		List<MovieDTO> find = sqlSession.selectList("findMovie",values);
		return find;
	}
	
	public int getAdminlistCount(String title) {
		Map<String, String> values = new Hashtable<String, String>();
		values.put("title", title);
		int count = sqlSession.selectOne("getAdminlistCount", values);
		return count;
	}
	
	//관리자 영상 입력 (dto에 넣어준 값을 insertMovie로 부르기)
	public int insertMovie(MovieDTO dto) {
		int res = sqlSession.insert("insertMovie" , dto);
		return res;
	}
	
	//관리자 영상입력 받은(movienum값 dto에 넣어준 값을 getmovie로 부르기)
	public MovieDTO getMovie(int movienum) {
		MovieDTO dto = sqlSession.selectOne("getMovie", movienum);
		return dto;
	}
	
	//관리자 영상 삭제 (입력받고 삭제할  movienum값을 deleteMovie로 부르기)
	public int deleteMovie(int movienum) {
		int res = sqlSession.delete("deleteMovie", movienum);
		return res;
	}
			
	//관리자 영상 수정(수정할 dto값을 updateMovie로 부르기)
	public int updateMovie(MovieDTO dto) {
		int res = sqlSession.update("updateMovie", dto);
		return res;
	}
	
	public List<GoodsDTO> listGoodsPage(int start, int end){
		Map<String, String> values = new Hashtable<String, String>();
		values.put("start", String.valueOf(start));
		values.put("end", String.valueOf(end));
		List<GoodsDTO> list = sqlSession.selectList("listGoodsPage", values);
		return list;
	}
	
	public List<GoodsDTO> adminFindGoods(String gname, int start, int end) {
		Map<String, String> values = new Hashtable<String, String>();
		values.put("start", String.valueOf(start));
		values.put("end", String.valueOf(end));
		//gname = "%" + gname + "%";
		values.put("gname", gname);
		return sqlSession.selectList("adminFindGoods", values);
	}
	
	public int getGoodslistCount(String gname) {
		Map<String, String> values = new Hashtable<String, String>();
		values.put("gname", gname);
		int count = sqlSession.selectOne("getGoodslistCount", values);
		return count;
	}
	
	
	
	//위 : 김한슬 || 아래 : 이아린
	
	
	
	public List<GoodsDTO> listGoods(){
		List<GoodsDTO> list = sqlSession.selectList("listGoods");
		return list;
	}
	
	public int insertGoods(GoodsDTO dto) {
		int res = sqlSession.insert("insertGoods", dto);
		return res;
	}
	
	public GoodsDTO getAGoods(int gnum) {
		GoodsDTO dto = sqlSession.selectOne("getAGoods", gnum);
		return dto;
	}
	
	public int updateGoods(GoodsDTO dto) {
		int res = sqlSession.update("updateGoods", dto);
		return res;
	}
	
	public int deleteGoods(int gnum) {
		int res = sqlSession.delete("deleteGoods", gnum);
		return res;
	}
	
	public List<GoodsDTO> adminFindGoods(String gname) {
		gname = "%" + gname + "%";
		return sqlSession.selectList("adminFindGoods", gname);
	}
	
	
	
	//위 : 이아린 || 아래 : 윤다인
	
	
	
	
	// 총매출 리스트
	public List<OrderlistDTO> totalSalelist(int start, int end){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("start",String.valueOf((start)));
		map.put("end", String.valueOf(end));
		//map.put("searchString",searchString);
		List<OrderlistDTO> list =sqlSession.selectList("totalSalelist",map);
		return list;
	}
		
	//총매출 갯수
	public int totalSalelistCount() {
		int count = sqlSession.selectOne("totalSalelistCount");
		return count;
	}
		
	//총매출 총합
	public int totalSum() {
		int sum = sqlSession.selectOne("totalSum");
		return sum;
	}
		
	//년도별 매출 갯수
	public int yearSaleCount(String searchString) {
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("searchString", searchString);
		int sum = sqlSession.selectOne("yearSaleCount",map);
		return sum;
	}
	
	//년도 입력하고 리스트 출력
	public List<OrderlistDTO> searchYear(int start, int end,String searchString ){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("searchString", searchString);
		map.put("start",String.valueOf((start)));
		map.put("end", String.valueOf(end));
		List<OrderlistDTO> search1 = sqlSession.selectList("searchYear",map);
		return search1;
	}
	
	//년도별로 합계
	public Integer YearSum(String searchString) {
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("searchString", searchString);
		
		String Sum = sqlSession.selectOne("YearSum",map);
		int sum= 0;
		if(Sum != null) {
			sum=Integer.parseInt(Sum);
		}
		return sum;
	}
	
	
	
	//일매출 개수
	public int daySaleCount(String date) {
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("date", date);
		int sum = sqlSession.selectOne("daySaleCount",map);
		return sum;
	}
	
	//일 매출 리스트
	public List<OrderlistDTO> searchDay(int start, int end, String date){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("date", date);
		map.put("start",String.valueOf((start)));
		map.put("end", String.valueOf(end));
		List<OrderlistDTO> search1 = sqlSession.selectList("searchDay",map);
		return search1;
		
	}
	
	//일매출 총합
	public Integer DaySum(String date) {
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("date", date);
		String Sum = sqlSession.selectOne("DaySum",map);
		
		int sum=0;
		if(Sum != null) {
			sum=Integer.parseInt(Sum);
		}
		return sum;
	}
	
	//월매출 개수
	public int monthSaleCount(String month, String year) {
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("month", month);
		map.put("year", year);
		int sum = sqlSession.selectOne("monthSaleCount",map);
			return sum;
	}
				
	//월 매출 리스트
	public List<OrderlistDTO> searchMonth(int start, int end, String month,String year){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("month", month);
		map.put("year", year);
		map.put("start",String.valueOf((start)));
		map.put("end", String.valueOf(end));
		List<OrderlistDTO> search1 = sqlSession.selectList("searchMonth",map);
		return search1;
				
	}
			
	//월매출 총합
	public Integer MonthSum(String year,String month) {
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("year", year);
		map.put("month", month);
		
		String Sum = sqlSession.selectOne("MonthSum",map);
		
		int sum=0;
		if(Sum != null) {
			sum=Integer.parseInt(Sum);
		}
		return sum;
		
	}
		
}
