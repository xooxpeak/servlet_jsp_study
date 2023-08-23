package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		// DB 연동해서 confirm 함. (인증완료)
		
		// 쿠키얻기, 최대 300개
		Cookie [] cookies = request.getCookies();
		
		// 로그인 정보 얻기
		String id = null;
		for(Cookie c: cookies) {
			if("user".equals(c.getName())) {
				id = c.getValue();
			}
		}
		
		// 로그인 유무 확인
		if(id != null) {
			//응답처리
			// 로그인 한 경우로서 응답처리
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						 out.print("<html><head>");
						 out.print("<meta charset=\"UTF-8\">");
						 out.print("<title>Insert title here</title>");
						 out.print("</head>");
						 out.print("<body>");
						 out.print("<h1>mypage</h1>");		 
						 out.print("안녕하세요. " + id +"님");		 
						 out.print("<a href='logout'>로그아웃</>");		 
						 
						 out.print("</body></html>");
		}else {
			response.sendRedirect("loginForm.jsp");
		}
		

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
