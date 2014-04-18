public class MagicGem {
	private String type;
	public Construct construct;
	
	/**
	 * MagicGem konstruktora.
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
		return 10;
	}
}