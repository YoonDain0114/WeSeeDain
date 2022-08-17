package com.ezen.WeSee.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.WeSee.dto.*;

@Service
public class BoardMapper {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> listBoard(int start, int end){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("start",String.valueOf((start)));
		map.put("end", String.valueOf(end));
		List<BoardDTO> list = sqlSession.selectList("listBoard",map);
		return list;
	}
	
	public List<BoardDTO> findBoard(String boardtitle) {
		boardtitle = '%'+boardtitle+'%';
		List<BoardDTO> find = sqlSession.selectList("findBoard", boardtitle);
		return find; 
	} 
	public int getBoardCount() {
		
		int count = sqlSession.selectOne("getBoardCount");
		
		return count;
	}
		
	public BoardDTO getBoard(int boardnum, String mode) {
		if (mode.equals("boardcontent")) {
			sqlSession.update("plusReadcount", boardnum);
		}else if(mode.equals("plusgood")){
			sqlSession.update("plusGood", boardnum);
		}else if(mode.equals("minusgood")){
			sqlSession.update("minusGood", boardnum);
		}
		BoardDTO dto = sqlSession.selectOne("getBoard", boardnum);
		return dto;
	}
	
	public int insertBoard(BoardDTO dto) {
		int res = sqlSession.insert("insertBoard", dto);
		return res;
	}

	public int deleteBoard(int boardnum) {
		int res = sqlSession.delete("deleteBoard", boardnum);
		return res;
	}

	
	public int updateBoard(BoardDTO dto) {
		int res = sqlSession.update("updateBoard", dto);
		return res;
	}
	
	public List<MovieDTO> getTitle() {
		return sqlSession.selectList("getTitle");
	}

}
