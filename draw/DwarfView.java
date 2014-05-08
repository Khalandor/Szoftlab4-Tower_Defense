package draw;
import java.awt.Graphics;

import game.Dwarf;
import game.Elf;

public class DwarfView extends Drawable {
	private String texture;
	private Dwarf dwarf;
	
	public DwarfView(View view,Dwarf d)
	{
		setView(view);
		dwarf = d;
		texture = "enemies/dwarf.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		View view = this.getView();
		int point[] = view.getTilePosition(dwarf.getTile());
		g.drawImage(getImage(), point[0], point[1], null);		
	}
}
