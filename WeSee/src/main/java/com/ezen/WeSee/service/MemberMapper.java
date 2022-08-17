package com.ezen.WeSee.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.WeSee.dto.MemberDTO;

@Service
public class MemberMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<MemberDTO> listMember(int start, int end){
		Map<String, String> values = new Hashtable<String, String>();
		values.put("start", String.valueOf(start));
		values.put("end", String.valueOf(end));
		List<MemberDTO> list = sqlSession.selectList("listMember", values);
		return list;
	}
	public int deleteMember(int membernum) {
		int res = sqlSession.delete("deleteMember", membernum);
		return res;
	}
	public List<MemberDTO> findMember(String name, int start, int end){
		Map<String, String> values = new Hashtable<String, String>();
		values.put("start", String.valueOf(start));
		values.put("end", String.valueOf(end));
		name = "%"+name+"%";
		values.put("name", name);
		List<MemberDTO> find = sqlSession.selectList("findMember", values);
		return find;
	}
	public int getMemberlistCount() {
		int count = sqlSession.selectOne("getMemberlistCount");
		return count;
	}
}
