package com.controller;

import java.io.IOException;

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


@WebServlet("/CartAddServlet")
public class CartAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* ?gImage=outer1&
		    gCode=O1&
		    gName=더블+버튼+블레이져&
		    gPrice=52800&
		    gSize=L&
		    gColor=black&
		    gAmount=3
		*/
		//로그인 여부 확인 방법
        HttpSession session = request.getSession();
        MemberDTO dto = (MemberDTO)session.getAttribute("login");
         String nextPage = null;
		 if(dto!=null) { 
		    //로그인 한 경우
			 String userid = dto.getUserid();
			 String gCode = request.getParameter("gCode");
			 String gName = request.getParameter("gName");
			 String gPrice = request.getParameter("gPrice");
			 String gSize = request.getParameter("gSize");
			 String gColor = request.getParameter("gColor");
			 String gAmount = request.getParameter("gAmount");
			 String gImage = request.getParameter("gImage");
			 
			 CartDTO cartDTO = new CartDTO();
			 cartDTO.setUserid(userid);
			 cartDTO.setgCode(gCode);
			 cartDTO.setgName(gName);
			 cartDTO.setgPrice(Integer.parseInt(gPrice));
			 cartDTO.setgSize(gSize);
			 cartDTO.setgColor(gColor);
			 cartDTO.setgAmount(Integer.parseInt(gAmount));
			 cartDTO.setgImage(gImage);
			 
			 // cartDTO 값을 서비스 거쳐서 DAO로 전달
		    CartService service = new CartServiceImpl();
		    int n = service.cartAdd(cartDTO);
		    nextPage="goods/cartAddSuccess.jsp";
			 
		 }else {
		   // 로그인 안했거나 했는데 time-out된 경우	 
			 nextPage="member/checkLogin.jsp";			 
		 }
		
		 //요청위임
		 response.sendRedirect(nextPage);
		
		
	}

}





