package draw;

import game.Barricade;

import java.awt.*;

public class BarricadeView extends Drawable {
	private String texture;
	private Barricade barricade;
	
	
	/**
	 * A BarricadeView konstructora. Itt t�rt�nik az attrib�tumok be�ll�t�sa.
	 * @param v Az oszt�ly maga a j�t�k�ll�st megjelen�t� n�zet
	 * @param b A kirajzolando barricade
	 */
	
	BarricadeView(View v , Barricade b){
		setView(v);
		barricade = b;
		
		// Textura elérésí útjának beállítása
			texture = "textures/constructs/barricade/barricade.png";
		
		// Textura betöltése
		setImage(texture);
	}
	
	/**
	 * A text�ra kirajzol�s�t v�gz� f�ggv�ny.
	 * @param g A fel�let amire a rajzol�s t�rt�nik
	 */
	public void draw(Graphics g) {
		// A barricade poz�ci�j�nak lek�r�se.
		int[] poz = this.getView().getTilePosition((barricade.getBarricadeLocation()));
		
		// ha a barric�dnak van magicgame-e akkor a text�r�ja megv�ltozik.
		if (barricade.getMagicGem()!= null)
			this.setImage("textures/constructs/barricade/barricade_gem.png");
		
		// A text�ra kirajzol�sa a megfelel� helyre.
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null); 
	}
}
