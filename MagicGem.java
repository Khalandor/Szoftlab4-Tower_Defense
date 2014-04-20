public class MagicGem {
	public String type;
	public Construct construct;	
	
	/**
	 * 
	 * A MagicGem konstruktora.
	 * 
	 * @param t az ellenseg tipusat jelolo String
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
}