package draw;
import game.Hobbit;
import game.Human;

import java.awt.Color;
import java.awt.Graphics;

public class HumanView extends Drawable {
	private String texture;
	private Human human;
	
	public HumanView(View view,Human h)
	{
		setView(view);
		human = h;
		texture = "enemies/human.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		View view = this.getView();
		int point[] = view.getTilePosition(human.getTile());
		g.drawImage(getImage(), point[0], point[1], null);		
	}
}
