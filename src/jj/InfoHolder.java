package jj;

import java.util.ArrayList;

import jj.levels.LevelInfo;
import jj.tiles.Grid;

public class InfoHolder {

	private static ArrayList<LevelInfo> mapInfo = new ArrayList<LevelInfo>();
	private static int current;

	// Sets map info
	public void addInfo(int map, int startPoint[], int startHeight[], int length, int height) {
		mapInfo.add(new LevelInfo(map, startPoint, startHeight, length, height));
	}

	// Sets map number
	public void setMap(int num) {
		current = num - 1;
	}

	// Returns the map info
	public int getMap() {
		return mapInfo.get(current).getMap();
	}

	public int getStartPoint(int map) {
		return mapInfo.get(map).getStartPoint()[0];
	}

	public int[] getStartPoints(int map) {
		return mapInfo.get(map).getStartPoint();
	}

	public int getStartHeight(int map) {
		return mapInfo.get(map).getStartHeight()[0];
	}

	public int[] getStartHeights(int map) {
		return mapInfo.get(map).getStartHeight();
	}

	public Grid getGrid(int map) {
		return mapInfo.get(map).getGrid();
	}

	public int getLeftCorner(int map) {
		return mapInfo.get(map).getLeftCorner();
	}

	public int getRightCorner(int map) {
		return mapInfo.get(map).getRightCorner();
	}

	public int getTopCorner(int map) {
		return mapInfo.get(map).getTopCorner();
	}

	public int getBottomCorner(int map) {
		return mapInfo.get(map).getBottomCorner();
	}
}
