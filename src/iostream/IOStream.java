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

	public void addEOS() throws Exception {
		for (int i = 0; i < EOSsize; i++) {
			os.write(255);
		}
	}

	public void fileToStream(String pathName) throws Exception {// ���ļ�pathNameд�뵽����
		String fileType = pathName.substring(pathName.indexOf('.'), pathName.length());

		os.write(fileType.getBytes());// �����ļ�����
		os.write(128);// �����ļ������������ַ�����Χ��0~127����128��Ϊ�ָ�������

		FileInputStream fis = new FileInputStream(pathName);
		int c;
		while ((c = fis.read()) != -1) {// �ļ�������־��-1
			os.write(c);
		}
		fis.close();
	}

	public String fileFromStream() throws Exception {// �����л���ļ��������ļ���
		String fileType = "";// �ļ�����
		int c0;
		while ((c0 = is.read()) != 128) {// �ļ����������ַ���Χ0~127��������128��Ϊ�ָ���
			fileType += (char) c0;
		}

		String fileName = "tmp" + fileType;
		FileOutputStream fos = new FileOutputStream(fileName);

		int c[] = new int[EOSsize];
		while (true) {
			for (int i = 0; i < EOSsize; i++)
				c[i] = -1;

			for (int i = 0; i < EOSsize; i++) {
				if ((c[i] = is.read()) != 255)
					break;
			}
			if (c[EOSsize - 1] == 255)
				break;
			for (int i = 0; i < EOSsize; i++) {
				if (c[i] == -1)
					break;
				fos.write(c[i]);
			}
		}
		fos.close();
		return fileName;
	}
}