package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddReplyControl;
import com.yedam.control.RemoveReplyControl;
import com.yedam.control.RemoveReplysControl;
import com.yedam.control.ReplyCountControl;
import com.yedam.control.ReplyListControl;
import com.yedam.control.ReplyTableControl;

// 게시글 관련 메뉴와 컨트롤 등록
public class MenuReply {
	private static MenuReply instance = new MenuReply();
	private MenuReply() {}
	public static MenuReply getInstance() {
		return instance;
	}
	
	public Map<String, Control> MenuMap() {
		Map<String, Control> menu = new HashMap<>();
		
		// 댓글의 목록을 json형식으로 출력하는 페이지
		menu.put("/replyList.do", new ReplyListControl());
		// 삭제컨트롤
		menu.put("/removeReply.do", new RemoveReplyControl());
		// 한번에삭제
		menu.put("/removeReplys.do", new RemoveReplysControl());
		//등록컨트롤
		menu.put("/addReply.do", new AddReplyControl());
		//댓글건수컨트롤
		menu.put("/replyCount.do", new ReplyCountControl());
		
		//댓글작성 dataTable용
		menu.put("/replyTable.do", new ReplyTableControl());
		return menu;
		
	}
}
