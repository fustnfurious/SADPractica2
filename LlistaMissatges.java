import java.util.ArrayList;


public class LlistaMissatges {
	protected ArrayList<Missatge> miss;
	protected int numToBroadcast;
	protected boolean nou;
	
	
	public LlistaMissatges() {
		miss = new ArrayList<>();
		numToBroadcast = 0;
		nou = false;
	}
	
	synchronized void add(Missatge m) {
		miss.add(m);
		nou = true;
		notifyAll();
	}
	
	synchronized Missatge getNextToBroad() {
		while(!nou){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Missatge m = miss.get(numToBroadcast);
		numToBroadcast++;
		nou=false;
		return m;
	}
}
