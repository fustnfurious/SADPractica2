import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server {
	protected HashMap<String, MySocket> map;
	protected LlistaMissatges list;
	protected MyServerSocket ss;
	
	public Server() {
		this.list = new LlistaMissatges();
		this.map = new HashMap<>();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Server server = new Server();
		server.ss = new MyServerSocket(Integer.parseInt(args[0]));
		MySocket s;
		while(true) {
			s = server.ss.accept();
			String nick = s.rebreMissatge();
			server.map.put(nick, s);
		}
		
	}
	
	
	
	public class broadcastSenderThread extends Thread {
		protected HashMap<String, MySocket> map;
		protected LlistaMissatges list;
		
		public broadcastSenderThread(LlistaMissatges list, HashMap<String, MySocket> map) {
			this.list = list;
			this.map = map;
		}
		
		public void run() {
			while(true) {
				Missatge m = list.getNextToBroad();
				broadcastMissatge(m, m.getNick());
			}
		}
		
		public void broadcastMissatge(Missatge m, String nick) {
			for (Map.Entry<String, MySocket> entry : map.entrySet()) {
				if(entry.getKey()!=nick) {
					entry.getValue().enviarMissatge(m.missToString());
				}
			}
		}
	}
	
	public class ClientReaderThread extends Thread {
		protected String nick;
		protected MySocket soc;
		protected LlistaMissatges list;
		
		public ClientReaderThread(String nick, MySocket s, LlistaMissatges list) {
			this.nick = nick;
			this.soc = s;
			this.list = list;
		}
		
		public void run() {
			while(true) {
				rebreMissatge();
			}
		}
		
		public void rebreMissatge() {
			String s = soc.rebreMissatge();
			list.add(new Missatge(s, this.nick, (new Date()).toString()));
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
