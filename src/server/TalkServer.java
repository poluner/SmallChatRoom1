package server;

import java.net.*;

import iostream.IOStream;

public class TalkServer {
	public static void main(String args[]) {
		try {
			ServerSocket server = new ServerSocket(4700); // 创建一个ServerSocket在端口4700监听客户请求
			Socket socket = server.accept();// 使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
			IOStream ioStream = new IOStream(socket);

			while (true) {
				String message = ioStream.is.readLine();
				System.out.println(message);
				System.out.println("received!");
			}
		} catch (Exception e) {
			System.out.println("ErrorServer:" + e);
		}
	}
}
