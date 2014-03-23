public class Construct {
	protected String type;
	protected MagicGem gem;
	
	/**
	 * Épület típusával tér vissza
	 * @return a típus amivel visszatér
	 */
	public String getType() {
		System.out.println("--> Construct.getType()");//
		System.out.println("<-- towerType");//
		return this.type;//
	}
	
	/**
	 * beállítja a gem attribútumot a kapott értékre
	 * @param gem - a kapott érték
	 */
	public void setMagicGem(MagicGem gem) {
		System.out.println("--> Construct.setMagicGem("+gem+")");//
		this.gem = gem;//
	}
}