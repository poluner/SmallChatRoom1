package server;

import java.net.*;

import iostream.IOStream;

public class TalkServer {
	public static void main(String args[]) {
		try {
			ServerSocket server = new ServerSocket(4700);
			Socket socket = server.accept();
			IOStream ioStream = new IOStream(socket);

			while (true) {
				System.out.println(ioStream.getMessage());
				System.out.println("received!");
			}
		} catch (Exception e) {
			System.out.println("ErrorServer:" + e);
		}
	}
}
