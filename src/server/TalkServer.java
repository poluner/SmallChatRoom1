package server;

import java.net.*;

import iostream.IOStream;

public class TalkServer {
	public static void main(String args[]) {
		try {
			ServerSocket server = new ServerSocket(4700); // ����һ��ServerSocket�ڶ˿�4700�����ͻ�����
			Socket socket = server.accept();// ʹ��accept()�����ȴ��ͻ������пͻ������������һ��Socket���󣬲�����ִ��
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
