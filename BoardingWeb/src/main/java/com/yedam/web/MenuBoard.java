package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;

// 게시글 관련 메뉴와 컨트롤 등록
public class MenuBoard {
	private static MenuBoard instance = new MenuBoard();
	private MenuBoard() {}
	public static MenuBoard getInstance() {
		return instance;
	}
	
	public Map<String, Control> MenuMap() {
		Map<String, Control> menu = new HashMap<>();
		
		return menu;
	}
}
