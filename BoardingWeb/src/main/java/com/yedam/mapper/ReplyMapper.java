package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	// 댓글목록.
	List<ReplyVO> selectList(int bno);
	List<ReplyVO> selectListPaging(SearchDTO search); // 페이지당 건수 출력
	// 댓글 삭제.
	int deleteReply(int rno);
	// 다건 삭제
	int deleteReplys(String[] arr);
	// 댓글 등록
	int insertReply(ReplyVO reply);
	int selectKey();
	
	// 댓글 건수
	int selectReplyCount(int bno);
	
	// fullcalendar 일정
	List<Map<String,Object>> selectEvent();
	int insertEvent(SearchDTO event);
	int deleteEvent(SearchDTO event);
	
	// 오늘의 할일
	List<Map<String,Object>> todoList();
	
	List<Map<String,Object>> countPerWriter();
	
}
