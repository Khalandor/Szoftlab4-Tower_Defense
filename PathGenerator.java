import java.util.ArrayList;

public class PathGenerator {
	private ArrayList<PathTile> pathStarts = new ArrayList<PathTile>();

	/**
	 * A PathGenerator osztály konstruktora.
	 * Létrehozza a kapott geometry segítségével az útvonalakat, melyeken ellenségeket indíthat el.
	 * @param geometry Pályát reprezentáló változó, melyen keresztül elérjük a csempéket
	 */
	public PathGenerator (Geometry geometry){
		System.out.println("--> PathGenerator( " +geometry+ " )");
		geometry.getTiles();										//Elkérjük az összes csempét
	}	
	
	public void start(Enemy enemy) {
		throw new UnsupportedOperationException();
	}
}