package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int bno); // 목록
	boolean removeReply(int rno);// 삭제
	boolean removeReplys(String[] arr);//다건 삭제
	boolean addReply(ReplyVO reply);//댓글 등록
}
