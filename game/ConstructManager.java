package game;

import java.util.HashMap;
import java.util.Map;

public class ConstructManager {
	private Map<String, Integer> costs = new HashMap<String, Integer> ();
	private Mana mana;
	private Updater updater;

	/**
	 * A ConstructManager osztály konstruktora.
	 * Beállítja az updater attribútumot.
	 * @param updater Az updater ami létrehozta
	 */
	public ConstructManager (Updater updater, Mana mana){
		this.updater = updater;							//Beállítjuk az updater attribútumot
		this.mana = mana;
		costs.put("tower", 50);
		costs.put("barricade", 30);
		costs.put("range", 20);
		costs.put("firerate", 20);
		costs.put("elf", 20);
		costs.put("dwarf", 20);
		costs.put("hobbit", 20);
		costs.put("human", 20);
		costs.put("slow", 20);
	}	
	
	/**
	 * egy épület létrehozása
	 * @param type - az épület típusa, ami alapján tudja a függvény, mit hozzon létre
	 * @param location - egy csempe, ahova az adott épülettípust elhelyezzük
	 */
	public void build(String type, Tile location) {
		if (mana.hasEnough(costs.get(type))) {
			Construct construct = null;
			
			if(type.equals("tower") && location.getType().equals("FieldTile")) {
				construct = new Tower((FieldTile) location);  
			} else
			if(type.equals("barricade") && location.getType().equals("PathTile")) {
				construct = new Barricade((PathTile) location);
			} else
				return;
			
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
			
			if(construct.getType().equals("Tower")) {
				
				if(type.equals("range")) {
					((Tower)construct).setRange(20);
					construct.setMagicGem(gem);
				}
				if(type.equals("firerate")) {
					((Tower)construct).setFireRate(2);
					construct.setMagicGem(gem);
				}
				if(type.equals("elf") || type.equals("hobbit") || type.equals("dwarf") || type.equals("human")) {
					construct.setMagicGem(gem);
				}
			}
			
			if(construct.getType().equals("Barricade")) {
				
				if(type.equals("slow")) {
					((Barricade)construct).setSpeedModifier(2);
					construct.setMagicGem(gem);
				}
			}
			mana.decrease(costs.get(type));
		}
	}
}