package game;

import java.util.ArrayList;

public class EndTile extends Tile {
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	/**
	 * A PathTile osztály konstruktora.
	 * Beállítja típusát, és a Geometry-re mutató referenciáját.
	 * @param geometry Az a Geometry, ami őt létrehozta (és tárolja)
	 */
	public EndTile (Geometry geometry) {
		super(geometry);									//Beállítjuk az őt tároló Geometry-re mutató referenciát
		type = "EndTile";									//Beállítjuk a saját típusát
	}
	/**
	 * Az adott csempé ellenség listájához hozzáad egy ellenséget, amennyiben az szükséges
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
}




