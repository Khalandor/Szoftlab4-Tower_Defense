package draw;
import game.Hobbit;

import java.awt.*;

public class HobbitView extends Drawable {
	private String texture;
	private Hobbit hobbit;
	
	public HobbitView(View view,Hobbit h)
	{
		setView(view);
		hobbit = h;
		texture = "textures/enemies/hobbit.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		View view = this.getView();
		int point[] = view.getTilePosition(hobbit.getTile());
		g.drawImage(getImage() , point[0]+5 , point[1], 32, 32, null);
	}
}
