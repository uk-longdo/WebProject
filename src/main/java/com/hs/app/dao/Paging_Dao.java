package com.hs.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.app.dto.Dress_list_Dto;

@Repository
public class Paging_Dao {

	@Autowired
	private SqlSession sqlSession;
	
	public int getTotalCount(){
		return sqlSession.selectOne("com.hs.getTotalCount");
		
	}
	public List<Dress_list_Dto> getAll(Map<String, Object> map ){
		return sqlSession.selectList("com.hs.getAll",map);
		
	}
	public int outergetTotalCount(){
		return sqlSession.selectOne("com.hs.outergetTotalCount");
		
	}
	public List<Dress_list_Dto> getOuter(Map<String, Object> map ){
		return sqlSession.selectList("com.hs.getOuter",map);
		
	}
	public int topgetTotalCount(){
		return sqlSession.selectOne("com.hs.topgetTotalCount");
		
	}
	public List<Dress_list_Dto> getTop(Map<String, Object> map ){
		return sqlSession.selectList("com.hs.getTop",map);
		
	}
	public int pantsgetTotalCount(){
		return sqlSession.selectOne("com.hs.pantsgetTotalCount");
		
	}
	public List<Dress_list_Dto> getPants(Map<String, Object> map ){
		return sqlSession.selectList("com.hs.getPants",map);
		
	}
	
}
