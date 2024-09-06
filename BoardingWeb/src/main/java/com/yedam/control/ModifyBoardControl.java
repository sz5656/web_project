package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 4개 값을 읽어서 db 반영 -> 목록으로 이동
		int maxSize = 5 * 1024 * 1024; //5MB
		String saveDir = request.getServletContext().getRealPath("images");
		MultipartRequest mr;
		mr = new MultipartRequest(request // 1.요청
				,saveDir // 2.파일저장경로
				,maxSize // 3.최대파일크기
				,"utf-8" // 4.인코딩방식
				,new DefaultFileRenamePolicy()// 5.리네임정책
				);
		String bno = mr.getParameter("bno");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		String sc = mr.getParameter("searchCondition");
		String kw = mr.getParameter("keyword");
		String page = mr.getParameter("page");
		
		String img = mr.getFilesystemName("srcImg");
		System.out.printf("bno : %s title : %s content : %s sc : %s kw : %s page : %s img : %s",bno,title,content,sc,kw,page,img);
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		request.setAttribute("page", page);
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		board.setImage(img);
		request.setAttribute("board", board);
		
		if(svc.modifyBoard(board)) {
			System.out.println(board.toString());
			System.out.println("page : " + page);
			response.sendRedirect("boardList.do?page=" + page + "&searchCondition=" + sc + "&keyword=" + kw);
		} else {
			request.setAttribute("message", "변경중 오류가 발생했습니다");
			request.getRequestDispatcher("board/boardList.tiles").forward(request, response);
		}
	}

}
