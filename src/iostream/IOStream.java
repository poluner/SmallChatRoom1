package iostream;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOStream {
	final static int EOSsize = 1000;// EndOfStream,��������־����
	public OutputStream os;
	public InputStream is;
	public Scanner sin;

	public IOStream(Socket socket) throws Exception {
		os = socket.getOutputStream();
		is = socket.getInputStream();
		sin = new Scanner(System.in);
	}

	static byte[] longToType(long l) {
		byte[] b = new byte[8];
		for (int i = 0; i < 8; i++) {
			b[i] = (byte) (l & 0xff);
			l >>= 8;
		}
		return b;
	}

	static long typeToLong(byte[] b) {
		long l = 0;
		for (int i = 7; i >= 0; i--) {
			l <<= 8;
			l |= b[i] & 0xff;
		}
		return l;
	}

	public void fileToStream(String pathName) throws Exception {// ���ļ�pathNameд�뵽����
		File f = new File(pathName);

		String fileType = pathName.substring(pathName.indexOf('.'), pathName.length());

		byte b[] = fileType.getBytes("GBK");

		os.write(longToType(f.length()));
		os.write(b.length);
		os.write(b);// ����Ϊb.length

		FileInputStream fis = new FileInputStream(f);

		int c;
		while ((c = fis.read()) != -1) {// ����Ϊf.length()
			os.write(c);
		}
		System.out.println("done");
		fis.close();
		os.flush();
	}

	public String fileFromStream() throws Exception {// �����л���ļ��������ļ���
		byte tb[] = new byte[8];
		for (int i = 0; i < 8; i++) {
			tb[i] = (byte) is.read();
		}
		long fileLength = typeToLong(tb);

		int fileTypeLength = is.read();
		byte b[] = new byte[fileTypeLength];
		for (int i = 0; i < fileTypeLength; i++) {
			b[i] = (byte) is.read();
		}
		String fileType = new String(b, "GBK");
		String fileName = "tmp" + fileType;
		FileOutputStream fos = new FileOutputStream(fileName);
		while (fileLength-- > 0) {
			fos.write(is.read());
		}
		fos.close();
		return fileName;
	}
}
