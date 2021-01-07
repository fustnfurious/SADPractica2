import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Client {
	
	protected String nick;
	protected MySocket s;

	public Client(String nick, MySocket s) {
		this.nick = nick;
		this.s = s;
	}
	
	public static void main(String args[]) {
		
		try {
			
			MySocket s = new MySocket(InetAddress.getByName(args[0]), Integer.parseInt(args[1]), args[2]);
			Client c = new Client(args[2], s);
			s.enviarMissatge(args[2]);
			c.new WriterThread(c.s).run();
			c.new ReaderThread(c.s).run();
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public class WriterThread implements Runnable {
		protected MySocket soc;
		Scanner sca = new Scanner(System.in);
		
		public WriterThread(MySocket s) {
			this.soc = s;
		}
		
		public void enviarMissatge(String s) {
			soc.enviarMissatge(s);
		}

		@Override
		public void run() {
			while(true) {
				String m = sca.nextLine();
				enviarMissatge(m);
			}
			
		}
	}
	
	
	
	public class ReaderThread implements Runnable {
		protected MySocket soc;
		protected HashMap<String, Integer> nickColorMap;
		protected Random rand = new Random();
		
		public ReaderThread(MySocket s) {
			this.soc = s;
		}
		
		@Override
		public void run() {
			while(true) {
				repMissatge();
			}
			
		}
		
		public void repMissatge() {
				String mstr = soc.rebreMissatge();
				Missatge m = stringToMiss(mstr);
				String mToPrint = m.getData()+ "    " + "\u001B[" + nickToColor(m.getNick()) + "m" + m.getNick() + "\u001B[0m" + ": "+m.getMissatge();
				System.out.println(mToPrint);
		}
		
		public Missatge stringToMiss(String str) {
			String[] parts = str.split("+");
			return new Missatge(parts[0], parts[1], parts[2]);
		}
		
		public String dataStrToHora(String data) {
			return data.split(" ")[3].split(":")[0]+":"+data.split(" ")[3].split(":")[1]; //amb regex seria mes bonic pero fa mandra
		}
		
		public int nickToColor(String nick) {
			if(nickColorMap.get(nick) != null) {
				return nickColorMap.get(nick);
			} else {
				int randColor = rand.nextInt(10);
				nickColorMap.put(nick, randColor);
				return randColor;
			}
		}
	}
	
	
	
	
	
}
