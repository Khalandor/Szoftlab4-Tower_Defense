package draw;

import java.awt.Color;
import java.awt.Graphics;

import game.PathTile;;

public class PathTileView extends Drawable {
	private String texture;
	private PathTile pathTile;
	
	
	PathTileView(View v , PathTile p){
		setView(v);
		texture = "tiles/pathTile.png";
		pathTile = p;
		setImage(texture);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] poz = this.getView().getTilePosition(pathTile);
		g.drawImage(getImage() , poz[0] , poz[1] , null);

	}
}
