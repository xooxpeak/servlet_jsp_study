package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		// DB 연동해서 confirm 함. (인증완료)
		
		// 세션얻기
		HttpSession session = request.getSession();
		System.out.println("세션의 id값: " + session.getId());  // ID 부여
		
		// 세션에 로그인 정보 저장
		session.setAttribute("user", userid);
		
		// 로그인 이후의 응답처리
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			 out.print("<html><head>");
			 out.print("<meta charset=\"UTF-8\">");
			 out.print("<title>Insert title here</title>");	
			 out.print("</head>");
			 out.print("<body>");
				 
			 out.print("<h1>로그인성공</h1>");		 
			 out.print("안녕하세요. " + userid +"님");		 
			 out.print("<a href='mypage'>mypage</>");
				 
				 out.print("</body></html>");
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
