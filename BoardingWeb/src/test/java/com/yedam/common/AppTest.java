package com.yedam.common;

import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;

public class AppTest {

	public static void main(String[] args) {
		SearchDTO search = new SearchDTO();
		search.setSearchCondition("TW");
		search.setKeyword("user01");
		search.setPage(2);
		BoardService svc = new BoardServiceImpl();
		svc.boardList(search).forEach(System.out::println);;
		
	}

}
