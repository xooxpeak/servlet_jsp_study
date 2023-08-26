package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.MemberServiceImpl;


@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그인 여부 확인
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage = null;
		
		// 로그인 한 경우
		if(dto!=null) {
			nextPage="mypage.jsp";
			// session에 MemberDTO를 저장했지만, 필요로 하는 데이터가 다 session에 없을 수도 있기 때문에 DB에서 땡겨와야된다.
			String userid = dto.getUserid();  
			
			// userid를 서비스 거쳐서 DAO에 전달하고 반환
			MemberService service = new MemberServiceImpl();
			MemberDTO mypage = service.mypage(userid);
			// session에 저장
			session.setAttribute("login", mypage);
			
		}else { // 로그인 안했거나 했는데 time-out된 경우
			nextPage="member/checkLogin.jsp";
		}
		// 요청위임
		// 위에 session에 저장했기 때문에 리다이렉트 해도 된다.
		response.sendRedirect(nextPage);
		
	}

}

