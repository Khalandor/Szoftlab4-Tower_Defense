import java.util.Random;

public class EnemyGenerator {
	private final int maxGeneratingSpeed = 10;
	private int maxEnemies = 30;

	private int generatingSpeed;
	private int placedEnemies;
	private PathGenerator pathGenerator;
	private Updater updater;

	/**
	 * Az EnemyGenerator konstruktora.
	 * Beállítja a pathGenerator referenciát.
	 * @param pathGenerator PathGenerator, melyen keresztül le fogja tenni az ellenségeket egy útvonalra.
	 * @param updater Updater, amire az új ellenségek felvételéhez van szükség
	 */
	public EnemyGenerator (PathGenerator pathGenerator, Updater updater){
		this.pathGenerator = pathGenerator;
		this.updater = updater;
	}

	/**
	 * Adott típusú ellenséget hoz létre
	 * @param type String, az ellenség típusa
	 * @return az új ellenség
	 * @throws Exception ha olyan ellenséget kérnek tőle, ami nincs
	 */
	private Enemy createEnemy(String type) throws Exception {
		Enemy newEnemy;
		if (type.equals("Dwarf"))
			return new Dwarf();
		else if (type.equals("Elf"))
			return new Elf();
		else if (type.equals("Hobbit"))
			return new Hobbit();
		else if (type.equals("Human"))
			return new Human();
		else
			throw new Exception("Unknown Enemy type");
	}

	/**
	 * Véletlenszerű ellenséget generál
	 * @return egy új Dwarf, Elf, Hobbit vagy Human
	 */
	private Enemy createRandomEnemy()
	{
		Random randomGenerator = new Random();
		int random = randomGenerator.nextInt(4);
		switch (random){
			case 0:
				return new Dwarf();
			case 1:
				return new Elf();
			case 2:
				return new Hobbit();
			case 3:
				return new Human();
		}
		return new Enemy();
	}

	/**
	 * Ellenségek létrehozására szolgáló függvény
	 * @return generált ellenségek
	 */
	public void generateEnemies() {
		//FIXME a szekvencia-diagramokon ez visszatér, a class diagramon meg void, szerintem jobb a void.
		for (int i = 0; i < generatingSpeed; i++)
		{
			Enemy newEnemy = createRandomEnemy();
			updater.addEnemy(newEnemy);
			pathGenerator.start(newEnemy);
		}
	}

	/**
	 *  Ha elértük a végleges generatingSpeed-et és az utolsó ellenséget is leraktuk, igazzal tér vissza. Egyébként hamis.
	 */
	public Boolean isLastEnemyGenerated() {
		if (generatingSpeed >= maxGeneratingSpeed && placedEnemies >= maxEnemies)
			return true;
		return false;
	}

	/**
	 * Lemásolja a paraméterül kapott ellenséget a paraméterül kapott csempére
	 */
	public void duplicateEnemy(Enemy enemy, PathTile pathTile) throws Exception {
		Enemy clonedEnemy = createEnemy( enemy.getType() ); // ugyanolyan típusú ellenség létrehozása
		clonedEnemy.setHealth(enemy.getHealth());	//azonos Health
		pathTile.addEnemy(clonedEnemy);		// rárakás a megadott útra
		updater.addEnemy(clonedEnemy);		// hozzáadás az ellenség-listához
	}

	/**
	 * Hozzáad a játékhoz egy adott csempére egy adott fajtájú ellenséget.
	 * @param tile A csempe, melyre az ellenséget tesszük le
	 * @param type Létrehozott ellenség típusa
	 */
	public void addEnemy(PathTile tile, String type) throws Exception {
		Enemy newEnemy = createEnemy( type );		// a megadott típusú ellenség létrehozása
		tile.addEnemy(newEnemy);		//rárakás a megadott útra
		updater.addEnemy(newEnemy);		// hozzáadás az ellenség-listához
	}

	/**
	 * Beállítja a még játék végéig legenerálandó ellenségek számát.
	 * @param remaining a hátralévő ellensége száma
	 */
	public void setRemainingEnemies(int remaining) {
		placedEnemies = maxEnemies - remaining;
	}
}