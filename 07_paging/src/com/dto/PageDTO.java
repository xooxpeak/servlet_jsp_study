package com.dto;

import java.util.List;


public class PageDTO {
	
	// list.jsp 에서 필요한 4개의 정보를 묶어주는 클래스
	List<BoardDTO> list;    
	
	int perPage = 3;   // 페이지당 보여줄 갯수
	int curPage;      // 현재 페이지 번호, 맨 처음 요청시에는 1로 설정됨
	int totalCount;  // 전체 레코드 갯수, select count(*) from board 이용
	
	
	// getter / setter 생성
	public List<BoardDTO> getList() {
		return list;
	}
	public void setList(List<BoardDTO> list) {
		this.list = list;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
