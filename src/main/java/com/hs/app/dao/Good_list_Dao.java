package com.hs.app.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Good_list_Dao {
	
	@Autowired
	private SqlSession sqlSession;

	public int goodup(int review_idx) {
		return sqlSession.update("com.hs.goodup", review_idx);
	}
	
	public int goods(Map<String, Object> map) {
		return sqlSession.insert("com.hs.goods", map);
	}
	
	public int goodcnt(Map<String, Object> map) {
		return sqlSession.selectOne("com.hs.goodcnt", map);
	}
	
	public int gooddown(Map<String, Object> map) {
		return sqlSession.delete("com.hs.gooddown", map);
	}
	
	
}
