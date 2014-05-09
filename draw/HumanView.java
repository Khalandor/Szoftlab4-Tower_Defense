package draw;
import game.Human;

import java.awt.*;

public class HumanView extends Drawable {
	private String texture;
	private Human human;
	
	public HumanView(View view,Human h)
	{
		setView(view);
		human = h;
		texture = "textures/enemies/human.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		View view = this.getView();
		int point[] = view.getTilePosition(human.getTile());
		g.drawImage(getImage(), point[0]+30, point[1]+20, null);	
	}
}
