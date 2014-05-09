package draw;

import game.FieldTile;

import java.awt.*;

public class FieldTileView extends Drawable {
	private String texture;
	private FieldTile fieldTile;
	
	FieldTileView(View v  , FieldTile f){
		setView(v);
		texture = "textures/tiles/fieldTile.png";
		fieldTile = f;
		setImage(texture);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] poz = this.getView().getTilePosition(fieldTile);
		g.drawImage(getImage() , poz[0] , poz[1] , null);		
	}
}
