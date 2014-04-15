public class Barricade extends Construct {
	private int speedModifier;

	/**
	 * visszater a a lassitas mertekevel
	 * @return speedModifier  
	 */
	public int getSpeedModifier() {
		return speedModifier;
	}
	
	/**
	 * A Barricade konstruktora
	 */
	public Barricade()
	{
		type = "Barricade";										//Beállítjuk a saját típusát
		speedModifier = 2 ;
	}
}