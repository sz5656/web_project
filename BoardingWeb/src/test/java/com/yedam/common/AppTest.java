package com.yedam.common;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.ReplyMapper;

public class AppTest {

	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		
		String[] arg = {"12", "13", "14","15"};
		
		mapper.deleteReplys(arg);
		
	}

}
