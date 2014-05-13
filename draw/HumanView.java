package draw;
import game.Human;

import java.awt.*;

public class HumanView extends Drawable {
	private String texture;
	private Human human;

	/**
	 * A HumanView konstruktora, beallitja a texturat a Humanéra
	 * a paraméterben kapott view-t a Humanviewhoz rendeli hozzá
	 * a paraméterben kapott Humant hozzarendeli a HumanView-hoz, hogy tudja melyik tilehoz tartozó Humanról van szó
	 * @param view beallitando view
	 * @param h beallitando Human
	 */
	public HumanView(View view,Human h)
	{
		setView(view);
		human = h;
		texture = "textures/enemies/human.png";
		this.setImage(texture);

	}
	/**
	 * Lekerjuk az aktualis view-t
	 * Ha a human mar sebzodott atallitjuk a texturat a sebzodottre
	 * Lekerjuk az ellenseg Tile-ját, es ebbol meghatarozzuk a pixel koordinatakat
	 * kirajzoljuk a kepet
	 */
	public void draw(Graphics g) {
		View view = this.getView();
		if (human.getHealth() < 50)
			texture = "textures/enemies/human-damaged.png";
		this.setImage(texture);
		int point[] = view.getTilePosition(human.getTile());
		g.drawImage(getImage() , point[0]-5 , point[1]+5, 32, 32, null);
	}
}
