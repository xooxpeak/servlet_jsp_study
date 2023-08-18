package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.BoardDTO;
import com.service.BoardService;
import com.service.BoardServiceImpl;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list")
public class BoardListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 검색 파라미터 얻기
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		// 2개의 값을 서비스 거쳐서 DAO에 전달
		// DTO에 저장하지 못하기때문에 HashMap 사용
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		
		
		
		//BoardService 연동
		BoardService service = new BoardServiceImpl();
		List<BoardDTO> list = service.list(map);     //다형성
		
		// 이전에는 서블릿에서 응답처리를 했음. => list.jsp한테 위임
		// list.jsp 에서 List<BoardDTO>를 보여주기 위해서는
		// List<BoardDTO>를 scope에 저장해야 된다. 목록보기는 request scope가 가장 최적임.
		/*
		 * request scope ( <=여기에 저장 )
		 * session scope
		 * application scope
		 */
		request.setAttribute("boardList", list);
		
		// 요청위임. 목록보기는 request scope에 저장했기 때문에 포워드로 사용.
		/*
		 * 포워드(forward)   <= 사용
		 * 리다이렉트(redirect)
		 */
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
