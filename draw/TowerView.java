package draw;
import game.MagicGem;
import game.Tower;

import java.awt.*;

public class TowerView extends Drawable {
	private String texture;
	private Tower tower;

	/**
	 * A towerview konstruktora
	 * Hozzarendeli a TowerView objektumhoz a view-t es a towert
	 * beallitja a torony texturajat az alap toronyera
	 * @param view a beallitando view
	 * @param t a beallitando torony
	 */
	public TowerView(View view,Tower t)
	{
		setView(view);
		tower = t;
		texture = "/textures/constructs/tower/tower.png";
		this.setImage(texture);

	}
	/**
	 * Lekeri az aktualis View-t
	 * majd egy in tomhoz hozzarendeli az adott torony Tile-nak pixelkoordinatait
	 * lekeri a MagicGemet, es ha nem null a Magicgem/tol fuggo texturat allit be
	 * vegen kirajzolja a parameterben kapott graphicsra
	 */
	public void draw(Graphics g) {
		View view = this.getView();
		int point[] = view.getTilePosition(tower.getTile());
		MagicGem gem = tower.getMagicGem();
		if (gem!=null)
		{
			if (gem.getType().equals("hobbit")) this.setImage("/textures/constructs/tower/tower_hobbit.png");
			else if (gem.getType().equals("human")) this.setImage("/textures/constructs/tower/tower_human.png");
			else if (gem.getType().equals("elf")) this.setImage("/textures/constructs/tower/tower_elf.png");
			else if (gem.getType().equals("dwarf")) this.setImage("/textures/constructs/tower/tower_dwarf.png");
			else if (gem.getType().equals("range")) this.setImage("/textures/constructs/tower/tower_range.png");
			else if (gem.getType().equals("firerate")) this.setImage("/textures/constructs/tower/tower_firerate.png");
		}
		g.drawImage(getImage() , point[0] , point[1], 30, 30, null);
	}
}
