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
		System.out.println("--> ConstructManager()");
		this.updater = updater;							//Beállítjuk az updater attribútumot
	}	
	
	public void build(String type, Tile center) {
		throw new UnsupportedOperationException();
	}

	public void upgrade(String type, Construct construct) {
		throw new UnsupportedOperationException();
	}
}