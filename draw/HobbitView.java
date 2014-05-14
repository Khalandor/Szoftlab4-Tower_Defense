package draw;
import game.Hobbit;

import java.awt.*;

public class HobbitView extends Drawable {
	private String texture;
	private Hobbit hobbit;


	/**
	 * A HobbitView konstruktora, beallitja a texturat a Hobbitéra
	 * a paraméterben kapott view-t a Hobbitviewhoz rendeli hozzá
	 * a paraméterben kapott Hobbitot hozzarendeli a HobbitView-hoz, hogy tudja melyik tilehoz tartozó Hobbitról van szó
	 * @param view beallitando view
	 * @param h beallitando Hobbit
	 */
	public HobbitView(View view,Hobbit h)
	{
		setView(view);
		hobbit = h;
		texture = "/textures/enemies/hobbit.png";
		this.setImage(texture);

	}

	/**
	 * Lekerjuk az aktualis view-t
	 * Ha a hobbit mar sebzodott atallitjuk a texturat a sebzodottre
	 * Lekerjuk az ellenseg Tile-ját, es ebbol meghatarozzuk a pixel koordinatakat
	 * kirajzoljuk a kepet
	 */
	public void draw(Graphics g) {
		View view = this.getView();
		if (hobbit.getHealth() < 20)
			texture = "/textures/enemies/hobbit-damaged.png";
		this.setImage(texture);
		int point[] = view.getTilePosition(hobbit.getTile());
		g.drawImage(drawableImage , point[0]+5 , point[1], 32, 32, null);
	}
}
