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
		View view = this.getView();
        if (human.getHealth() < 50)
            texture = "textures/enemies/human-damaged.png";
        this.setImage(texture);
		int point[] = view.getTilePosition(human.getTile());
		g.drawImage(getImage() , point[0]-5 , point[1]+5, 32, 32, null);
	}
}
