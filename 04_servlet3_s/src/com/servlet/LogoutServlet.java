package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		// DB 연동해서 confirm 함. (인증완료)
		
		// 세션얻기
		HttpSession session = request.getSession();
		
		// 세션에 로그인 정보 얻기
		String id = (String)session.getAttribute("user");
		
		if(id != null) {
			// 로그인 한 경우로서 응답처리
			session.invalidate();
			// 로그아웃 이후의 화면 선택
			response.sendRedirect("loginForm.jsp");
		}else {
			// id값이 null => 로그인을 위한 경우 또는 했는데 time-out 
			response.sendRedirect("loginForm.jsp");
			// 로그인 이후의 응답처리
		}
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
