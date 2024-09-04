package com.yedam.common;

import lombok.Data;

@Data
public class PageDTO {
	// 현재 페이지 정보
	// 이전, 이후 정보
	int page;
	int startPage, endPage;
	boolean prev, next;
	
	public PageDTO(int page, int totalCnt) {
		this.page = page;
		this.endPage = (int)Math.ceil(page / 10.0)*10;
		this.startPage = this.endPage-9;
		
		int realEnd = (int)Math.ceil(totalCnt/5.0);
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		//이전 이후 여부
		prev = this.startPage > 1; // 1, 11, 21
		next = this.endPage < realEnd ? true : false;
	}
}
