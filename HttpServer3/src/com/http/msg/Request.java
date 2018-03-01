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
 * ��װRequest
 * @author lenovo
 *
 */

public class Request {
	//���󷽷�
	private String method;
	//������Դ
	private String url;
	//�������
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
		//����ͷ��Ϣ
		parseRequestInfo();
	}
	
	//����������Ϣ
	private void parseRequestInfo() {
		if(requestInfo == null || (requestInfo.trim()).equals(""))
			return;
		/**
		 * ����Ϣ���зֽ������ʽ������·�������������get��ʽ���ܴ��ڣ�
		 * ���Ϊpost��ʽ�������������������������
		 */
		
		//�����������
		String paramString = "";
		
		//��ȡ���󷽷�
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int index = requestInfo.indexOf("/");
		this.method = firstLine.substring(0, index).trim();
		String urlStr = firstLine.substring(index, requestInfo.indexOf("HTTP/")).trim();
		if(this.method.equalsIgnoreCase("POST")) {
			this.url = urlStr;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF));//post��ʽ�������
		}else if(this.method.equalsIgnoreCase("GET")) {
			if(urlStr.contains("?")) {//�ж��Ƿ���ڲ���
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];//����URL
				paramString = urlArray[1];//get��ʽ�������
			}else {
				this.url = urlStr;
			}
		}
		
		//�������������
		if(paramString.equals(""))
			return;
		//�����������װ��map��
		parseParams(paramString);
	}
	
	//�����������װ��map��
	private void parseParams(String paramString) {
		//�ָ� ���ַ�ת��������
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
	
	//������ı�������
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
	
	//����ҳ���name��ȡ��Ӧ��ֵ
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if(values == null) {
			return null;
		}else {
			return values[0];
		}
	}
	
	//����ҳ���name��ȡ��Ӧ�Ķ��ֵ
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
