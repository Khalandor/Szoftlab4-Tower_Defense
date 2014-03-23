public class MagicGem {
	private String type;
	public Construct construct;
	
	/**
	 * MagicGem konstruktora.
	 */
	public MagicGem ()
	{
		System.out.println("--> MagicGem()");
		type = "MagicGem";
	}
	
	/**
	 *  Visszaadja a bonusz sebzes erteket amit a MagicGem
	 *  tud kiosztani a type altal deffinialt ellenseg ellen
	 * 
	 * @param type az ellenseget tipusat adja meg 
	 */
	public int getDamageBonus(String type) {
		System.out.println("--> MagicGem.getDamageBonus(type)");
		System.out.println("<-- amount");
		return 10;
	}
}