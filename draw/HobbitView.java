package draw;
import game.Dwarf;
import game.Hobbit;

import java.awt.Color;
import java.awt.Graphics;

public class HobbitView extends Drawable {
	private String texture;
	private Hobbit hobbit;
	
	public HobbitView(View view,Hobbit h)
	{
		setView(view);
		hobbit = h;
		texture = "enemies/hobbit.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		View view = this.getView();
		int point[] = view.getTilePosition(hobbit.getTile());
		g.drawImage(getImage(), point[0], point[1], null);		
	}
}
