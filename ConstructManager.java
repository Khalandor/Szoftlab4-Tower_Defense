public class ConstructManager {
	private int costs;
	private String[] types;
	private Mana mana;
	private Updater updater;

	/**
	 * A ConstructManager osztály konstruktora.
	 * Beállítja az updater attribútumot.
	 * @param updater Az updater ami létrehozta
	 */
	public ConstructManager (Updater updater){
		this.updater = updater;							//Beállítjuk az updater attribútumot
	}	
	
	/**
	 * egy épület létrehozása
	 * @param type - az épület típusa, ami alapján tudja a függvény, mit hozzon létre
	 * @param location - egy csempe, ahova az adott épülettípust elhelyezzük
	 */
	public void build(String type, Tile location) {
		System.out.println("--> ConstructManager.build("+type+", "+location+")");
		int cost = 20;
		if (mana.hasEnough(cost)) {
			location.getType();
			Construct construct = null;//
			if(type == "Tower") {//
				construct = new Tower();  //
			}//
			if(type == "Barricade") {//
				construct = new Barricade();//
			}//
			location.addConstruct(construct);
			mana.decrease(cost);
			updater.addConstruct(construct);
		}		
	}

	/**
	 * Az adott épület fejlesztése varázskő alapján
	 * @param type - ennek megfelelően fejleszti az adott épületet a varázskővel
	 * @param construct az épület, amelyen a fejlesztést véghezvisszük
	 */
	public void upgrade(String type, Construct construct) {
		System.out.println("--> ConstructManager.upgrade(gemType, "+construct+")");
		int cost = 20;
		if (mana.hasEnough(cost)) {
			if(construct.getType() == "Tower"){
				MagicGem gem = new MagicGem();
				construct.setMagicGem(gem);
				if(type == "Range") {
					((Tower)construct).setRange(20);
				}
			}
			mana.decrease(cost);
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