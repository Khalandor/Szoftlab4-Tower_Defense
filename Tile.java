public class Tile {
	protected String type;
	protected Geometry geometry;
	protected Construct builton;
	protected Enemy EnemyOnTile;

	public Tile (){}
	
	public void addConstruct(Construct construct) {
	}

	/**
	 * Visszaadja a csempén lévő épületet.
	 * @return a csempén lévő épület.
	 */
	public Construct getConstruct() {
		throw new UnsupportedOperationException();
	}

	public Geometry getGeometry() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Visszaadja a csempe típusát.
	 * @return csempe típusa
	 */
	public String getType() {
		//TODO ezt egy private változóból tudja gondolom, azt a konstruktorban be kéne állítani.
		return Type;
	}

	/**
	 * A kapott ellenséget felveszi az ellenségek közé, ha lehet rajta.
	 * @param enemy a kapott ellenség
	 * @see Enemy
	 */
	public void addEnemy(Enemy enemy) {
		// FIXME ezt így írjuk ki?
		System.out.println("-->Tile.addEnemy(" + enemy.getType() + ")");
		EnemyOnTile = enemy;
	}
}
