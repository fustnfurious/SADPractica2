public class Missatge {
	protected String missatge;
	protected String nick;
	protected String data;
	
	public Missatge (String missatge, String nick, String data) {
		this.missatge = missatge;
		this.nick = nick;
		this.data = data;
	}
	
	public String missToString() {
		String miss = this.missatge + "+" + this.nick + "+" + this.data.toString();
		return miss;
	}
	
	public String toString() {
		String m = "Missatge: "+this.missatge + "\nData: " + this.data + "\nNick: "+this.nick;
		return m;
	}
	
	public String getMissatge() {
		return missatge;
	}

	public void setMissatge(String missatge) {
		this.missatge = missatge;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
