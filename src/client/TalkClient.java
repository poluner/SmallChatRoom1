package client;

import java.net.*;

import iostream.*;

public class TalkClient {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);// 向本机的4700端口发出客户请求
			IOStream ioStream = new IOStream(socket);
			while (true) {
				String message = ioStream.sin.nextLine();
				ioStream.os.println(message);// 将信息输出到流
				ioStream.os.flush();// 刷新使服务器立刻收到
				System.out.println("sended!");
			}
		} catch (Exception e) {
			System.out.println("ErrorClient:" + e); // 出错，则打印出错信息
		}
	}
}