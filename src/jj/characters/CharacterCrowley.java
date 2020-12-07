package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterCrowley extends CharacterType {

	public CharacterCrowley() {
		super("Jacob Crowley", 2.4, 75, 13, 60);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(255, 204, 204));
	}

}
