package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;

public class RemoveMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberService msv = new MemberServiceImpl();
		
		if(msv.removeMember(id)) {
			response.sendRedirect("memberList.do");
		} else {
			request.setAttribute("message", "삭제처리가 올바르게 되지 않았습니다.");
			request.getRequestDispatcher("WEB-INF/html/memberList.jsp").forward(request, response);
		}
		
		
	}

}
