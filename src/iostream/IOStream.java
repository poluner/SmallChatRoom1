package iostream;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOStream {
	public OutputStream os;
	public InputStream is;
	public Scanner sin;

	public IOStream(Socket socket) throws Exception {
		os = socket.getOutputStream();
		is = socket.getInputStream();
		sin = new Scanner(System.in);
	}

	public String getMessage() throws Exception {
		String message = "";
		int c;
		while ((c = is.read()) != 255) {// 255��Ϊ�Ľ�����
			message += (char) c;
		}
		return message;
	}
}
