import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
	ServerSocket ss;
	public MyServerSocket(int port) throws IOException {
		this.ss = new ServerSocket(port);
		
	}
	
	public MySocket accept() {
		try {
			Socket s;
			s = this.ss.accept();
			MySocket soc = new MySocket(s);
			return soc;
		} catch (IOException e) {
			return null;
		}
		
	}

	

}
