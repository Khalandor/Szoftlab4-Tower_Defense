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

	public Boolean isLastEnemyGenerated() {
		throw new UnsupportedOperationException();
	}
}