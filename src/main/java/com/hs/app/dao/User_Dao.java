package com.hs.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hs.app.dto.Dress_list_Dto;
import com.hs.app.dto.User_Dto;

@Repository
public class User_Dao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<User_Dto> user_list(Map<String, Object> map) {
		return sqlSession.selectList("com.hs.user_list", map);
	}
	
	
	public int insertlogin(Map<String, Object> map) {
		return sqlSession.insert("com.hs.insertlogin", map);
	}
	
	public int logindo(Map<String, Object> map){
	    return sqlSession.selectOne("com.hs.login", map);
	}  
	
	public int admintest(String user_id) {
		return sqlSession.selectOne("com.hs.admintest", user_id);
		
	}
	public int log_upchk(String user_id) {
	      
	      return sqlSession.selectOne("com.hs.log_upchk", user_id);
	   }


	public int userTotalCount() {
		
		return sqlSession.selectOne("com.hs.userTotalCount");
	}
	
	
	public int userdelete(Map<String, Object> map) {
		
		return sqlSession.delete("com.hs.userdelete", map);
		
	}


	
}
