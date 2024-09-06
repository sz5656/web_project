package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.compiler.ast.Keyword;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		page = page == null ? "1" : page; // 페이지 값이 없을 경우 1페이지 이동
		
		// 검색 조건 파라미터
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		if(sc == null || kw == null || sc.isEmpty() || kw.isEmpty()) { // 검색조건이 NULL이면
			request.setAttribute("message", "검색조건을 입력하세요");
		} else {
		
			SearchDTO search = new SearchDTO(); // 검색조건 지정
			search.setSearchCondition(sc);
			search.setKeyword(kw);
			search.setPage(Integer.parseInt(page));
			request.setAttribute("search", search);
			
			BoardService svc = new BoardServiceImpl();
			List<BoardVO> list = svc.boardList(search);
			request.setAttribute("list", list);
			
			//페이징 처리를 위한 기능.
			int totalCnt = svc.getTotalCnt(search);
			PageDTO paging = new PageDTO(Integer.parseInt(page), totalCnt);
			request.setAttribute("paging", paging);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("board/boardList.tiles");
		rd.forward(request, response);
	}

}
