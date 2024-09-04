package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 4개 값을 읽어서 db 반영 -> 목록으로 이동
		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		if(svc.modifyBoard(board)) {
			response.sendRedirect("boardList.do");
		} else {
			request.setAttribute("message", "변경중 오류가 발생했습니다");
			request.getRequestDispatcher("WEB-INF/board/boardList.jsp").forward(request, response);
		}
	}

}
