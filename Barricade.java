public class Barricade extends Construct {
	private int speedModifier;

	/**
	 * visszater a a lassitas mertekevel
	 * @return speedModifier  
	 */
	public int getSpeedModifier() {
		System.out.println("--> Barricade.getSpeedModifier()");
		System.out.println("<-- modifier");
		return speedModifier;
	}
	
	/**
	 * A Barricade konstruktora
	 */
	public Barricade()
	{
		System.out.println("--> Barricade()");
		type = "Barricade";										//Beállítjuk a saját típusát
		speedModifier = 2 ;
	}
}