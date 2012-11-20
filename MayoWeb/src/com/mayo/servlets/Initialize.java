package com.mayo.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mayo.dwr.HTTPPoster;
import com.mayo.transform.Files;

public class Initialize extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.main();
		System.out.println("initializing");
		resp.getWriter().print("Initialize done");
	}

	public void main() {
			String path = getServletContext().getRealPath("/WEB-INF/filters.xml");
			path = path.replace("filters.xml","");
			System.out.println(path);
			Files.getInstance().init(path);
			HTTPPoster.getInstance().initURL(Files.getInstance().CONF);
	}
}
