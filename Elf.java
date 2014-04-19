public class Elf extends Enemy {
	
	/**
	 * Az Elf konstruktora, amely beállítja az Enemy típusát 'Elf' -re
	 */
	public Elf(EnemyGenerator enemyGenerator) {
		super(enemyGenerator);
		this.type = "Elf";
		this.health = 20;
		this.manaValue = 5;
		this.speed = 1;
	}
}