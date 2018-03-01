package com.http.servlet;

import com.http.msg.Request;
import com.http.msg.Response;

/**
 * 抽象成一个父类
 * @author lenovo
 *
 */

public abstract class Servlet {
	public void service(Request request, Response response)throws Exception {
		this.doGet(request, response);
		this.doPost(request, response);
	}
	
	protected abstract void doGet(Request request, Response response)throws Exception;
	
	protected abstract void doPost(Request request, Response response)throws Exception;

}
