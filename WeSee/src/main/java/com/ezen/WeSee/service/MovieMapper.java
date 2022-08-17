package com.ezen.WeSee.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.WeSee.dto.MemberDTO;
import com.ezen.WeSee.dto.MovieDTO;

@Service
public class MovieMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int updateMember(MemberDTO dto) {
		int res = sqlSession.update("updateMember", dto);
		return res;
	}
	
	public MovieDTO getMovie(int movienum) {
		return sqlSession.selectOne("getMovie", movienum);
	}
	
	public int updateFe(MemberDTO dto) {
		int res = sqlSession.update("updateFe", dto);
		return res;
	}
	
	public int updateOnlyFe(MemberDTO dto) {
		int res = sqlSession.update("updateFe", dto);
		return res;
	}
	
	public int stopFe(MemberDTO dto) {
		int res = sqlSession.update("stopFe", dto);
		return res;
	}
	
	public int paydateOver(MemberDTO dto) {
		int res = sqlSession.update("paydateOver", dto);
		return res;
	}
	
	public int paydateRenew(MemberDTO dto) {
		int res = sqlSession.update("paydateRenew", dto);
		return res;
	}
	
	public int deleteMember(int membernum) {
		int res = sqlSession.update("deleteMember", membernum);
		return res;
	}
	
	public int updateWish(int membernum, String wish) {
		Map<String, String> values = new Hashtable<>();
		values.put("membernum", String.valueOf(membernum));
		values.put("wish", wish);
		int res = sqlSession.update("updateWish", values);
		return res;
	}
	
	public int updateWatch(int membernum, String watch) {
		Map<String, String> values = new Hashtable<>();
		values.put("membernum", String.valueOf(membernum));
		values.put("watch", watch);
		int res = sqlSession.update("updateWatch", values);
		return res;
	}
	
	public int plusViewcount(int movienum) {
		int res = sqlSession.update("plusViewcount", movienum);
		return res;
	}
	
	
	
	//위 : 김형준 || 아래 : 이아린
	
	
	
	
	
	public List<MovieDTO> findMovieList(String title){
		title = "%" + title + "%";
		return sqlSession.selectList("findMovieList", title);
	}
	
	public List<MovieDTO> recMovie(){
		List<MovieDTO> list = sqlSession.selectList("recMovie");
		return list;
	}

	public List<MovieDTO> yearlyMovie(){
		List<MovieDTO> list = sqlSession.selectList("yearlyMovie");
		return list;
	}
	
	
	
	//위 : 이아린 || 아래 : 김민
	
	
	
	public List<MovieDTO> listMovie(){
		List<MovieDTO> list = sqlSession.selectList("listMovie");
		return list;
	}/*
	public MovieDTO getMovie (int movienum) {
		MovieDTO dto = sqlSession.selectOne("getMovie", movienum);
		return dto;	
	}*/
	public List<MovieDTO> selectByGenre(String genre){
	    genre = "%"+genre+"%";
		return sqlSession.selectList("selectByGenre", genre);
    }
	public List<MovieDTO> searchgenre(String genre) {
		List<MovieDTO> list = sqlSession.selectList("searchgenre", genre);
		return list;
	}
	public List<MovieDTO> searchtitle(String title) {
		List<MovieDTO> list = sqlSession.selectList("searchtitle", title);
		return list;
	}
	
	
	
	//위 : 김민 || 아래 : 김동완
	
	
	
	//리스트에 장르 넣기
	public List<MovieDTO> sameGenre(String genre){
		genre = "%" + genre + "%";
		return sqlSession.selectList("sameGenre", genre);
	}
}
