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
		response.println("<html><head><meta charset=\"GBK\" /><title>ע��ɹ�</title></head>");
		response.println("<body>");
		response.println("��ϲ").println(request.getParameter("userName")).println("�����Ѿ��ɹ�ע�ᣡ");
		response.println("</body></html>");
	}

}
