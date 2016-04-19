package iostream;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOStream {
	public PrintWriter os;
	public BufferedReader is;
	public Scanner sin;

	public IOStream(Socket socket) throws Exception {
		os = new PrintWriter(socket.getOutputStream());// 由Socket对象得到输出流，并构造PrintWriter对象
		is = new BufferedReader(new InputStreamReader(socket.getInputStream()));// 由Socket对象得到输入流，并构造相应的BufferedReader对象
		sin = new Scanner(System.in);
	}
}
