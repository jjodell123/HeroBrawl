package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterDeclan extends CharacterType {

	public CharacterDeclan() {
		super("Declan Sykes", 1.0, 75, 10, 60);
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
	}
}
