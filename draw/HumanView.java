package draw;
import game.Human;

import java.awt.Color;
import java.awt.Graphics;

public class HumanView extends Drawable {
	private String texture;
	private Human human;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		g.drawRect(40, 40, 60, 600);
	}
}
