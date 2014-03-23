public class Enemy {
	protected int health;
	protected int speed;
	protected int manaValue;
	protected String type;
	protected int moveDelay;
	protected Tile currentTile;
	public PathTile unnamed_PathTile_;
	public EndTile unnamed_EndTile_;
	public Updater unnamed_Updater_;


	/**
	 * value értékével csökkenti az életerejét
	 * @param value ennyivel sérül
	 */
	public void damage(int value) {
		System.out.println("--> Enemy.damage(" + value +")");
		if (value >= health)
			health = 0;
		else health -= value;
		System.out.println("<--");
	}

	/**
	 * Csökkenti a moveDelay attribútum értékét egyel.
	 */
	public void decreaseMoveDelay() {
		System.out.println("--> Enemy.decreaseMoveDelay");
		System.out.println("<--");
	}

	/**
	 * Az ellenség életerejével tér vissza
	 */
	public int getHealth() {
		System.out.println("--> Enemy.getHealth()");
		System.out.println("<--" + health);
		return this.health;
	}

	/**
	 * Visszaadja, hogy mennyi manát ér a megölése
	 */
	public int getManaValue() {
		System.out.println("--> Enemy.getManaValue()");
		System.out.println("<--" + manaValue);
		return this.manaValue;
	}

	/**
	 * Visszaadja a csempét melyen az ellenség jelenleg tartózkodik.
	 */
	public Tile getTile() {
		System.out.println("--> Enemy.getTile()");
		System.out.println("<--" + currentTile);
		return this.currentTile;
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
		
		String tileType = currentTile.getType();
		
		//FIXME BUG: ez bajlehet. itt előbbre kellett hoznom a getType-ot, mert csak a PathTile-nak van getNextTile-ja és removeEnemy()-je, máson nem lehet meghívni
		if (tileType == "PathTile")
		{
			Tile nextTile = ((PathTile) currentTile).getNextTile();
			nextTile.addEnemy(this);
			
			((PathTile) currentTile).removeEnemy(this);
			currentTile = nextTile;
		}
		else if (tileType == "EndTile")
		{
			//TODO TileType==végzethegye -> vesztés, return true;
		}
		
		Construct constructOnTile = currentTile.getConstruct();
		//FIXME BUG: itt lekérjük a típusát, hogy tudjuk, hogy Barricade-e, ez lemaradt az 5. szekv diagramról.
		if (constructOnTile != null && constructOnTile.getType() == "Barricade")
		{
			//TODO barrikádra lépett.
		}
		else 
			setMoveDelay(0);

		//visszatérés lemaradt a szekvenciadiagramról
		System.out.println("<-- false");
		return false;
	}

	/**
	 * Beállítja a moveDelay-t a sebesség és a kapott modifier összegére.
	 */
	public void setMoveDelay(int delay) {
		System.out.println("--> Enemy.setMoveDelay(" + delay + ")");
	}
}
