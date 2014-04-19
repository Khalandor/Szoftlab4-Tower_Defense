public class Human extends Enemy {

	/**
	 *  Human osztaly konstruktora.
	 */
	public Human(EnemyGenerator enemyGenerator) {
		super(enemyGenerator);
		this.type = "Human";
		this.health = 50;
		this.manaValue = 7;
		this.speed = 3;
	}
}