package com.http.entity;

/**
 * <servlet>
		<servlet-name>login</servlet-name>
		<servlet-class>com.http.server.demo4.LoginServlet</servlet-class>
	</servlet>
 * @author lenovo
 *
 */

public class ServletEntity {
	private String name;
	private String clz;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClz() {
		return clz;
	}
	public void setClz(String clz) {
		this.clz = clz;
	}
	
	

}
