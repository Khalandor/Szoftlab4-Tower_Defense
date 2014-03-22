import java.util.ArrayList;

public class PathTile extends Tile {
	private PathTile nextTile;
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	/**
	 * A PathTile osztály konstruktora.
	 * Beállítja típusát, és a Geometry-re mutató referenciáját.
	 * @param geometry Az a Geometry, ami őt létrehozta (és tárolja)
	 */
	public PathTile (Geometry geometry)
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
		System.out.println("<-- " + enemyList.get(0));
		return enemyList.get(0);
	}

	/**
	 * Visszaadja a következő útcsempét
	 * @return Az úton ezután következő csempe
	 */
	public Tile getNextTile() {
		System.out.println("--> PathTile.getNextTile()");
		System.out.println("<-- " + nextTile);
		return nextTile;
	}

	/**
	 * Törli a csempéről a paraméterül átadott ellenséget
	 * @param enemy a törölt ellenség
	 */
	public void removeEnemy(Enemy enemy) {
		System.out.println("--> PathTile.RemoveEnemy()");
		System.out.println("<-- ");
		enemyList.remove(enemy);
	}
}