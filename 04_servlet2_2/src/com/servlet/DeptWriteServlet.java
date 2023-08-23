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
//현재:http://localhost:8090/03servlet/deptForm.jsp 절대경로
//타겟:http://localhost:8090/03servlet/write 상대경로
@WebServlet("/write")
public class DeptWriteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.getParameter()는 무조건 문자열로 가져온다. => String으로 써주기
		String deptno = request.getParameter("deptno");
		String dname = request.getParameter("dname");
		String loc = request.getParameter("loc");
		
		DeptDTO dto = new DeptDTO(Integer.parseInt(deptno), dname, loc);
		
		// 서비스 연동
		DeptService service = new DeptServiceImpl();
		int n = service.addDept(dto);    // n = 저장이 됐으면 1, 안됐으면 0
		
		//응답처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");
		if(n==1) {
			out.print("부서등록 성공");
			out.print("<a href='list'>목록보기</a>");
		}else {
			out.print("부서등록 실패");
			out.print("<a href='deptForm.jsp'>부서등록</a>");
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
