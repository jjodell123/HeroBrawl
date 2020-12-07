package jj;

import jj.characters.Character;
import jj.characters.CharacterType;

public class PlayerInfo {

	private static Character chara;

	public PlayerInfo(CharacterType charaType) {
		chara = new Character(charaType);
	}

	public Character getCharacter() {
		return chara;
	}

	public static int getHeight() {
		return chara.getHeight();
	}
}
