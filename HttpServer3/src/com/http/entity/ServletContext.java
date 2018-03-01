package com.http.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文 管理URL-name-class的映射关系
 * @author lenovo
 *
 */

public class ServletContext {
	//为每个servlet起一个别名
	//一个资源对应多个URL（存Servlet类的全限定名）
	//别名到servlet资源名的映射
	private Map<String, String> servlet;
	//路径名到别名的映射
	private Map<String, String> mapping;
	
	public ServletContext() {
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
	}
	
	public Map<String, String> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
	

}
