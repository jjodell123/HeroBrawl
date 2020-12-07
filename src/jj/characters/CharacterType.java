package jj.characters;

import java.awt.Graphics2D;

public class CharacterType {

	private final String name;
	private int height, length, jumpLength;
	private double speed;

	public CharacterType(String name, double speed, int height, int length, int jumpLength) {
		this.name = name;
		this.speed = speed;
		this.height = height;
		this.length = length;
		this.jumpLength = jumpLength;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return name.substring(0, name.indexOf(" "));
	}

	public int getHeight() {
		return height;
	}

	public int getLength() {
		return length;
	}

	public double getSpeed() {
		return speed;
	}

	public int getJumpLength() {
		return jumpLength;
	}

	public void draw(Graphics2D g) {

	}
}
