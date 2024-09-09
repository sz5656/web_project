package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		// 댓글작성자, 원본글번호, 댓글내용.
		String replyer = request.getParameter("replyer");
		String bno = request.getParameter("bno");
		String reply = request.getParameter("reply");
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReply(reply);
		replyVO.setReplyer(replyer);
		replyVO.setBoardNo(Integer.parseInt(bno));
		
		ReplyService svc = new ReplyServiceImpl();
		if(svc.addReply(replyVO)) {
			// {"retCode":"OK", "retVal": {"replyNo": 19, "reply": "reply", "replyer":"replyer","boardNo":+148}}
			svc.replyList(Integer.parseInt(bno));
			response.getWriter().print("{\"retCode\":\"OK\", \"retVal\": {"
										+ "\"reply_date\": "+replyVO.getReplyDate()
										+ "\"replyNo\": "+replyVO.getReplyNo()+
										", \"reply\": "+replyVO.getReply()+
										" \"replyer\":"+replyVO.getReplyer()+
										",\"boardNo\":"+replyVO.getBoardNo()+"}}");
		} else {
			// {"retCode":"NG"}
			response.getWriter().print("{\"retCode\":\"NG\"}");
		}
		//retCode => OK retCode => NG
	}

}
