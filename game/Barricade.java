package game;

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
	
	/**
	 * A Barricade lassitasanak beallitoja
	 * @param speedModifier a beallitando ertek
	 */
	public void setSpeedModifier(int speedModifier) //TODO  nincs rajta a class diagramokon
	{	
		this.speedModifier = speedModifier;
	}
}