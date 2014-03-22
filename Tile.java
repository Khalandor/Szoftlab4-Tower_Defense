public class Tile {
	protected String type;
	public Geometry geometry;
	public Construct constructOnTile;

	public void addConstruct(Construct construct) {
	}

	/**
	 * Visszaadja a csempén lévő épületet.
	 * @return a csempén lévő épület.
	 */
	public Construct getConstruct() {
		System.out.println("--> Tile.getConstruct()");
		System.out.println("<-- " + constructOnTile);
		return constructOnTile;
	}

	/**
	 * Visszaadja a csempét tároló Geometry-t
	 */
	public Geometry getGeometry() {
		System.out.println("--> Tile.getGeometry()");
		System.out.println("<-- " + geometry);
		return geometry;
	}

	/**
	 * Visszaadja a csempe típusát.
	 */
	public String getType() {
		System.out.println("--> Tile.getType");
		System.out.println("<-- " + geometry);
		return type;
	}

	/**
	 * A kapott ellenséget felveszi az ellenségek közé, ha lehet rajta.
	 * @param enemy a kapott ellenség
	 */
	// FIXME Ez semmit nem csinál, mert itt nem tárolunk ellenséglistát, nincs is mihez hozzáadni. Overloadolni kell az EndTile-ban meg a Pathtile-ban, hogy csináljon is valamit. 
	public void addEnemy(Enemy enemy) {
		System.out.println("--> Tile.addEnemy(" + enemy +")");
		System.out.println("<--");
	}
}

