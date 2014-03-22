import java.util.ArrayList;

public class Geometry {
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	/**
	 * A Geometry osztály konstruktora.
	 * Létrehozza a csempéket.
	 */
	public Geometry() {
		System.out.println("--> Geometry()");
		for(int i = 0; i < 3; i++){				
			tiles.add(new FieldTile(this));		//Létrehozunk 3 csempét, melyekre tornyokat lehet építeni
			tiles.add(new PathTile(this));		//Létrehozunk 3 útcsempét
		}
		tiles.add(new EndTile(this));			//Létrehozzuk a végzet hegyét
	}
	
	/**
	 * Egy csempe adott sugarú körén belüli csempéit adja vissza
	 * @param center a csempe, amelyikről a lövést indítjuk
	 * @param range a lövés hatótáva (kör sugara)
	 * @return azok a csempék, amik elérhetőek az adott pályán a megadott paraméterekkel 
	 */
	public ArrayList<PathTile> getNearby(Tile center, int range) {
		System.out.println("-->getNearby(" + center + "," + range + ")");
		System.out.println("<-- "+ tiles);
		PathTile tesztTarget = (PathTile)tiles.get(0);
		ArrayList<PathTile> targets = new ArrayList<PathTile>();
		targets.add(tesztTarget);
		return targets;	
	}
	
	/**
	 * Visszaadja az összes csempét
	 * @return Az összes csempe
	 */
	public ArrayList<Tile> getTiles() { 
		return tiles;							//Visszaadja az összes csempét
	}
}

