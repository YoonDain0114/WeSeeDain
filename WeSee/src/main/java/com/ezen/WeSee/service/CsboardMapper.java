package com.ezen.WeSee.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.WeSee.dto.CSBoardDTO;
import com.ezen.WeSee.dto.CommentDTO;
import com.ezen.WeSee.dto.MemberDTO;

@Service
public class CsboardMapper {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CSBoardDTO> listCsBoard(int start, int end){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("start",String.valueOf((start)));
		map.put("end", String.valueOf(end));
		List<CSBoardDTO> list =sqlSession.selectList("listCsBoard",map);
		return list;
	}
	
	public int insertCsBoard(CSBoardDTO dto) {
		int res = sqlSession.insert("insertCsBoard",dto);
		return res;
	}
	
	public CSBoardDTO getCsBoard(int csboardnum) {
		CSBoardDTO dto =sqlSession.selectOne("getCsBoard", csboardnum);
		return dto;
	}
	
	public MemberDTO getMembername(String name) {
		MemberDTO dto = sqlSession.selectOne("getMembername", name);
		return dto;
	}
	
	public int deleteCsBoard(int csboardnum) {
		return sqlSession.delete("deleteCsBoard",csboardnum);
	}
	
	public List<CSBoardDTO> searchCsBoard(String search, String searchString ){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("search",search);
		map.put("searchString", searchString);
		List<CSBoardDTO> search1 = sqlSession.selectList("searchCsBoard",map);
		return search1;
	}
	
	public int getCsBoardCount() {
		int count = sqlSession.selectOne("getCsBoardCount");
		return count;
	}
	//추가한 부분
	
	//댓글추가
	public int insertCommentCsBoard(CommentDTO dto) {
		int res =sqlSession.insert("insertCommentCsBoard",dto);
		return res;
	}
	//댓글리스트 불러오기
	public List<CommentDTO> listCommentCsBoard(int bno){
		List<CommentDTO> list =sqlSession.selectList("listCommentCsBoard",bno);
		return list;
	}
	//댓글 삭제
	public int deleteCommentCsBoard(int cno) {
		return sqlSession.delete("deleteCommentCsBoard",cno);
	}
	
	public CommentDTO getCommentCsBoard(int cno) {
		CommentDTO dto =sqlSession.selectOne("getCommentCsBoard",cno);
		return dto;
	}
	
	public int updateComment(int csboardnum) {
		int res = sqlSession.update("updateComment", csboardnum);
		return res;
	}
	public int MupdateComment(int csboardnum) {
		int res = sqlSession.update("MupdateComment", csboardnum);
		return res;
	}
	
	public int deleteAllComment(int csboardnum) {
		int res = sqlSession.delete("deleteAllComment",csboardnum);
		return res;
	}
}
