package com.http.controller;

import java.io.IOException;
import java.net.Socket;

import com.http.msg.Request;
import com.http.msg.Response;
import com.http.service.WebApp;
import com.http.servlet.Servlet;

public class Dispatcher implements Runnable{
	//���յ��ͻ��˵�����
	private Socket client;
	private Request request;
	//��Ӧ
	private Response response;
	private int code = 200;
	
	public Dispatcher(Socket client) {
		this.client = client;
		try {
			request = new Request(client.getInputStream());
			response = new Response(client.getOutputStream());
		} catch (IOException e) {
			code = 500;
			return;
		}
	}

	@Override
	public void run() {
		
		try {
			//�õ�һ��servlet�ĸ���
			Servlet servlet = WebApp.getServlet(request.getUrl());
			if(servlet == null) {
				this.code = 404;
			}else {
				servlet.service(request, response);
			}
			//push200
			response.pushToClient(code);
		} catch (Exception e) {
			this.code = 500;
		}
		
		try {
			//push500
			response.pushToClient(code);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
