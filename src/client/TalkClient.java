package client;

import java.net.*;

import iostream.*;

public class TalkClient {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);
			IOStream ioStream = new IOStream(socket);
			while (true) {
				String message = ioStream.sin.nextLine();
				ioStream.os.write(message.getBytes());
				ioStream.os.write(255);// 添加255作为结束符
				ioStream.os.flush();
				System.out.println("sended!");
			}
		} catch (Exception e) {
			System.out.println("ErrorClient:" + e);
		}
	}
}