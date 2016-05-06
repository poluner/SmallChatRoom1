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
		int length=is.read();
		byte b[]=new byte[102400];
		for(int i=0;i<length;i++){
			b[i]=(byte) is.read();
		}
		return new String(b,"GBK");
	}
}
