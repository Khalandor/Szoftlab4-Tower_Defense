package draw;

import game.ConstructManager;
import game.Tile;
import game.Updater;

public class Controller {
	private static Updater updater;
	private static ConstructManager constructManager;
	private static View view;

	public static void main(String[] args) {
		updater = new Updater();
		view = new View(updater);
		constructManager = updater.getConstructManager();
		view.drawAll();
	}

	public static void buildTower(Tile tile) {
		constructManager.build("tower", tile);
	}

	public static void buildBarricade(Tile tile) {
		constructManager.build("barricade", tile);
	}

	public static void upgrade(Tile tile, String type) {
		type = type.toLowerCase();
		type=type.replaceAll("\\s","");
		constructManager.upgrade(type, tile.getConstruct());
	}
}
