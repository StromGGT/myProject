package com.http.msg;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 封装Request
 * @author lenovo
 *
 */

public class Request {
	//请求方法
	private String method;
	//请求资源
	private String url;
	//请求参数
	private Map<String, List<String>> parameterMapValues;
	private static final String CRLF = "\r\n";
	private InputStream ls;
	private String requestInfo;
	
	public Request() {
		method = "";
		url = "";
		parameterMapValues = new HashMap<>();
		requestInfo = "";
	}
	
	public Request(InputStream ls) {
		this();
		this.ls = ls;
		try {
			byte[] data = new byte[20480];
			int len = ls.read(data);
			requestInfo = new String(data, 0, len);
		} catch (IOException e) {
			return;
		}
		//分析头信息
		parseRequestInfo();
	}
	
	//解析请求信息
	private void parseRequestInfo() {
		if(requestInfo == null || (requestInfo.trim()).equals(""))
			return;
		/**
		 * 从信息首行分解出请求方式，请求路径，请求参数（get方式可能存在）
		 * 如果为post方式，请求参数可能在请求正文中
		 */
		
		//接收请求参数
		String paramString = "";
		
		//获取请求方法
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int index = requestInfo.indexOf("/");
		this.method = firstLine.substring(0, index).trim();
		String urlStr = firstLine.substring(index, requestInfo.indexOf("HTTP/")).trim();
		if(this.method.equalsIgnoreCase("POST")) {
			this.url = urlStr;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF));//post方式请求参数
		}else if(this.method.equalsIgnoreCase("GET")) {
			if(urlStr.contains("?")) {//判断是否存在参数
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];//请求URL
				paramString = urlArray[1];//get方式请求参数
			}else {
				this.url = urlStr;
			}
		}
		
		//不存在请求参数
		if(paramString.equals(""))
			return;
		//将请求参数封装到map中
		parseParams(paramString);
	}
	
	//将请求参数封装到map中
	private void parseParams(String paramString) {
		//分割 将字符转换成数组
		StringTokenizer token = new StringTokenizer(paramString, "&");
		while(token.hasMoreTokens()) {
			String keyValue = token.nextToken();
			String[] keyValues = keyValue.split("=");
			if(keyValues.length == 1) {
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}
			
			String key = keyValues[0].trim();
			String value = keyValues[1]==null ? null : decode(keyValues[1].trim(),"utf-8");
			
			if(!parameterMapValues.containsKey(key)) {
				parameterMapValues.put(key, new ArrayList<String>());
			}
			
			List<String> values = parameterMapValues.get(key);
			values.add(value);
			
		}
	}
	
	//解决中文编码问题
	private String decode(String value, String code) {
		String str = null;
		try {
			str = URLDecoder.decode(value, code);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	//根据页面的name获取对应的值
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if(values == null) {
			return null;
		}else {
			return values[0];
		}
	}
	
	//根据页面的name获取对应的多个值
	public String[] getParameterValues(String name) {
		List<String> values = null;
		if((values = parameterMapValues.get(name)) == null) {
			return null;
		}else {
			
			return values.toArray(new String[0]);
		}
	}
	
	public String getUrl() {
		return this.url;
	}

}
