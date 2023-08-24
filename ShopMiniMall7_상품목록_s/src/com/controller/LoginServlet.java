package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
				
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		// hashmap을 서비스 거쳐서 dao까지 전달.
		MemberService service = new MemberServiceImpl();
		MemberDTO dto = service.login(map);
		
		String nextPage=null;
		if(dto != null) {
			nextPage = "main";

			//세션처리
			HttpSession session = request.getSession();
			session.setAttribute("login", dto);
			
			// session scope에 저장된 login 키의 존재여부에 따라서 로그인 헀는지
			// 또는 안했는지 알 수 있다.
			
		}else {
			// userid 또는 passwd 틀린 경우
			nextPage = "member/loginFail.jsp";
			
		}
		
	   //요청 위임
		response.sendRedirect(nextPage);
		
		
	}

}






