import java.util.ArrayList;

public class EndTile extends Tile {
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	/**
	 * A PathTile osztály konstruktora.
	 * Beállítja típusát, és a Geometry-re mutató referenciáját.
	 * @param geometry Az a Geometry, ami őt létrehozta (és tárolja)
	 */
	public EndTile (Geometry geometry) {
		System.out.println("--> EndTile( " +geometry+ " )");	
		this.geometry = geometry;								//Beállítjuk az őt tároló Geometry-re mutató referenciát
		type = "EndTile";										//Beállítjuk a saját típusát
	}
	
	public void addEnemy(Enemy enemy) {
		System.out.println("--> EndTile.addEnemy(" + enemy +")");
		enemies.add(enemy);
		System.out.println("<--");
	}
}




