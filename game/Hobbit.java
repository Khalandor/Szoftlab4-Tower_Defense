package game;

public class Hobbit extends Enemy {

	/**
	 *  Hobbit osztaly konstruktora.
	 */
	public Hobbit(EnemyGenerator enemyGenerator) {
		super(enemyGenerator);
		this.type = "hobbit";
		this.health = 20;
		this.manaValue = 3;
		this.speed = 4;
	}
}