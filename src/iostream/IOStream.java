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
		byte b[]=new byte[102400];
		int cnt=0;		
		int c;
		while ((c = is.read()) != 255) {// 255作为的结束符
			b[cnt++]= (byte) c;
		}
		return new String(b,"GBK");
	}
}
