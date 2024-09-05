package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddBoardControl;
import com.yedam.control.AddBoardFormControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.ModifyBoardControl;
import com.yedam.control.ModifyBoardFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveBoardControl;

// 게시글 관련 메뉴와 컨트롤 등록
public class MenuBoard {
	private static MenuBoard instance = new MenuBoard();
	private MenuBoard() {}
	public static MenuBoard getInstance() {
		return instance;
	}
	
	public Map<String, Control> MenuMap() {
		Map<String, Control> menu = new HashMap<>();
		
		menu.put("/boardList.do", new BoardListControl());
		
		menu.put("/getBoard.do", new BoardControl());
		
		menu.put("/modifyFormBoard.do", new ModifyBoardFormControl());
		
		menu.put("/modifyBoard.do", new ModifyBoardControl()); // 수정처리
		
		menu.put("/removeBoard.do", new RemoveBoardControl());
		
		//등록
		menu.put("/addBoardForm.do", new AddBoardFormControl());
		menu.put("/addBoard.do", new AddBoardControl());
		return menu;
	}
}
