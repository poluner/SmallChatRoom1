package client;

import java.net.*;

import iostream.*;

public class TalkClient {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);// �򱾻���4700�˿ڷ����ͻ�����
			IOStream ioStream = new IOStream(socket);
			while (true) {
				String message = ioStream.sin.nextLine();
				ioStream.os.println(message);// ����Ϣ�������
				ioStream.os.flush();// ˢ��ʹ�����������յ�
				System.out.println("sended!");
			}
		} catch (Exception e) {
			System.out.println("ErrorClient:" + e); // �������ӡ������Ϣ
		}
	}
}