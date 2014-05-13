package draw;
import game.Elf;

import java.awt.*;

public class ElfView extends Drawable {
	private String texture;
	private Elf elf;
	/**
	 * A ElfView konstruktora, beallitja a texturat a Elféra
	 * a paraméterben kapott view-t a Elfviewhoz rendeli hozzá
	 * a paraméterben kapott Elfet hozzarendeli a ElfView-hoz, hogy tudja melyik tilehoz tartozó Elfrõl van szó
	 * @param view beallitando view
	 * @param e beallitando Elf
	 */

	public ElfView(View view,Elf e)
	{
		setView(view);
		elf = e;
		texture = "textures/enemies/elf.png";
		this.setImage(texture);

	}


	/**
	 * Lekerjuk az aktualis view-t
	 * Ha az elf mar sebzodott atallitjuk a texturat a sebzodottre
	 * Lekerjuk az ellenseg Tile-ját, es ebbol meghatarozzuk a pixel koordinatakat
	 * kirajzoljuk a kepet
	 */
	public void draw(Graphics g) {
		View view = this.getView();
		if (elf.getHealth() < 20)
			texture = "textures/enemies/elf-damaged.png";
		this.setImage(texture);
		int point[] = view.getTilePosition(elf.getTile());
		g.drawImage(getImage() , point[0] , point[1], 32, 32, null);
	}
}
