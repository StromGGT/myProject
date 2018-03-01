package com.http.servlet;

import com.http.msg.Request;
import com.http.msg.Response;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		response.println("<html><head><meta charset=\"GBK\" /><title>注册成功</title></head>");
		response.println("<body>");
		response.println("恭喜").println(request.getParameter("userName")).println("，您已经成功注册！");
		response.println("</body></html>");
	}

}
