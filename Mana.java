public class Mana {
	private int mana;
	public ConstructManager unnamed_ConstructManager_;
	public Updater unnamed_Updater_;

	public void decrease(int value) {
		//throw new UnsupportedOperationException();
	}

	public Boolean hasEnought(int value) {
		//throw new UnsupportedOperationException();
		System.out.println("--> Mana.hasEnought(" + value + ")");
		System.out.println("<--" + (value <= mana));
		return (value <= mana);
	}
	
	public void setMana(int value) {
		mana = value;
	}

	/**
	 * Hozzáadja a jelenlegi varázserőhöz a kapott értéket
	 * @param value ezzel inkrementál
	 */ 
	public void increase(int value) {
		System.out.println("--> Mana.increase(" + value + ")");
		System.out.println("<--");
	}
}
