package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(SearchDTO search); // 목록
	boolean removeReply(int rno);// 삭제
	boolean removeReplys(String[] arr);//다건 삭제
	boolean addReply(ReplyVO reply);//댓글 등록
	
	//댓글건수
	int getReplyCount(int bno);
	
	//fullCalendar
	List<Map<String, Object>> eventList();
	boolean addEvent(SearchDTO event);
	boolean removeEvent(SearchDTO event);
	
	//todo
	List<Map<String, Object>> todoList();
	
	//chart
	List<Map<String, Object>> countPerWriter();
}
