package draw;

import game.PathTile;

import java.awt.*;

;

public class PathTileView extends Drawable {
	private String texture;
	private PathTile pathTile;
	
	/**
	 * A PathTileView konstructora. Itt t�rt�nik az attrib�tumok be�ll�t�sa.
	 * @param v Az oszt�ly maga a j�t�k�ll�st megjelen�t� n�zet
	 * @param b A kirajzolando PathTile
	 */
	PathTileView(View v , PathTile p){
		setView(v);
		// Textura el�r�s� �tj�nak be�ll�t�sa �s atext�ra bet�lt�se
		texture = "textures/tiles/pathTile.png";
		pathTile = p;
		setSubImage(texture, 30);
	}
	
	/**
	 * A text�ra kirajzol�s�t v�gz� f�ggv�ny.
	 * @param g A fel�let amire a rajzol�s t�rt�nik
	 */
	public void draw(Graphics g) {
		// A PathTile poz�ci�j�nak lek�r�se �s a text�ra kirajzol�sa a megfelel� helyre.
		int[] poz = this.getView().getTilePosition(pathTile);
		g.drawImage(getImage() , poz[0] , poz[1], 30, 30, null); 

	}
}
