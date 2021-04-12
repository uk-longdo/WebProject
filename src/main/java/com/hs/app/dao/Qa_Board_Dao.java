package com.hs.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.app.dto.Qa_Board_Dto;
import com.hs.app.dto.Review_Dto;
import com.hs.app.dto.User_Dto;

@Repository
public class Qa_Board_Dao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Qa_Board_Dto> qa_list(Map<String, Object> map) {
		
		return sqlSession.selectList("com.hs.qa_list", map);
	}
	
	public Qa_Board_Dto qa_read(int idx) {
		
		   return sqlSession.selectOne("com.hs.qa_read", idx);
	}
	
	public int board_insert(Map<String, Object> map) {
	
		return sqlSession.insert("com.hs.board_insert", map);
	}
	
	//dao에서 두개 다 처리해야함
	public int qa_rewriter(Map<String, Object> map) { 
		
		return sqlSession.update("com.hs.qa_rewriter", map);
	}
	//답글 인서트
	public int qa_rewriter_insert(Map<String, Object> map) {
		
		return sqlSession.insert("com.hs.qa_rewriter_insert", map);
	}
	public int qa_update( Map<String, Object> map) {
		
		return sqlSession.update("com.hs.qa_update", map);
	}
	
	public int qa_delete(Map<String, Object> map) {
		
		return sqlSession.delete("com.hs.qa_delete", map);
	}
	
	public int qaTotalCount(){
		return sqlSession.selectOne("com.hs.qaTotalCount");
		
	}
	
}
