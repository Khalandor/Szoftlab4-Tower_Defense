import java.util.ArrayList;

public class Geometry {
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	/**
	 * A Geometry osztály konstruktora.
	 * Létrehozza a csempéket.
	 */
	public Geometry() {
		System.out.println("--> Geometry()");
	}
	
	/**
	 * Egy csempe adott sugarú körén belüli csempéit adja vissza
	 * @param center a csempe, amelyikről a lövést indítjuk
	 * @param range a lövés hatótáva (kör sugara)
	 * @return azok a csempék, amik elérhetőek az adott pályán a megadott paraméterekkel 
	 */
	public ArrayList<PathTile> getNearby(Tile center, int range) {
		System.out.println("--> Geometry.getNearby(" + center + "," + range + ")");
		// teszteléshez az utolsó cellát adja vissza (ezen álljon az ellenség, ha lőni akarunk rá)
		
		// a teszt target a tiles utolso eleme
		PathTile tesztTarget = (PathTile)tiles.get(tiles.size()-1);
		ArrayList<PathTile> targets = new ArrayList<PathTile>();
		// a targets lista az teszt target egz listaba agyazva
		targets.add(tesztTarget);
		//System.out.println("<-- " + targets);
		System.out.println("<-- tilesInRange");
		return targets;	
	}
	
	/**
	 * Visszaadja az összes csempét
	 * @return Az összes csempe
	 */
	public ArrayList<Tile> getTiles() { 
		System.out.println("--> getTiles()");
		System.out.println("<-- "+tiles);
		return tiles;							//Visszaadja az összes csempét
	}
	
	
	public ArrayList<Tile> getTilesList() { 
		return tiles;	
	}
}

