package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.LoginControl;
import com.yedam.control.LoginFormControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModifyFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveMemberControl;

//회원관련 메뉴와 컨트롤 등록
public class MenuMember {
	private static MenuMember instance = new MenuMember();
	private MenuMember() {}
	public static MenuMember getInstance() {
		return instance;
	}
	
	public Map<String, Control> MenuMap() {
		Map<String, Control> menu = new HashMap<>();
		menu.put("/addMember.do", new AddMemberControl()); // 회원등록페이지
		menu.put("/addForm.do", new AddFormControl()); // 회원등록페이지.
		menu.put("/memberList.do", new MemberListControl());
		menu.put("/getMember.do", new GetMemberControl()); // 회원아이디로 상세조회
		menu.put("/modifyForm.do", new ModifyFormControl()); // 수정화면 이동
		menu.put("/modifyMember.do", new ModifyMemberControl()); // 수정처리
		menu.put("/removeMember.do", new RemoveMemberControl()); // 삭제처리
		
		//로그인 관련
		menu.put("/loginForm.do", new LoginFormControl());
		menu.put("/login.do", new LoginControl());
		return menu;
	}
}
