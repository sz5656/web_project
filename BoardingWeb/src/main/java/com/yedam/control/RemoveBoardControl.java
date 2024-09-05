package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		// 로그인 정보.
		HttpSession session = request.getSession();
		String logid = (String) session.getAttribute("logid");
		
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		
		//로그인 정보가 없거나 작성자와 아이디가 같지 않을 경우
		if(logid==null || !logid.equals(board.getWriter())) {
			request.setAttribute("message", "권한이 없어 사용할 수 없습니다");
			request.setAttribute("board", board);
			request.setAttribute("page", page);
			request.getRequestDispatcher("WEB-INF/board/board.jsp").forward(request, response);
			return;
		}
		
		// 게시글 삭제
		if(svc.removeBoard(Integer.parseInt(bno))) {
			System.out.println(page);
			response.sendRedirect("boardList.do?page=" + page + "&searchCondition=" + sc + "&keyword=" + kw);
		}
	}

}
