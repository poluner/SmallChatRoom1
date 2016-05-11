package client;

import java.net.*;
import iostream.*;

public class TalkClient {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);
			IOStream ioStream = new IOStream(socket);
			while (true) {
				String pathName = ioStream.sin.nextLine();
				ioStream.fileToStream(pathName);
				System.out.println("sended a file!");
			}
		} catch (Exception e) {
			System.out.println("ErrorClient:" + e);
		}
	}
}
