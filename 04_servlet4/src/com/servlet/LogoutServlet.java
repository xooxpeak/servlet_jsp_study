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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키얻기, 최대 300개
				Cookie [] cookies = request.getCookies();
				
				// 로그인 정보 얻기
				String id = null;
				Cookie xxx = null;
				for(Cookie c: cookies) {
					if("user".equals(c.getName())) {
						id = c.getValue();
						xxx = c;
					}
				}
				
				// 로그인 유무 확인
				if(id != null) {
					//응답처리
					xxx.setMaxAge(0);    // 쿠키 삭제
					response.addCookie(xxx);   // 쿠키 응답
					// 로그아웃 이후의 화면 선택
					response.sendRedirect("loginForm.jsp");
				}else {
					response.sendRedirect("loginForm.jsp");
				}
				
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
