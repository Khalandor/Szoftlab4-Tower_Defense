import java.util.Random;

public class EnemyGenerator {
	private final int maxGeneratingSpeed = 10;
	private int maxEnemies = 30;

	private int generatingSpeed;
	private int placedEnemies;
	private PathGenerator pathGenerator;
	private Updater updater;
    private int delay;

	/**
	 * Az EnemyGenerator konstruktora.
	 * Beállítja a pathGenerator referenciát.
	 * @param pathGenerator PathGenerator, melyen keresztül le fogja tenni az ellenségeket egy útvonalra.
	 * @param updater Updater, amire az új ellenségek felvételéhez van szükség
	 */
	public EnemyGenerator (PathGenerator pathGenerator, Updater updater){
		this.pathGenerator = pathGenerator;
		this.updater = updater;
        this.generatingSpeed = 1;
        this.delay = 5;
        this.placedEnemies = 0;

	}

	/**
	 * Adott típusú ellenséget hoz létre
	 * @param type String, az ellenség típusa
	 * @return az új ellenség
	 * @throws Exception ha olyan ellenséget kérnek tőle, ami nincs
	 */
	private Enemy createEnemy(String type) {
		Enemy newEnemy;
		if (type.equals("Dwarf"))
			return new Dwarf(this);
		else if (type.equals("Elf"))
			return new Elf(this);
		else if (type.equals("Hobbit"))
			return new Hobbit(this);
		else if (type.equals("Human"))
			return new Human(this);
		return null;
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
				return new Dwarf(this);
			case 1:
				return new Elf(this);
			case 2:
				return new Hobbit(this);
			case 3:
				return new Human(this);
		}
		return null;
	}

    /*
    // Csúnya tickek nélküli függvény, szerencsére elkerülhető:
	public void generateEnemies() {
		for (int i = 0; i < generatingSpeed; i++)
		{
			Enemy newEnemy = createRandomEnemy();
			updater.addEnemy(newEnemy);
			pathGenerator.start(newEnemy);
			placedEnemies++;
		}
		generatingSpeed++;
	}
	*/

    // Gyönyörű időkezelős függvény, sokkal jobb:
    /**
     * Új ellenség pályára helyezése a megfelelő időben
     */
    public void generateEnemies() {
        delay--;
        if(delay > 0)
            return;
        // sebesség nő a maxig => delay csökken 0-ig
        if (maxGeneratingSpeed > generatingSpeed){
            delay = maxGeneratingSpeed - generatingSpeed;
            generatingSpeed++;
        }
        else
            delay = 0;

        Enemy newEnemy = createRandomEnemy();
        updater.addEnemy(newEnemy);
        pathGenerator.start(newEnemy);
        placedEnemies++;
    }

	/**
	 *  Ha az utolsó ellenséget is leraktuk, igazzal tér vissza. Egyébként hamis.
	 */
	public Boolean isLastEnemyGenerated() {
		if (placedEnemies >= maxEnemies)
			return true;
		return false;
	}

	/**
	 * Lemásolja a paraméterül kapott ellenséget a paraméterül kapott csempére
	 */
	public void duplicateEnemy(Enemy enemy, PathTile pathTile) {
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