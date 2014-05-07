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
		
		/*TODO szerintem jobb felvenni egy ilyen függvényt az Updaterbe,
		*mint ha itt hoznánk létre a ConstructManagert 
		*és adnánk hozzá az Updaterhez*/
		constructManager = updater.getConstructManager(); 	
		
		Drawable test = new HobbitView();
		Drawable test2 = new HumanView();
		view.addView(test);
		view.addView(test2);
		view.drawAll();
	}
	
	public static void buildTower(Tile tile) {
		System.out.println("Build Tower");
		constructManager.build("tower", tile);
	}
	
	public static void buildBarricade(Tile tile) {
		System.out.println("Build Barricade");
		constructManager.build("barricade", tile);
	}
	
	public static void upgrade(Tile tile, String type) {
		System.out.println("Upgrade: "+type);
		type = type.toLowerCase();
		type=type.replaceAll("\\s","");
		constructManager.upgrade(type, tile.getConstruct());
	}
}
