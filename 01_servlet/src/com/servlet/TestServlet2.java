package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 요청 URL: http://localhost:8090/01_servlet/test
@WebServlet("/test2")
public class TestServlet2 extends HttpServlet {
	
	
	//인스턴스 변수 : 공유 가능  ( thread-unsafe )
	int num;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int num2 = 0;   // 로컬변수 : 나 혼자 사용 가능 ★   ( thread-safe )
		               // 서블릿 코드 구현은 thread-safe하게 구현해야 된다. 
		num++;
		num2++;
		
		System.out.println("thread-unsafe: " + num);
		System.out.println("thread-safe: " + num2);
	
	}

}
