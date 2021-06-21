package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// 필드
	private Socket socket;

	// 생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 메소드 -gs

	// 메소드 -일반
	// 출장가서 해야할일 -메세지 주고받기
	@Override
	public void run() {
		// -메세지 주고받기
		// 메세지 받기용 스트림

		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {
				// 메세지 받기
				String msg = br.readLine();

				if (msg == null) {
					System.out.println("클라이언트 접속 종료");
					break;
				}

				System.out.println("받은메세지:" + msg);
				// 메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}

		} catch (IOException e) {
			System.out.println("에러발생");
		}

	}

}