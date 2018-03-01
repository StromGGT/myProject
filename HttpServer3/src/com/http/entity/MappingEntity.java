package com.http.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <servlet-mapping>
		<servlet-name>login</servlet-name>
		<url-pattern>/login</url-pattern>
	</servlet-mapping>
 * @author lenovo
 *
 */

public class MappingEntity {
	private String name;
	private List<String> urlPattern;
	
	public MappingEntity() {
		urlPattern = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
}
