package draw;

import game.ConstructManager;
import game.Tile;
import game.Updater;

import java.util.Timer;
import java.util.TimerTask;

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

		view.drawAll();
		
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  System.out.println("Update called");
			    updater.update();
			  }
			}, 0, 1000);
	}
	
	public static void buildTower(Tile tile) {
		constructManager.build("tower", tile);
		view.drawAll();
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
