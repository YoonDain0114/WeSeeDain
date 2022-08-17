package com.ezen.WeSee.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.WeSee.dto.GoodsDTO;
import com.ezen.WeSee.dto.OrderlistDTO;

@Service
public class GoodsMapper {
	
	private Hashtable<String,List<GoodsDTO>> ht = new Hashtable<String,List<GoodsDTO>>();
	
	@Autowired
	private SqlSession sqlSession;
	
	public GoodsDTO getGoods(int gnum) {
		GoodsDTO dto = sqlSession.selectOne("getGoods", gnum);
		return dto;
	}
	
	public int insertOrderlist(Map<String, String> values) {
		int tmp = sqlSession.insert("insertOrderlist", values);
		if(tmp > 0) {
			int res = sqlSession.selectOne("getOrderNum", values);
			return res;
		}
		return 0;
	}
	
	public List<OrderlistDTO> getOrderlist(int ordermember){
		return sqlSession.selectList("getOrderlist", ordermember);
	}
	
	public int insertOrdercode(String ordercode, int ordernum) {
		Map<String, String> values = new Hashtable<String, String>();
		values.put("ordercode", ordercode);
		values.put("ordernum", String.valueOf(ordernum));
		return sqlSession.update("insertOrdercode", values);
	}
	
	
	
	
	//�� : ������ || �Ʒ� : �̾Ƹ�
	
	
	
	public List<GoodsDTO> cateList(String gcategory){
		List<GoodsDTO> list = sqlSession.selectList("cateList", gcategory);
		return list;
	}
	
	
	
	//�� : �̾Ƹ� || �Ʒ� : ������
	
	
	
	public List<GoodsDTO> selectByspec(String gspec){
		List<GoodsDTO> glist = sqlSession.selectList("selectBySpec",gspec);
		ht.put(gspec,glist);
		return glist;
	}
	
	public List<GoodsDTO> findByName(String search){
		java.util.Map<String,String> map = new java.util.Hashtable<String,String>();
		map.put("search",search);
		List<GoodsDTO> find = sqlSession.selectList("findByName",map);
		return find;
	}
	
	
	public GoodsDTO getGoods(int gnum, String gspec) {
		List<GoodsDTO> glist = ht.get(gspec);
		for(GoodsDTO gdto: glist) {
			if(gdto.getGnum()==gnum) {
				return gdto;
			}
		}return null;
	}
	
	public GoodsDTO sawGoods(int gnum){
		List<GoodsDTO> glist = ht.get(gnum);
		for(GoodsDTO gdto:glist) {
			if(gdto.getGnum()==gnum) {
				return gdto;
			}
		}return null;
	}
	
	public int deleteMemberOrder(int ordermember) {
		int res = sqlSession.delete("deleteMemberOrder",ordermember);
		return res;
	}
	
	public int M_updategqty(int gnum,int gqty) {
	      Map<String, String> value = new Hashtable<>();
	      value.put("gnum", String.valueOf(gnum));
	      value.put("gqty", String.valueOf(gqty));
	      int res = sqlSession.update("M_updategqty",value);
	      return res;
	   }

	
	
	
	
	//�� : ������ || �Ʒ� : �赿��
	
	
	
	public int updategoodsWish(int membernum, String goodswish) {
		Map<String, String> values = new Hashtable<>();
		values.put("membernum", String.valueOf(membernum));
		values.put("goodswish", goodswish);
		int res = sqlSession.update("updategoodsWish", values);
		return res;
	}
	
	
	
	
	//�� : �赿�� || �Ʒ� : �̽���
	
	
	
	

	public OrderlistDTO getOrder(int ordernum) {
		OrderlistDTO dto = sqlSession.selectOne("getOrder",ordernum);
		return dto;
	}

}
