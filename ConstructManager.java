import java.util.HashMap;
import java.util.Map;

public class ConstructManager {
	public Map<String, Integer> costs = new HashMap<String, Integer> ();
	private Mana mana;
	private Updater updater;

	/**
	 * A ConstructManager osztály konstruktora.
	 * Beállítja az updater attribútumot.
	 * @param updater Az updater ami létrehozta
	 */
	public ConstructManager (Updater updater){
		this.updater = updater;							//Beállítjuk az updater attribútumot
		costs.put("Tower", 50);
		costs.put("Barricade", 30);
		costs.put("Range", 20);
		costs.put("FireRate", 20);
		costs.put("Damage", 20);
		costs.put("Slow", 20);
	}	
	
	/**
	 * egy épület létrehozása
	 * @param type - az épület típusa, ami alapján tudja a függvény, mit hozzon létre
	 * @param location - egy csempe, ahova az adott épülettípust elhelyezzük
	 */
	public void build(String type, Tile location) {
		if (mana.hasEnough(costs.get(type))) {
			location.getType();
			Construct construct = null;
			if(type == "Tower") {
				construct = new Tower();  
			}
			if(type == "Barricade") {
				construct = new Barricade();
			}
			location.addConstruct(construct);
			mana.decrease(costs.get(type));
			updater.addConstruct(construct);
		}		
	}

	/**
	 * Az adott épület fejlesztése varázskő alapján
	 * @param type - ennek megfelelően fejleszti az adott épületet a varázskővel
	 * @param construct az épület, amelyen a fejlesztést véghezvisszük
	 */
	public void upgrade(String type, Construct construct) {
		if (mana.hasEnough(costs.get(type))) {
			MagicGem gem = new MagicGem(type);
			construct.setMagicGem(gem);
			if(type == "Range") {
				((Tower)construct).setRange(20);
			}
			if(type == "FireRate") {
				((Tower)construct).setFireRate(10);
			}
			if(type == "Slow") {
				((Barricade)construct).setSpeedModifier(20);
			}
			mana.decrease(costs.get(type));
		}
	}

	/**
	 * A mana beállítása
	 * @param mana - az adott mana, amit beállítunk
	 */
	public void setMana(Mana mana) {
		this.mana = mana;
	}
}