package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardServiceImpl implements BoardService{
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	@Override
	public List<BoardVO> boardList(SearchDTO search) {
//		return mapper.selectListPaging(search);
		return mapper.selectList();
	}

	@Override
	public boolean addBoard(BoardVO board) {
		return mapper.insertBoard(board)==1;
	}

	@Override
	public boolean modifyBoard(BoardVO board) {
		return mapper.updateBoard(board)==1;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		return mapper.deleteBoard(boardNo)==1;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		return mapper.selectBoard(boardNo);
	}

	@Override
	public int getTotalCnt(SearchDTO search) {
		return mapper.selectCount(search);
	}

}
