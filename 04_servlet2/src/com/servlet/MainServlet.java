package com.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 요청 서블릿
// http://localhost:8090/04_servlet/main
// 가. URL이 변경안됨
@WebServlet("/main")
public class MainServlet extends HttpServlet {
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainServlet");
		// 요청위임1 - 포워드(forward)
		// 가. URL 변경이 안됨. (왜? request가 확장)
		
		// scope에 저장
		request.setAttribute("request", "request");
		
		HttpSession session = request.getSession();
		session.setAttribute("session", "session");
		
		ServletContext applicaton = getServletContext();
		applicaton.setAttribute("applicaton", "applicaton");
		
		
		
		request.getRequestDispatcher("response").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
