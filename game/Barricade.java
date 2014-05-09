package game;

public class Barricade extends Construct {
	private int speedModifier;
	private PathTile barricadeLocation;
	
	/**
	 * visszater a a lassitas mertekevel
	 * @return speedModifier  
	 */
	public int getSpeedModifier() {
		return speedModifier;
	}
	
	/**
	 * A Barricade konstruktora
	 * @param location 
	 */
	public Barricade(PathTile location)
	{
		type = "Barricade";										//Beállítjuk a saját típusát
		speedModifier = 2 ;
		barricadeLocation = location;
	}
	
	/**
	 * A Barricade lassitasanak beallitoja
	 * @param speedModifier a beallitando ertek
	 */
	public void setSpeedModifier(int speedModifier) 
	{	
		this.speedModifier = speedModifier;
	}

	public PathTile getBarricadeLocation(){
		return barricadeLocation;
	}
}