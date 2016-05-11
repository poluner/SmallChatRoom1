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

	public void fileToStream(String pathName) throws Exception {// ���ļ�pathNameд�뵽����
		File f = new File(pathName);
		os.writeLong(f.length());// �ϴ��ļ�����long

		String fileType = pathName.substring(pathName.indexOf('.'), pathName.length());
		byte b[] = fileType.getBytes("GBK");
		os.writeInt(b.length);// �ϴ��ļ����ͳ���int

		os.write(b);// �ϴ��ļ�����

		FileInputStream fis = new FileInputStream(f);
		int c;
		while ((c = fis.read()) != -1) {// �ϴ��ļ�
			os.write(c);
		}
		fis.close();
		os.flush();
	}

	public String fileFromStream() throws Exception {// �����л���ļ��������ļ���
		long fileLength = is.readLong();// �����ļ�����
		int fileTypeLength = is.readInt();// �����ļ����ͳ���

		byte b[] = new byte[fileTypeLength];
		is.read(b, 0, fileTypeLength);// �����ļ����ͣ��ļ����ͳ���һ��̣ܶ�����һ��д������
		String fileType = new String(b, "GBK");

		String fileName = "tmp" + fileType;
		FileOutputStream fos = new FileOutputStream(fileName);
		while (fileLength-- > 0) {// �����ļ���д�뱾��
			fos.write(is.read());
		}
		fos.close();
		return fileName;
	}
}
