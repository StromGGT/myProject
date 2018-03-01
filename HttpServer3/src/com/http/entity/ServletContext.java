package com.http.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * ������ ����URL-name-class��ӳ���ϵ
 * @author lenovo
 *
 */

public class ServletContext {
	//Ϊÿ��servlet��һ������
	//һ����Դ��Ӧ���URL����Servlet���ȫ�޶�����
	//������servlet��Դ����ӳ��
	private Map<String, String> servlet;
	//·������������ӳ��
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
