package com.yedam.control;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
/**
 *"/eventList.do"   ->
 *"/addEvent.do"    ->
 *"/removeEvent.do" ->
 */
public class EventControl implements Control {
	ReplyService svc = new ReplyServiceImpl();
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/json;charset=utf-8");
		
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String page = uri.substring(context.length());
		
		// 요청uri값에서 /와 .do를 제외한 값을 실행할 메소드
		String methodName = page.substring(1, page.indexOf("."));
		System.out.println(methodName);
		
		try {
			Class<?> cls = Class.forName(this.getClass().getName());
			Method method = cls.getDeclaredMethod(methodName
					, HttpServletRequest.class
					, HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eventList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReplyService svc = new ReplyServiceImpl();
		List<Map<String, Object>>list = svc.eventList();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		
		response.getWriter().println(json);
	}
	public void addEvent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		SearchDTO event = new SearchDTO();
		event.setStart(start);
		event.setEnd(end);
		event.setTitle(title);
		
		if(svc.addEvent(event)) {
			//{"retCode":"OK"}
			response.getWriter().print("{\"retCode\":\"OK\"}");
		} else {
			response.getWriter().print("{\"retCode\":\"NG\"}");
		}
		
	}
	public void removeEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		SearchDTO event = new SearchDTO();
		event.setStart(start);
		event.setEnd(end);
		event.setTitle(title);
		
		if(svc.removeEvent(event)) {
			//{"retCode":"OK"}
			response.getWriter().print("{\"retCode\":\"OK\"}");
		} else {
			response.getWriter().print("{\"retCode\":\"NG\"}");
		}
	}

}
