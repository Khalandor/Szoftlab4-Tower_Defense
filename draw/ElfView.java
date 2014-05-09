package draw;
import game.Elf;

import java.awt.*;

public class ElfView extends Drawable {
	private String texture;
	private Elf elf;
	
	
	public ElfView(View view,Elf e)
	{
		setView(view);
		elf = e;
		texture = "textures/enemies/elf.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		View view = this.getView();
		int point[] = view.getTilePosition(elf.getTile());		
		g.drawImage(getImage() , point[0] , point[1], 32, 32, null);
	}
}
