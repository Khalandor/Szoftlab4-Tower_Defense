package game;

public class Construct {
	protected String type;
	protected MagicGem gem;

	/**
	 * Épület típusával tér vissza
	 * @return a típus amivel visszatér
	 */
	public String getType() {
		return this.type;//
	}

	/**
	 * beállítja a gem attribútumot a kapott értékre
	 * @param gem - a kapott érték
	 */
	public void setMagicGem(MagicGem gem) {
		this.gem = gem;//
	}

	public MagicGem getMagicGem() {
		return gem;
	}
}