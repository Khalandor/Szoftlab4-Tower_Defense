public class MagicGem {
	public String type;
	public Construct construct;
	private String EnemyType;
	
	
	/**
	 * 
	 * A MAgicGem konstruktora
	 * 
	 * @param t az ellenseg tipusat jelolo String
	 */
	public MagicGem (String t)
	{
		type = "MagicGem";
		this.EnemyType = t;
	}
	
	/**
	 *  Visszaadja a bonusz sebzes erteket amit a MagicGem
	 *  tud kiosztani a type altal deffinialt ellenseg ellen
	 * 
	 * @param type az ellenseget tipusat adja meg 
	 */
	public int getDamageBonus(String type) {
		if (EnemyType.equals(type)) 	return 10;
		else return 0;
	}
}