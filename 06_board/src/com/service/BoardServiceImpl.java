package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.BoardDAO;
import com.dto.BoardDTO;

public class BoardServiceImpl implements BoardService {

	@Override
	public List<BoardDTO> list() {
		List<BoardDTO> list = null;    //return 하기 위해 밖에서 선언
		SqlSession session = MySqlSessionFactory.getSession();
			try {
				// DAO 연동코드
				BoardDAO dao = new BoardDAO();    //객체생성
				list = dao.list(session); 
				
			}finally {
				session.close();
			}
		return list;
	}

	@Override
	public int write(BoardDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();	
			try {
				// DAO 연동코드
				BoardDAO dao = new BoardDAO();    //객체생성
				n = dao.write(session, dto);
				session.commit();   //mybatis는 자동커밋이 아니기 때문에 반드시 커밋
			}finally {
				session.close();
			}
		return n;
	}

	@Override
	public BoardDTO retrieve(int num) {
		BoardDTO dto = null;
		SqlSession session = MySqlSessionFactory.getSession();	
			try {
				// DAO 연동코드
				BoardDAO dao = new BoardDAO();    //객체생성
				//조회수 증가
				int n = dao.readcnt(session, num);
				session.commit();
				//자세히 보기
				dto = dao.retrieve(session, num);
			}finally {
				session.close();
			}
		return dto;
	}

	@Override
	public int update(BoardDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();	
			try {
				// DAO 연동코드
				BoardDAO dao = new BoardDAO();    //객체생성
				n = dao.update(session, dto);
				session.commit();
			}finally {
				session.close();
			}
		return n;
	}

	@Override
	public int delete(int num) {
	  int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();	
			try {
				// DAO 연동코드
				BoardDAO dao = new BoardDAO();    //객체생성
				n = dao.delete(session, num);
				session.commit();
			}finally {
				session.close();
			}
		return n;
	}

}

