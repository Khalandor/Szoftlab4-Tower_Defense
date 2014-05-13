package game;

public class Mana {
	private int mana;


	/**
	 * A mana erteket csokknti a parameter altal
	 * meghatarozott mennyiseggel
	 * @param cost ezzel az ertekkel kel csokkenteni a mana erteket
	 */
	public void decrease(int cost) {
		mana -= cost;
	}

	/**
	 * Lekerdezheto hogy a parameterkent kapott value erteknel nagyobb a mana erteke
	 *
	 * @param value ezzel az ertekkel tortenik az ossehasonlitas
	 * @return true ha van eleg mana.
	 */
	public Boolean hasEnough(int value) {
		return (value <= mana);
	}

	/**
	 * Mana erteket lehet beallitani altala
	 * @param value ezzel az ertekkel lesz egyenlo a mana
	 */
	public void setMana(int value) {
		mana = value;
	}

	/**
	 * Hozzaadja a jelenlegi varazserohoz a kapott erteket
	 * @param value ezzel inkrementál
	 */
	public void increase(int value) {
		mana += value;
	}

	public int getMana() {
		return mana;
	}

}
