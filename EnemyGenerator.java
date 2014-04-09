public class EnemyGenerator {
	private int generatingSpeed;
	private int placedEnemies;
	private PathGenerator pathGenerator;

	/**
	 * Az EnemyGenerator konstruktora.
	 * Beállítja a pathGenerator referenciát.
	 * @param pathGenerator PathGenerator, melyen keresztül le fogja tenni az ellenségeket egy útvonalra.
	 */
	public EnemyGenerator (PathGenerator pathGenerator){
		this.pathGenerator = pathGenerator;
	}
	/**
	 * Ellenségek létrehozására szolgáló függvény
	 * @return visszatér az adott ellenséggel
	 */
	public Enemy generateEnemies() {
		System.out.println("--> EnemyGenerator.generateEnemies() ");
		Dwarf Tyrion = new Dwarf();
		pathGenerator.start(Tyrion);
		System.out.println("<-- Enemy");
		return Tyrion;
	}

	/**
	 *  Ha elértük a végleges generatingSpeed-et és az utolsó ellenséget is leraktuk, igazzal tér vissza. Egyébként hamis.
	 */
	public Boolean isLastEnemyGenerated() {
		System.out.println("--> EnemyGenerator.isLastEnemyGenerated()");
		System.out.println("<--");
		return true;
	}
	
	/**
	 * Lemásolja a paraméterül kapott ellenséget a paraméterül kapott csempére
	 */
	public void duplicateEnemy(Enemy enemy, PathTile pathTile)
	{
		
	}
}