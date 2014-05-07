package game;

public abstract class Tile {
	protected String type;
	protected Geometry geometry;
	protected Construct constructOnTile;
	private String name;

	public Tile(Geometry geometry) {
		this.geometry = geometry;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A Tile-on egy Construct elhelyezeset szavatolja 
	 * @param construct ezt a Constructott helyezzuk el a Tile-re
	 */
	public void addConstruct(Construct construct) {
		constructOnTile = construct; //
	}

	/**
	 * Visszaadja a csempén lévő épületet.
	 * @return a csempén lévő épület.
	 */
	
	public Construct getConstruct() {
		return constructOnTile;
	}

	/**
	 * Visszaadja a csempét tároló Geometry-t
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * Visszaadja a csempe típusát.
	 */
	public String getType() {
		return type;
	}

	/**
	 * A kapott ellenséget felveszi az ellenségek közé, ha lehet rajta.
	 * @param enemy a kapott ellenség
	 */
	public void addEnemy(Enemy enemy) {
	}
}

