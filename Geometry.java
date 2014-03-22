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
	
	public Tile[] getNearby(Tile centre, int range) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Visszaadja az összes csempét
	 * @return Az összes csempe
	 */
	public ArrayList<Tile> getTiles() { 
		return tiles;							//Visszaadja az összes csempét
	}
}