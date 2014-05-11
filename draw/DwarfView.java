package draw;
import game.Dwarf;

import java.awt.*;

public class DwarfView extends Drawable {
	private String texture;
	private Dwarf dwarf;
	
	/**
	 * A DwarfView konstruktora, beallitja a texturat a dwarf�ra
	 * a param�terben kapott view-t a dwarfviewhoz rendeli hozz�
	 * a param�terben kapott dwarfot hozzarendeli a DwarfView-hoz, hogy tudja melyik tilehoz tartoz� Dwarfr�l van sz�
	 * @param view beallitando view
	 * @param d beallitando dwarf
	 */
	public DwarfView(View view,Dwarf d)
	{
		setView(view);
		dwarf = d;
		texture = "textures/enemies/dwarf.png";
		this.setImage(texture);
		
	}

	
	/**
	 * Lekerjuk az aktualis view-t
	 * Ha a dwarf mar sebzodott atallitjuk a texturat a sebzodottre
	 * Lekerjuk az ellenseg Tile-j�t, es ebbol meghatarozzuk a pixel koordinatakat
	 * kirajzoljuk a kepet 
	 */
	public void draw(Graphics g) {
		View view = this.getView();
        if (dwarf.getHealth() < 80)
            texture = "textures/enemies/dwarf-damaged.png";
        this.setImage(texture);
		int point[] = view.getTilePosition(dwarf.getTile());			
		g.drawImage(getImage() , point[0]+5 , point[1]-5, 32, 32, null);
	}
}
