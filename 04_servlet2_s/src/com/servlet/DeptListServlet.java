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

//현재: http://localhost:8090/03_servlet/list
//타겟: http://localhost:8090/03_servlet/deptForm.jsp
@WebServlet("/list")
public class DeptListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptService service = new DeptServiceImpl();
		List<DeptDTO> list = service.findAll();
		
		//응답처리
		//응답처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		 out.print("<html><head>");
		 out.print("<meta charset=\"UTF-8\">");
		 out.print("<title>Insert title here</title>");
		 out.print("</head>");
		 out.print("<body>");
		 
		 out.print("<h1>Dept 목록</h1>");		 
		 out.print("<table border='1'>");
		 out.print("<tr>");
		 out.print("<th>부서번호</th>");
		 out.print("<th>부서명</th>");
		 out.print("<th>부서위치</th>");		 
		 out.print("</tr>");
		 
		 for (DeptDTO dto : list) {
			 out.print("<tr>");
			 // http://localhost:8090/03_servlet/retrieve?deptno=값
			 out.print("<td><a href='retrieve?deptno="+dto.getDeptno()+"'>"+dto.getDeptno()+"</a></td>");
			 out.print("<td>"+dto.getDname()+"</td>");
			 out.print("<td>"+dto.getLoc()+"</td>");
			 out.print("</tr>");
		 }
		 
		 out.print("</table>");

		 out.print("<a href='deptForm.jsp'>부서등록</a>");
		 out.print("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
