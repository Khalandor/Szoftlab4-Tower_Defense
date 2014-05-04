package game;

public class FieldTile extends Tile {
	
	/**
	 * A FieldTile osztály konstruktora.
	 * Beállítja típusát, és a Geometry-re mutató referenciáját.
	 * @param geometry Az a Geometry, ami őt létrehozta (és tárolja)
	 */
	public FieldTile (Geometry geometry)
	{
		super(geometry);								//Beállítjuk az őt tároló Geometry-re mutató referenciát
		type = "FieldTile";										//Beállítjuk a saját típusát
	}
}