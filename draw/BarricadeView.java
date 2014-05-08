package draw;

import java.awt.Graphics;

import game.Barricade;

public class BarricadeView extends Drawable {
	private String texture;
	private Barricade barricade;
	
	BarricadeView(View v , Barricade b){
		setView(v);
		barricade = b;
		
		// Textura elérésí útjának beállítása
			texture = "constructs/barricade/barricade.png";
		
		// Textura betöltése
		setImage(texture);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] poz = this.getView().getTilePosition((barricade.getBarricadeLocation()));
		if (barricade.getMagicGem()!= null)
			this.setImage("constructs/barricade/barricade_gem.png");
		g.drawImage(getImage() , poz[0] , poz[1] , null);
	}
}
