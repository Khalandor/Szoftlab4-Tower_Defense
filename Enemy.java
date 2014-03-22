public class Enemy {
	protected int health;
	protected int speed;
	protected int manaValue;
	protected String type;
	protected int moveDelay;
	public PathTile unnamed_PathTile_;
	public EndTile unnamed_EndTile_;
	public Updater unnamed_Updater_;
	public Tile _currentTile;


	public Boolean damage(int value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Csökkenti a moveDelay attribútum értékét egyel.
	 */
	public void decreaseMoveDelay() {
		System.out.println("--> Elf.decreaseMoveDelay");
		moveDelay--;
	}

	public int getHealth() {
		return this.health;
	}

	public int getManaValue() {
		return this.manaValue;
	}

	public Tile getTile() {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		return this.type;
	}

	/**
	 *  Lépteti az ellenséget a currentTile-ról a currentTile nextTile-jára. Megnézi, hogy a végzet hegyére léptünk-e, ha igen akkor true-val tér vissza. Lépés után meghívja a setMoveDelay-t az esetleges akadálytól kapott lassítás mértékével.
	 *  @return a végzet hegyén áll-e az ellenség?
	 */
	public boolean move() {
		System.out.println("--> Enemy.move()");
		decreaseMoveDelay();
		Tile nextTile = currentTile.getNextTile();
		nextTile.addEnemy(this);
		currentTile.removeEnemy(this);
		currentTile = nextTile;
		String TileType = currentTile.getType();

		//TODO TileType=végzethegye -> vesztés
		
		Construct ConstructOnTile = currentTile.getConstruct();
		// itt lekérjük a típusát, hogy tudjuk, hogy Barricade-e, ez lemaradt az 5. szekv diagramról.
		if (ConstructOnTile.getType() == "Barricade")
		{
			//TODO barrikádra lép
		}
		else
		{
			//itt a szekv. diagram paraméter nélkül hívja, 0-val kéne 
			setMoveDelay(0);
		}


		//visszatérés lemaradt a szekvenciadiagramról
		System.out.println("<-- false");
		return false;
	}

	public void setMoveDelay(int delay) {
		throw new UnsupportedOperationException();
	}
}
