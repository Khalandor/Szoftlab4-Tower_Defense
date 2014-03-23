import java.util.ArrayList;

public class PathGenerator {
	private ArrayList<PathTile> pathStarts = new ArrayList<PathTile>();

	/**
	 * A PathGenerator osztaly konstruktora.
	 * Letrehozza a kapott geometry segitsegevel az utvonalakat, melyeken ellensegeket indithat el.
	 * @param geometry Palyat reprezentáló változó, melyen keresztül elérjük a csempeket
	 */
	public PathGenerator (Geometry geometry){
		System.out.println("--> PathGenerator( " +geometry+ " )");
		geometry.getTiles();										//Elkérjük az összes csempét
	}	
	
	
	/**
	 * A parameterkent kapott enemy-t rarakja valamelyik kezdo csempere
	 * @param enemy ezt a peldanyt rakja ra a csempere
	 */
	public void start(Enemy enemy) {
		System.out.println("--> PathGenerator.start(Enemy)");
		pathStarts.get(0).addEnemy(enemy);
		System.out.println("<--");
	}
	
	
	/**
	 * A kezdo csempek PathGenerator-ba regisztralasat vegzi 
	 * @param pathTile ez a csempe  amelyet beregisztral
	 */
	public void add(PathTile pathTile) {
		pathStarts.add(pathTile);
	}
}