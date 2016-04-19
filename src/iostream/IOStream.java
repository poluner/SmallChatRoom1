package iostream;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOStream {
	final static int EOSsize=1000;//EndOfStream,流结束标志长度
	public OutputStream os;
	public InputStream is;
	public Scanner sin;

	public IOStream(Socket socket) throws Exception {
		os = socket.getOutputStream();
		is = socket.getInputStream();
		sin = new Scanner(System.in);
	}
	public void addEOS() throws Exception{
		for(int i=0;i<EOSsize;i++){
			os.write(255);
		}
	}

	public String getMessage() throws Exception {
		byte[] b = new byte[102400];
		int cnt = 0;
		
		int c[]=new int[EOSsize];
		while(true){
			for(int i=0;i<EOSsize;i++)
			c[i]=-1;
			for(int i=0;i<EOSsize;i++){
				if((c[i]=is.read())!=255)break;
			}
			if(c[EOSsize-1]==255)break;
			for(int i=0;i<EOSsize;i++){
				if(c[i]==-1)break;
				b[cnt++]=(byte)c[i];
			}
		}
		return new String(b, "GBK");
	}
}
