package com.http.msg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * ��װResponse
 * @author lenovo
 *
 */

public class Response {
	//�洢ͷ��Ϣ
	private StringBuilder headInfo;
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	//���ĳ���
	private int len = 0;
	//��Ӧ����
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
	
	//��������
	public  Response print(String info) {
		content.append(info);
		len += info.getBytes().length;
		return this;
	}
	
	//��������+�س�
	public  Response println(String info) {
		content.append(info).append(CRLF);
		len += (info+CRLF).getBytes().length;
		return this;
	}
	
	private void createHeadInfo(int code) {
		//HTTPЭ��汾��״̬�룬����
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
		//HTTP��Ӧͷ
		headInfo.append("Server:MyServer/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset:GBK").append(CRLF);
		//HTTP��Ӧ���ĵĳ���  �ֽڳ���
		headInfo.append("Content-length:").append(len).append(CRLF);
		//��Ӧ���ĺ���Ӧͷ֮��Ŀ���
		headInfo.append(CRLF);
	}
	
	//���͵��ͻ���
	public void pushToClient(int code) throws IOException {
		if(headInfo == null) {
			code = 500;
		}
		createHeadInfo(code);
		//��Ӧͷ��Ϣ+����
		bw.append(headInfo.toString());
		//��Ӧ����
		bw.append(content.toString());
		bw.flush();
	}
	
	public void close() throws IOException {
		if(bw != null) {
			bw.close();
		}
	}

}
