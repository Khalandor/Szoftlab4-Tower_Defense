import java.util.ArrayList;

public class PathTile extends Tile {
	private Tile nextTile;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * A PathTile osztály konstruktora.
	 * Beállítja típusát, és a Geometry-re mutató referenciáját.
	 * @param geometry Az a Geometry, ami őt létrehozta (és tárolja)
	 */
	public PathTile(Geometry geometry)
	{
		System.out.println("--> PathTile( "+geometry+" )");
		this.geometry = geometry;								//Beállítjuk az őt tároló Geometry-re mutató referenciát
		type = "PathTile";										//Beállítjuk a saját típusát
	}

	/**
	 * Visszaad egy, az ezen a csempén lévő ellenséget
	 * @return Az úton lévő ellenség
	 */
	public Enemy getEnemy() {
		System.out.println("--> PathTile.getEnemy()");
		//System.out.println("<-- " + enemies.get(0));
		System.out.println("<-- Enemy");
		return enemies.get(0);
	}

	/**
	 * Visszaadja a következő útcsempét
	 * @return Az úton ezután következő csempe
	 */
	public Tile getNextTile() {
		System.out.println("--> PathTile.getNextTile()");
		System.out.println("<-- nextTile");
		return nextTile;
	}
	
	/**
	 * Beállitja a nextTile attributum erteket
	 * @param path ez lesz a nextTile uj erteke
	 */
	public void setNextTile(Tile path) {
		nextTile = path;
	}

	/**
	 * Törli a csempéről a paraméterül átadott ellenséget
	 * @param enemy a törölt ellenség
	 */
	public void removeEnemy(Enemy enemy) {
		System.out.println("--> PathTile.RemoveEnemy(" + enemy + ")");
		enemies.remove(enemy);
	}
	

	/**
	 * A kapott ellenséget felveszi az ellenségek közé.
	 * @param enemy a kapott ellenség
	 */
	public void addEnemy(Enemy enemy) {
		System.out.println("--> PathTile.addEnemy(" + enemy +")");
		System.out.println("<--");
		enemies.add(enemy);
	}
}