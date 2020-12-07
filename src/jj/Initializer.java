package jj;

import jj.characters.Character;
import jj.characters.CharacterJJ;
import jj.levels.One;

public class Initializer {

	InfoHolder info;
	PlayerInfo player;

	public Initializer() {
		info = new InfoHolder();
		loadCharacters();
		loadPlayer();
		loadInfo();
		initializeLevels();

	}

	// Loads level information
	public void initializeLevels() {
		new One();
	}

	// Initializes player information
	public void loadCharacters() {
		Character.loadCharacters();
	}

	// Sets player info
	public void loadPlayer() {
		player = new PlayerInfo(new CharacterJJ());

	}

	// Returns the player
	public PlayerInfo getPlayerInfo() {
		return player;
	}

	// For each level, this sets the locations of the blocks
	public void loadInfo() {
		info.addInfo(0,
				new int[] { 2600, 3560, 3360, 2200, 1460, 2480, 3560, 2640, 1800, 660, 1200, -100, -150, 360, 0 },
				new int[] { 411, 691, 791, 791, 671, 571, 331, 191, 171, 471, 551, 791, 611, 211, 311 }, 200, 40);
	}
}
