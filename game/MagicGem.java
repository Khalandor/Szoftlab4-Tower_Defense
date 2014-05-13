package game;

public class MagicGem {
	private String type;

	/**
	 *
	 * A MagicGem konstruktora.
	 *
	 * @param type az ellenseg tipusat jelolo String
	 */
	public MagicGem (String type)
	{
		this.type = type;
	}

	/**
	 *  Visszaadja a bonusz sebzes erteket amit a MagicGem
	 *  tud kiosztani a type altal deffinialt ellenseg ellen
	 *
	 * @param type az ellenseget tipusat adja meg
	 */
	public int getDamageBonus(String type) {
		if (this.type.equals(type)) return 10;
		else return 0;
	}

	public String getType() {
		return type;
	}
}