package draw;
import java.awt.Graphics;

import game.Elf;

public class ElfView extends Drawable {
	private String texture;
	private Elf elf;
	
	
	public ElfView(View view,Elf e)
	{
		setView(view);
		elf = e;
		texture = "enemies/elf.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		View view = this.getView();
		int point[] = view.getTilePosition(elf.getTile());
		g.drawImage(getImage(), point[0], point[1], null);		
	}
}
