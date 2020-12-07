package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterAlex extends CharacterType {

	public CharacterAlex() {
		super("Alex Adragna", 3.0, 60, 10, 80);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(0, 255, 0));
	}
}
