package com.http.servlet;

import com.http.msg.Request;
import com.http.msg.Response;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) throws Exception {
		String name = request.getParameter("userName");
		String pwd = request.getParameter("userPwd");
		if(login(name,pwd)) {
			response.println("��½�ɹ���");
		}else {
			response.println("��½ʧ�ܣ�");
		}
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		
		
	}
	
	public boolean login(String name, String pwd) {
		return name.equals("admin") && pwd.equals("a111");
	}

}
