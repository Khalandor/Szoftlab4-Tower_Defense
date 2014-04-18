import java.util.ArrayList;

public class Enemy {
	public int health;
	protected int speed;
	protected int manaValue;
	protected String type;
	public int moveDelay;
	public Tile currentTile;


	/**
	 * value értékével csökkenti az életerejét
	 * @param value ennyivel sérül
	 */
	public void damage(int value) {
		if (value >= health)
			health = 0;
		else health -= value;
	}
	
	/**
	 * Felezi az életerejét
	 */
	public void damageHalf()
	{
		health /= 2;
	}

	/**
	 * Csökkenti a moveDelay attribútum értékét egyel.
	 */
	public void decreaseMoveDelay() {
		if(moveDelay!=0){
			moveDelay--;
		}
	}

	/**
	 * Az ellenség életerejével tér vissza
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Visszaadja, hogy mennyi manát ér a megölése
	 */
	public int getManaValue() {
		return this.manaValue;
	}

	/**
	 * Visszaadja a csempét melyen az ellenség jelenleg tartózkodik.
	 */
	public Tile getTile() {
		return this.currentTile;
	}
	
	/**
	 * Az ellenség típusát adja vissza
	 * @return maga a típus
	 */
	public String getType() {
		return this.type;
	}

	/**
	 *  Lépteti az ellenséget a currentTile-ról a currentTile nextTile-jára. 
	 *  Megnézi, hogy a végzet hegyére léptünk-e, ha igen akkor true-val tér vissza. 
	 *  Lépés után meghívja a setMoveDelay-t az esetleges akadálytól kapott lassítás mértékével.
	 *  @return a végzet hegyén áll-e az ellenség?
	 */
	public boolean move() {
		decreaseMoveDelay(); //csökkenti a késleltetést
		
		if (moveDelay==0) { //ha 0 a késleltetés
			if (((PathTile) currentTile).getNextTiles().size()>0) {
				ArrayList<Tile> nextTile  = ((PathTile) currentTile).getNextTiles(); //lekéri az elérhető cellákat
				((PathTile) currentTile).removeEnemy(this); //eltávolítja magát az aktuális celláról
				setTile(nextTile.get((int) (Math.random() % nextTile.size()))); //véletlenszerűen választ egy cellát a listából és beállítja aktuálisnak
				currentTile.addEnemy(this); //hozzáadja magát az akutális csempéhez
				if (currentTile.getType().equals("EndTile")) { //ha végzet hegyére lépett, akkor igazzal visszatér -> vége a játéknak
					return true;
				}
				Construct construct = currentTile.getConstruct(); //lekéri az épületet az adott celláról
				if (construct!=null &&construct.getType().equals("Barricade")) { //ha van épület a cellán és az akadály
					setMoveDelay((((Barricade) construct).getSpeedModifier())); //megszorozza az aktuális delay-t az akadály lassításával
				}
			}
		}
		setMoveDelay(speed);
		return false;
	}

	/**
	 * Beállítja a moveDelay-t a sebesség és a kapott modifier összegére.
	 */
	public void setMoveDelay(int delay) {
		moveDelay = speed + delay;
	}
	
	/**
	 * Beállítja az ellenség életerejét
	 * @param health az új életereje
	 */
	public void setHealth(int health){
		this.health = health;
	}
	
	/**
	 * Az ellenséghez hozzárendeli a csempét, amin tartózkodik
	 * @param tile - az adott csempe, amin az ellenség áll
	 */
	public void setTile(Tile tile){
		currentTile=tile;
	}
}
