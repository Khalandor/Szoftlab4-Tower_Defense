public class Dwarf extends Enemy {
	
	/**
	 * A dwarf konstruktora, beállítja az enemy típusát 'Dwarf'-ra
	 */
	
	public Dwarf() {
		this.type = "Dwarf";
		this.health = 80;
		this.manaValue = 8;
		this.speed = 6;
	}
}