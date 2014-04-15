import java.util.ArrayList;

public class Enemy {
	protected int health;
	protected int speed;
	protected int manaValue;
	protected String type;
	protected int moveDelay;
	protected Tile currentTile;


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

	private Tile chooseTile(ArrayList<Tile> Tiles)
	{
		return Tiles.get(0);
	}
	/**
	 *  Lépteti az ellenséget a currentTile-ról a currentTile nextTile-jára. 
	 *  Megnézi, hogy a végzet hegyére léptünk-e, ha igen akkor true-val tér vissza. 
	 *  Lépés után meghívja a setMoveDelay-t az esetleges akadálytól kapott lassítás mértékével.
	 *  @return a végzet hegyén áll-e az ellenség?
	 */
	public boolean move() {
		decreaseMoveDelay();
		
		if(moveDelay==0){
			Tile nextTile = chooseTile(((PathTile) currentTile).getNextTiles());
			nextTile.addEnemy(this);
			((PathTile) currentTile).removeEnemy(this);
			currentTile = nextTile;
			
			String tileType = currentTile.getType();
			if (tileType == "EndTile")
			{
				//vesztés, return true;
				return true ;
			}
			
			
			Construct constructOnTile = currentTile.getConstruct();
			if (constructOnTile != null)
			{
				
				int modifier = ((Barricade)(constructOnTile)).getSpeedModifier();
				setMoveDelay(modifier);
			}
			else 
				setMoveDelay(0);
		}
		return false;
		
	}

	/**
	 * Beállítja a moveDelay-t a sebesség és a kapott modifier összegére.
	 */
	public void setMoveDelay(int delay) {
		moveDelay = delay;
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
