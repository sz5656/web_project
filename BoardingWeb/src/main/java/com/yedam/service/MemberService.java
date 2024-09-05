package com.yedam.service;

import java.util.List;

import com.yedam.vo.MemberVO;

// 기능의 정의.
public interface MemberService {
	List<MemberVO> getMembers();
	boolean addMember(MemberVO member);
	boolean removeMember(String memberId);
	boolean modifyMember(MemberVO member);
	MemberVO getMember(String memberId); // 단건조회
	
	MemberVO loginCheck(String id, String pw);
}
