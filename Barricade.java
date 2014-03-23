public class Barricade extends Construct {
	private Object speedModifier;

	public int getSpeedModifier() {
		throw new UnsupportedOperationException();
	}
	
	public Barricade()
	{
		System.out.println("--> Barricade()");
		type = "Barricade";										//Beállítjuk a saját típusát
	}
}