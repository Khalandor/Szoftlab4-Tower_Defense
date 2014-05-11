package draw;

import game.EndTile;

import java.awt.*;

public class EndTileView extends Drawable {
	private String texture;
	private EndTile endTile;
	
	/**
	 * A EndTileView konstructora. Itt történik az attribútumok beállítása.
	 * @param v Az osztály maga a játékállást megjelenítõ nézet
	 * @param b A kirajzolando EndTile
	 */
	EndTileView(View v , EndTile e){
		setView(v);
		
		// Textura elérésí útjának beállítása és atextúra betöltése
		texture = "textures/tiles/mount_doom.png";
		endTile = e;
		setImage(texture);
	}
	
	/**
	 * A textúra kirajzolását végzõ függvény.
	 * @param g A felület amire a rajzolás történik
	 */
	public void draw(Graphics g) {
		// A EndTile pozíciójának lekérése a textúra kirajzolása a megfelelõ helyre.
		int[] poz = this.getView().getTilePosition(endTile);
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null); 
	}
}
