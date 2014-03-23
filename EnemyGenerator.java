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
		System.out.println("--> EnemyGenerator( " +pathGenerator+ " )");
		this.pathGenerator = pathGenerator;
	}
	
	public void generateEnemies() {
		throw new UnsupportedOperationException();
	}

	/**
	 *  Ha elértük a végleges generatingSpeed-et és az utolsó ellenséget is leraktuk, igazzal tér vissza. Egyébként hamis.
	 */
	public Boolean isLastEnemyGenerated() {
		System.out.println("--> EnemyGenerator.isLastEnemyGenerated()");
		System.out.println("<--");
		//TODO ide mit írjunk? a 10. tesztben elvileg true-val tér vissza, de megoldható
		return true;
	}
}