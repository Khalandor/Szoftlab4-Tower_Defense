package draw;

import game.Tile;
import game.Updater;
import game.ConstructManager;

public class Controller {
	private static Updater updater;
	private static ConstructManager constructManager;
	private static View view;
	
	public static void main(String[] args) {
		view = new View();
		updater = new Updater();
		constructManager = updater.getConstructManager(); 	/*TODO szerintem jobb felvenni egy ilyen függvényt az Updaterbe,
															*mint ha itt hoznánk létre a ConstructManagert 
															*és adnánk hozzá az Updaterhez*/
	}
	
	public static void buildTower(Tile tile) {
		System.out.println("Build Tower");
	}
	
	public static void buildBarricade(Tile tile) {
		System.out.println("Build Barricade");
	}
	
	public static void upgrade(Tile tile, String type) {
		System.out.println("Upgrade: "+type);
	}
}
