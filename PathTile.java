import java.util.ArrayList;

public class PathTile extends Tile {
	private ArrayList<Tile> nextTiles = new ArrayList<Tile>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * A PathTile osztály konstruktora.
	 * Beállítja típusát, és a Geometry-re mutató referenciáját.
	 * @param geometry Az a Geometry, ami őt létrehozta (és tárolja)
	 */
	public PathTile(Geometry geometry)
	{
		super(geometry);								//Beállítjuk az őt tároló Geometry-re mutató referenciát
		type = "PathTile";										//Beállítjuk a saját típusát
	}

	/**
	 * Visszaad egy, az ezen a csempén lévő ellenséget
	 * @return Az úton lévő ellenség
	 */
	public Enemy getEnemy() {
		return enemies.get(0);
	}

	/**
	 * Visszaadja a következő útcsempét
	 * @return Az úton ezután következő csempe
	 */
	public ArrayList<Tile> getNextTiles() {
		return nextTiles;
	}
	
	/**
	 * Beállítja a csempéket, amikre az úton innen lépni lehet
	 * @param path ez lesz a nextTile uj erteke
	 */
	public void setNextTile(PathTile path) {
		nextTiles.add(path);
	}

	/**
	 * Törli a csempéről a paraméterül átadott ellenséget
	 * @param enemy a törölt ellenség
	 */
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}
	

	/**
	 * A kapott ellenséget felveszi az ellenségek közé.
	 * @param enemy a kapott ellenség
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
}