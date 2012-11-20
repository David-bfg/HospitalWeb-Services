package com.mayo.dwr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET");
		System.out.println(request.getContextPath());
		System.out.println(request.getPathInfo());
		response.getWriter().write("get");
	}
	
	
	public void doPut(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("PUT");
		System.out.println(request.getContextPath());
		System.out.println(request.getReader().readLine());
		response.getWriter().write("put");
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("DELETE");
		System.out.println(request.getContextPath());
		response.getWriter().write("delete");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("POST");

		System.out.println(request.getReader().readLine());
		System.out.println(request.getContextPath());
		response.getWriter().write("post");
	}
}
