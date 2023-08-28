package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.CartServiceImpl;


@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  HttpSession session = request.getSession();
		  MemberDTO dto =
				  (MemberDTO)session.getAttribute("login");
		  
		 String nextPage = null; 
		 if(dto!=null) { 
		    //로그인 한 경우
			  String num = request.getParameter("num");
			  // num값을 서비스 거쳐서 DAO에 전달
			  CartService service = new CartServiceImpl();
			  int n  = service.cartDelete(Integer.parseInt(num));
			  
			 nextPage = "CartListServlet";
		 }else {
		   // 로그인 안했거나 했는데 time-out된 경우	 
			 nextPage = "member/checkLogin.jsp";
		 }
		 response.sendRedirect(nextPage);
	 
	}

}





