package draw;

import game.PathTile;

import java.awt.*;

;

public class PathTileView extends Drawable {
	private String texture;
	private PathTile pathTile;
	
	
	PathTileView(View v , PathTile p){
		setView(v);
		texture = "textures/tiles/pathTile.png";
		pathTile = p;
		setImage(texture);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] poz = this.getView().getTilePosition(pathTile);
		g.drawImage(getImage() , poz[0] , poz[1] , null);

	}
}
