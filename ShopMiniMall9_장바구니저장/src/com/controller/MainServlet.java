package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.GoodsDTO;
import com.service.GoodsService;
import com.service.GoodsServiceImpl;


@WebServlet("/main")
public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//상품목록
		String gCategory=request.getParameter("gCategory");
		
		//맨처음 화면 즉, top dress out bottom 카테고리를 하나도 안눌렀을 경우 (null) 
		//그 첫 화면은 top으로 해준다.
		if(gCategory == null) {
			gCategory = "top";
		}
		
		GoodsService service = new GoodsServiceImpl();
		List<GoodsDTO> list = service.goodsList(gCategory);
		
		//scope 저장
		request.setAttribute("goodsList", list);
		
		//포워드
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}

