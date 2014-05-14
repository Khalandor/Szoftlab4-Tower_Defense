package draw;

import game.PathTile;

import java.awt.*;



public class PathTileView extends Drawable {
	private String texture;
	private PathTile pathTile;

	/**
	 * A PathTileView konstructora. Itt történik az attribútumok beállítása.
	 * @param v Az osztály maga a játékállást megjelenítõ nézet
	 * @param p A kirajzolando PathTile
	 */
	PathTileView(View v , PathTile p) {
		setView(v);
		// Textura elérésí útjának beállítása és atextúra betöltése
		texture = "/textures/tiles/pathTile.png";
		pathTile = p;
		setSubImage(texture, 30);
	}

	/**
	 * A textúra kirajzolását végzõ függvény.
	 * @param g A felület amire a rajzolás történik
	 */
	public void draw(Graphics g) {
		// A PathTile pozíciójának lekérése és a textúra kirajzolása a megfelelõ helyre.
		int[] poz = this.getView().getTilePosition(pathTile);
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null);

	}
}
