package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

/*
 * 목록, 추가, 수정, 삭제 단건
 * */
public interface BoardMapper {
	List<BoardVO> selectList();
	List<BoardVO> selectListPaging(SearchDTO search);
	int insertBoard(BoardVO board);
	int updateBoard(BoardVO board);
	int deleteBoard(int boardNo);
	BoardVO selectBoard(int boardNo);
	
	//페이징 계산
	int selectCount(SearchDTO search);
}
