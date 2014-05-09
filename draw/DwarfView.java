package draw;
import game.Dwarf;

import java.awt.*;

public class DwarfView extends Drawable {
	private String texture;
	private Dwarf dwarf;
	
	public DwarfView(View view,Dwarf d)
	{
		setView(view);
		dwarf = d;
		texture = "textures/enemies/dwarf.png";
		this.setImage(texture);
		
	}
	@Override
	public void draw(Graphics g) {
		View view = this.getView();
		int point[] = view.getTilePosition(dwarf.getTile());			
		g.drawImage(getImage() , point[0]+5 , point[1]+12, 16, 16, null); 
	}
}
