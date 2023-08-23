package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.DeptDTO;
import com.service.DeptService;
import com.service.DeptServiceImpl;

@WebServlet("/retrieve")
public class DeptRetrieveServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String deptno =
				request.getParameter("deptno");
		
		DeptService service = 
				new DeptServiceImpl();
		
		DeptDTO dto = service.findByDeptno(Integer.parseInt(deptno));
		
		//응답처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		 out.print("<html><head>");
		 out.print("<meta charset=\"UTF-8\">");
		 out.print("<title>Insert title here</title>");
		 out.print("</head>");
		 out.print("<body>");
		 
		 out.print("<h1>클릭한 데이터</h1>");
		 
		 out.print("<form action='update' method='get'>");		
		 
		 out.print("부서번호:"+ dto.getDeptno()+"<br>");
		 out.print("<input type='hidden' name='deptno' value='"+dto.getDeptno()+"'>");		 
		 
		 out.print("부서명:<input type='text' name='dname' value='"+dto.getDname()+"'><br>");		 
		 out.print("부서위치:<input type='text' name='loc' value='"+dto.getLoc()+"'><br>");		 
		 
		 out.print("<input type='submit' value='수정'>");
		 out.print("</form>");
		 
		 out.print("<hr>");
		 out.print("<a href='delete?deptno="+dto.getDeptno()+"'>삭제</a>");		 
		 
		 out.print("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST 한글처리 ==> 나중에 필터로 구현
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
