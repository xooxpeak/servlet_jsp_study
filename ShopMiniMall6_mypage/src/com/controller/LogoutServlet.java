package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 여부 확인
		// 세션에 로그인키가 있는지 확인
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage=null;
		if(dto!=null) {   //1.로그인 되어있으면
			nextPage="main";    //3.메인으로 간다
			session.invalidate();    //2.로그아웃하고
		}else {
			nextPage="member/checkLogin.jsp";     //로그인이 안된 상태=>checkLogin으로 간다.
		}
		
		response.sendRedirect(nextPage);
	}

}

