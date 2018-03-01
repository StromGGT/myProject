package com.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.http.controller.Dispatcher;

/**
 *������Ӧ
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
	
	//��������
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
	
	//���շ���
	private void receive() {
		try {
			while(!isShutDown) {
				new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			stop();
		}
	}
	
	//ֹͣ����
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
