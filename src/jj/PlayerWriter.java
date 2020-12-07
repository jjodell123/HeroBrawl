package jj;

public class PlayerWriter {

	private int x, y;
	private String charaName;
	String name;
	boolean hit;

	public PlayerWriter(String name) {
		this.name = name;
	}

	public void setX(String x) {
		this.x = Integer.parseInt(x);
	}

	public void setY(String y) {
		this.y = Integer.parseInt(y);
	}

	public void setHit(String hit) {
		this.hit = Boolean.parseBoolean(hit);
	}

	public void setChara(String chara) {
		charaName = chara;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "~" + name + ":" + x + "," + y + "," + hit + "," + charaName;
	}

}
