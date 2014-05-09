package draw;

import game.EndTile;

import java.awt.*;

public class EndTileView extends Drawable {
	private String texture;
	private EndTile endTile;
	
	EndTileView(View v , EndTile e){
		setView(v);
		texture = "textures/tiles/mount_doom.png";
		endTile = e;
		setImage(texture);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] poz = this.getView().getTilePosition(endTile);
		g.drawImage(getImage() , poz[0] , poz[1] , null);
	}
}
