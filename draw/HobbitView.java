package draw;
import game.Hobbit;

import java.awt.Color;
import java.awt.Graphics;

public class HobbitView extends Drawable {
	private String texture;
	private Hobbit hobbit;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.drawRect(20, 20, 60, 60);
	}
}
