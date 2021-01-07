import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MySocket { 
	
	protected String nick;
	protected BufferedReader in;
	protected PrintWriter out;
	protected Socket s;
	
	public MySocket(InetAddress host, int port, String nick) throws IOException {
		this.s = new Socket(host, port);
		this.nick = nick;
		this.in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
		this.out = new PrintWriter(this.s.getOutputStream());
	}
	
	public MySocket(Socket s) {
		this.s = s;
	}
	
	public void enviarMissatge(String m){
		out.print(m);
		out.flush();
	}
	
	public String rebreMissatge() {
		try {
			String m = in.readLine();
			return m;
		} catch (IOException e) {
			return "Error";
		}
	}
	
}
