package draw;

import game.EndTile;

import java.awt.*;

public class EndTileView extends Drawable {
	private String texture;
	private EndTile endTile;
	
	/**
	 * A EndTileView konstructora. Itt t�rt�nik az attrib�tumok be�ll�t�sa.
	 * @param v Az oszt�ly maga a j�t�k�ll�st megjelen�t� n�zet
	 * @param b A kirajzolando EndTile
	 */
	EndTileView(View v , EndTile e){
		setView(v);
		
		// Textura el�r�s� �tj�nak be�ll�t�sa �s atext�ra bet�lt�se
		texture = "textures/tiles/mount_doom.png";
		endTile = e;
		setImage(texture);
	}
	
	/**
	 * A text�ra kirajzol�s�t v�gz� f�ggv�ny.
	 * @param g A fel�let amire a rajzol�s t�rt�nik
	 */
	public void draw(Graphics g) {
		// A EndTile poz�ci�j�nak lek�r�se a text�ra kirajzol�sa a megfelel� helyre.
		int[] poz = this.getView().getTilePosition(endTile);
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null); 
	}
}
