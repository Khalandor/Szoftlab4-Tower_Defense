public class Hobbit extends Enemy {
	
	/**
	 *  Hobbit osztaly konstruktora.
	 */
	public Hobbit(EnemyGenerator enemyGenerator) {
		super(enemyGenerator);
		this.type = "Hobbit";
		this.health = 20;
		this.manaValue = 3;
		this.speed = 4;
	}
}