package com.http.msg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 封装Response
 * @author lenovo
 *
 */

public class Response {
	//存储头信息
	private StringBuilder headInfo;
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	//正文长度
	private int len = 0;
	//响应正文
	private StringBuilder content;
	private BufferedWriter bw;
	
	public Response() {
		headInfo = new StringBuilder();
		content = new StringBuilder();
	}
	
	public Response(OutputStream os) {
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public Response(Socket client) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			headInfo = null;
		}
	}
	
	//构建正文
	public  Response print(String info) {
		content.append(info);
		len += info.getBytes().length;
		return this;
	}
	
	//构建正文+回车
	public  Response println(String info) {
		content.append(info).append(CRLF);
		len += (info+CRLF).getBytes().length;
		return this;
	}
	
	private void createHeadInfo(int code) {
		//HTTP协议版本，状态码，描述
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch(code) {
		case 200 : 
			headInfo.append("OK");
			break;
		case 404 : 
			headInfo.append("NOT FOUND");
			break;
		case 505 : 
			headInfo.append("SERVER ERROR");
			break;
		}
		headInfo.append(CRLF);
		//HTTP响应头
		headInfo.append("Server:MyServer/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset:GBK").append(CRLF);
		//HTTP响应正文的长度  字节长度
		headInfo.append("Content-length:").append(len).append(CRLF);
		//响应正文和响应头之间的空行
		headInfo.append(CRLF);
	}
	
	//推送到客户端
	public void pushToClient(int code) throws IOException {
		if(headInfo == null) {
			code = 500;
		}
		createHeadInfo(code);
		//响应头信息+空行
		bw.append(headInfo.toString());
		//响应正文
		bw.append(content.toString());
		bw.flush();
	}
	
	public void close() throws IOException {
		if(bw != null) {
			bw.close();
		}
	}

}
