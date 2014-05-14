package draw;

import game.FieldTile;

import java.awt.*;

public class FieldTileView extends Drawable {
	private String texture;
	private FieldTile fieldTile;


	/**
	 * A FieldTileView konstructora. Itt történik az attribútumok beállítása.
	 * @param v Az osztály maga a játékállást megjelenítõ nézet
	 * @param f A kirajzolando FieldTile
	 */
	FieldTileView(View v  , FieldTile f) {
		setView(v);
		// Textura elérésí útjának beállítása és atextúra betöltése
		texture = "/textures/tiles/fieldTile.png";
		fieldTile = f;
		setSubImage(texture, 30);
	}

	/**
	 * A textúra kirajzolását végzõ függvény.
	 * @param g A felület amire a rajzolás történik
	 */
	public void draw(Graphics g) {
		// A FieldTile pozíciójának lekérése és a textúra kirajzolása a megfelelõ helyre.
		int[] poz = this.getView().getTilePosition(fieldTile);
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null);
	}
}
