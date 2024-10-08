package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB 추출된 정보를 활용해서 json 포맷의 문자열을 반환
		// [{"replyNo": 1, "replyer": "user01", "reply": "149번에 댓글1입니다","boardNo":149...},{},{},...{}]
		
		response.setContentType("text/json;charset=utf-8");
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		System.out.println("page:" + page +"& bno:" + bno);
		//검색조건 SearchDTO
		SearchDTO search = new SearchDTO();
		search.setBoardNo(Integer.parseInt(bno));
		search.setPage(Integer.parseInt(page));
		ReplyService svc = new ReplyServiceImpl();
		List<ReplyVO> list = svc.replyList(search);
		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"replyNo\": " + list.get(i).getReplyNo()
					+",\"replyer\": \"" + list.get(i).getReplyer()
					+ "\",\"reply\": \"" + list.get(i).getReply() 
					+ "\",\"boardNo\": " + list.get(i).getBoardNo()
					+ ",\"replyDate\": \"" + list.get(i).getReplyDate();
			if(i!=list.size()-1)json += "\"},";
			else json += "\"}";
			
		}
		json += "]";
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		json = gson.toJson(list); // gson을 활용해서 json문자열 생성
		response.getWriter().print(json);
		
	}

}
