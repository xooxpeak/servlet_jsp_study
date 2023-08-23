package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.DeptDTO;
import com.service.DeptService;
import com.service.DeptServiceImpl;
@WebServlet("/update")
public class DeptUpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// http://localhost:8090/03_servlet/update?dname=영업부&loc=제주
	// 부서번호까지 넘겨줘야함
		String deptno = request.getParameter("deptno");
		String dname = request.getParameter("dname");
		String loc = request.getParameter("loc");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptno", deptno);
		map.put("dname", dname);
		map.put("loc", loc);
		
		//서비스 연동
		DeptService service = new DeptServiceImpl();
		int n = service.updateDept(map);
		

		//응답처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");
		if(n==1) {
			out.print("부서수정 성공");
			out.print("<a href='list'>목록보기</a>");
		}else {
			out.print("부서수정 실패");
			out.print("<a href='retrieve?deptno="+deptno+"'>부서정보</a>");
		}
		out.print("<h1>TestServlet</h1>");
		out.print("</body></html>");
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST 한글처리 => 나중에 필터로 구현
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
