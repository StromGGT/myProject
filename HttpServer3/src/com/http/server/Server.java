package com.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.http.controller.Dispatcher;

/**
 *请求并响应
 * @author lenovo
 *
 */

public class Server {
	private ServerSocket server;
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	private boolean isShutDown = false;
	
	
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();	
	}
	
	//启动方法
	public void start() {
		start(8888);
	}
	
	public void start(int port) {
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			stop();
		}
	}
	
	//接收方法
	private void receive() {
		try {
			while(!isShutDown) {
				new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			stop();
		}
	}
	
	//停止方法
	public void stop() {
		isShutDown = true;
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
