package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.EventControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.IntroControl;
import com.yedam.control.JavaScriptControl;
import com.yedam.control.MainControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModifyFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveMemberControl;
import com.yedam.control.SubControl;

//@WebServlet("*.do")
public class FrontController extends HttpServlet {

	// url pattern - 실행되는 기능 -> map 컬렉션에 지정.
	Map<String, Control> map;

	public FrontController() {
		map = new HashMap<>();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl());
		map.put("/sub.do", new SubControl());
		map.put("/intro.do", new IntroControl());
		map.put("/javascript.do", new JavaScriptControl());

		// fullcalendar
		map.put("/eventList.do", new EventControl());
		map.put("/addEvent.do", new EventControl());
		map.put("/removeEvent.do", new EventControl());
		map.put("/chart.do", new EventControl());
		map.put("/showChart.do", new EventControl());
		
		// 기능등록.
		Map<String, Control> memberMenu = MenuMember.getInstance().MenuMap();
		Map<String, Control> boardMenu = MenuBoard.getInstance().MenuMap();
		Map<String, Control> menuReply = MenuReply.getInstance().MenuMap();
		map.putAll(memberMenu); // 멤버관련 메뉴추가
		map.putAll(boardMenu); // 게시글관련 메뉴추가
		map.putAll(menuReply); // 댓글 관련 메뉴추가
	}

	// HttpServletRequest
	// HttpServletResponse

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();// /BoardWeb/main.do
		String context = req.getContextPath(); // /BoardWeb
		String page = uri.substring(context.length()); // /main.do
		System.out.println(page);

		Control control = map.get(page);
		control.exec(req, resp);
	}
}
