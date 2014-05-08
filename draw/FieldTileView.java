package draw;

import java.awt.Graphics;

import game.FieldTile;

public class FieldTileView extends Drawable {
	private String texture;
	private FieldTile fieldTile;
	
	FieldTileView(View v  , FieldTile f){
		setView(v);
		texture = "tiles/fieldTile.png";
		fieldTile = f;
		setImage(texture);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] poz = this.getView().getTilePosition(fieldTile);
		g.drawImage(getImage() , poz[0] , poz[1] , null);		
	}
}
