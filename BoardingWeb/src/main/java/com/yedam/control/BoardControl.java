package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		request.setAttribute("board", board);
		
		SearchDTO search = new SearchDTO();
		search.setPage(Integer.parseInt(page));
		int totalCnt = svc.getTotalCnt(search);
		PageDTO paging = new PageDTO(Integer.parseInt(page), totalCnt);
		request.setAttribute("paging", paging);
		RequestDispatcher rd = request.getRequestDispatcher("board/board.tiles");
		rd.forward(request, response);
	}

}
