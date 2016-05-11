package iostream;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOStream {
	public DataOutputStream os;
	public DataInputStream is;
	public Scanner sin;

	public IOStream(Socket socket) throws Exception {
		os = new DataOutputStream(socket.getOutputStream());
		is = new DataInputStream(socket.getInputStream());
		sin = new Scanner(System.in);
	}

	public void fileToStream(String pathName) throws Exception {// 将文件pathName写入到流中
		File f = new File(pathName);
		os.writeLong(f.length());// 上传文件长度long

		String fileType = pathName.substring(pathName.indexOf('.'), pathName.length());
		byte b[] = fileType.getBytes("GBK");
		os.writeInt(b.length);// 上传文件类型长度int

		os.write(b);// 上传文件类型

		FileInputStream fis = new FileInputStream(f);
		int c;
		while ((c = fis.read()) != -1) {// 上传文件
			os.write(c);
		}
		fis.close();
		os.flush();
	}

	public String fileFromStream() throws Exception {// 从流中获得文件，返回文件名
		long fileLength = is.readLong();// 下载文件长度
		int fileTypeLength = is.readInt();// 下载文件类型长度

		byte b[] = new byte[fileTypeLength];
		for (int i = 0; i < fileTypeLength; i++) {//用is.read(b,0,fileTypeLength);可能一次都不完，所以还不如用循环
			b[i] = is.readByte();
		}
		String fileType = new String(b, "GBK");

		String fileName = "tmp" + fileType;
		FileOutputStream fos = new FileOutputStream(fileName);
		while (fileLength-- > 0) {// 下载文件并写入本地
			fos.write(is.read());
		}
		fos.close();
		return fileName;
	}
}
