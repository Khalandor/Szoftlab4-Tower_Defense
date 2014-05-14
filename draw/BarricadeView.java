package draw;

import game.Barricade;

import java.awt.*;

public class BarricadeView extends Drawable {
	private String texture;
	private Barricade barricade;


	/**
	 * A BarricadeView konstructora. Itt történik az attribútumok beállítása.
	 * @param v Az osztály maga a játékállást megjelenítõ nézet
	 * @param b A kirajzolando barricade
	 */

	BarricadeView(View v , Barricade b) {
		setView(v);
		barricade = b;

		// Textúra elérési útjának beállítása
		texture = "/textures/constructs/barricade/barricade.png";

		// Textúra betöltése
		setImage(texture);
	}

	/**
	 * A textúra kirajzolását végzõ függvény.
	 * @param g A felület amire a rajzolás történik
	 */
	public void draw(Graphics g) {
		// A barricade pozíciójának lekérése.
		int[] poz = this.getView().getTilePosition((barricade.getBarricadeLocation()));

		// ha a barricádnak van magicgame-e akkor a textúrája megváltozik.
		if (barricade.getMagicGem()!= null)
			this.setImage("/textures/constructs/barricade/barricade_gem.png");

		// A textúra kirajzolása a megfelelõ helyre.
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null);
	}
}
