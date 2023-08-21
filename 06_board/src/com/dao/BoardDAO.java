package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.BoardDTO;
import com.dto.PageDTO;

public class BoardDAO {
	
	// 전체 목록
	public PageDTO list(SqlSession session, HashMap<String, String> map, int curPage){
		PageDTO pageDTO = new PageDTO();
		
		//현재페이지는 BoardListServlet으로 가야함
		int offset=(curPage-1)*pageDTO.getPerPage();    //시작위치    //인덱스 고려해서 (현재페이지-1)
		int limit=pageDTO.getPerPage();     //한 페이지 당 보여줄 갯수
		
		List<BoardDTO> list = session.selectList("BoardMapper.list", map,
												new RowBounds(offset, limit));
		
		//list 저장
		pageDTO.setList(list);
		//curPage 저장
		pageDTO.setCurPage(curPage);
		
		//totalCount 저장
		int totalCount = 0;
		if(map.get("searchValue")==null) {
			// 검색 안한 경우
			totalCount = session.selectOne("totalCount");
		}else {
			// 검색 한 경우
			totalCount = session.selectOne("totalCountSearch", map);
		}
		pageDTO.setTotalCount(totalCount);
		
		
		//serachName과 searchValue 모두 PageDTO에 저장 필요
		pageDTO.setSearchName(map.get("searchName"));
		pageDTO.setSearchValue(map.get("searchValue"));
		
		
		
		return pageDTO;
	}
	
	// 글 저장
	public int write(SqlSession session, BoardDTO dto) {
		int n = session.insert("BoardMapper.write", dto);
		return n;
	}
	
	// 글 자세히 보기
	public BoardDTO retrieve(SqlSession session, int num) {
		BoardDTO dto = session.selectOne("BoardMapper.retrieve", num);
		return dto;
	}
	
	// 조회수 증가
	public int readcnt (SqlSession session, int num) {
		int n = session.update("BoardMapper.readcnt", num);
		return n;
	}
	
	// 글 수정
	public int update(SqlSession session, BoardDTO dto) {
		int n = session.update("BoardMapper.update", dto);
		return n;
	}
	
	// 글 삭제
	public int delete(SqlSession session, int num) {
		int n = session.delete("BoardMapper.delete", num);
				return n;
	}

}
