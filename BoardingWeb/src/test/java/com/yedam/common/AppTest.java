package com.yedam.common;

import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AppTest {

	public static void main(String[] args) {
		BoardVO board = new BoardVO();
		board.setBoardNo(3);
		
		BoardService svc = new BoardServiceImpl();
		svc.removeBoard(board.getBoardNo());
		
	}

}
