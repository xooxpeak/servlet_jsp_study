package com.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.BoardDTO;
import com.dto.PageDTO;

public class BoardDAO {
	
	public PageDTO list(SqlSession session, int curPage) {
		
		// PageDTO에 4개의 데이터 저장 해야함
		/*
		 * List<BoardDTO> list; 
		 * int perPage = 3;
		 * int curPage; 
		 * int totalCount;
		 */
		PageDTO pageDTO = new PageDTO();
		// 시작위치 = (현재페이지-1) * 페이지당 보여줄 갯수
		int offset = (curPage-1) * pageDTO.getPerPage();
		
		// 테이블에서 얻을 갯수
		int limit = pageDTO.getPerPage();
		
		// 3개 레코드가 저장됨
		List<BoardDTO> list = session.selectList("BoardMapper.list", 
													null, 
													new RowBounds(offset, limit));
		pageDTO.setList(list);
		
		// 현재 페이지 번호 저장됨
		pageDTO.setCurPage(curPage);
		
		// 전체 레코드 갯수 (select count(*)에 의해서 전체 레코드 갯수 저장됨)
		int totalCount = session.selectOne("BoardMapper.totalCount");
		pageDTO.setTotalCount(totalCount);
		
		return pageDTO;
	}

}
