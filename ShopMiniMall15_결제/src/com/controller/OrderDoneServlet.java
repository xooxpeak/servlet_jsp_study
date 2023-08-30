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
import com.dto.OrderDTO;
import com.service.CartService;
import com.service.CartServiceImpl;
import com.service.OrderService;
import com.service.OrderServiceImpl;


@WebServlet("/OrderDoneServlet")
public class OrderDoneServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		  MemberDTO dto =
				  (MemberDTO)session.getAttribute("login");
		  
		 String nextPage = null; 
		 if(dto!=null) { 
		    //로그인 한 경우
			 
			 /*
			  *     
			  *  userid VARCHAR2(10),
     gCode varchar2(20) not null,
     gName varchar2(50) not null,
     gPrice NUMBER(6) not null,
     gSize CHAR(1) not null,
     gColor VARCHAR2(10) not null,
     gAmount NUMBER(2) not null,
     gImage varchar2(20) not null,
     				orderName VARCHAR2(10) NOT NULL,
     post VARCHAR2(5) NOT NULL,
     addr1 VARCHAR2(500) NOT NULL,
     addr2 VARCHAR2(500) NOT NULL,
     phone VARCHAR2(11) NOT NULL,
     payMethod VARCHAR2(10) NOT NULL,
			  */
			 
			 // 주문자 정보 (로그인 정보)
			 String userid = dto.getUserid();
			 
			 // 상품정보
			 String gCode = request.getParameter("gCode");
			 String gName = request.getParameter("gName");
			 String gPrice = request.getParameter("gPrice");
			 String gSize = request.getParameter("gSize");
			 String gColor = request.getParameter("gColor");
			 String gAmount = request.getParameter("gAmount");
			 String gImage = request.getParameter("gImage");
			 
			 // 배송정보
			 String orderName = request.getParameter("orderName");
			 String post = request.getParameter("post");
			 String addr1 = request.getParameter("addr1");
			 String addr2 = request.getParameter("addr2");
			 String phone = request.getParameter("phone");
			 String payMethod = request.getParameter("payMethod");
			 
			 
			 // <!-- Cart 테이블에서 삭제할 num 전달 -->
			 // 이 num을 서비스에 넘겨줘야한다.
			 String del_num = request.getParameter("num");
			 
			 //OrderDTO에 저장
			 OrderDTO orderDTO = new OrderDTO();
			 orderDTO.setUserid(userid);
			 orderDTO.setgCode(gCode);
			 orderDTO.setgName(gName);
			 orderDTO.setgPrice(Integer.parseInt(gPrice));
			 orderDTO.setgSize(gSize);
			 orderDTO.setgColor(gColor);
			 orderDTO.setgAmount(Integer.parseInt(gAmount));
			 orderDTO.setgImage(gImage);
			 
			 orderDTO.setOrderName(orderName);
			 orderDTO.setPost(post);
			 orderDTO.setAddr1(addr1);
			 orderDTO.setAddr2(addr2);
			 orderDTO.setPhone(phone);
			 orderDTO.setPayMethod(payMethod);
			 
			 // orderDTO 값을 서비스 거쳐서 DAO에 전달
			 OrderService service = new OrderServiceImpl();
			 //orderDTO로 insert / Integer.parseInt(del_num)로 delete
			 int n = service.orderDone(orderDTO, Integer.parseInt(del_num));   
			 
			 // orderDTO를 scope에 저장해야 orderDone을 보여줄 수 있다.
			 request.setAttribute("orderDTO", orderDTO);
			 
			 // 요청위임
			 nextPage = "orderDone.jsp";
		 }else {
		   // 로그인 안했거나 했는데 time-out된 경우	 
			nextPage = "member/checkLogin.jsp";
		 }

        request.getRequestDispatcher(nextPage).forward(request, response);
		
		
	}

}



