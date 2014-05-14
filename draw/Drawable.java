package draw;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public abstract class Drawable {
	private View view;

	protected Image drawableImage;

	/**
	 * Az Image texturajanak beallitasa a parameterben megkapott eleris utvonal szerint
	 * @param path az eleresi utvonal
	 */
	public void setImage(String path)
	{
		try {
            if (this.getClass().getResource(path) != null)
                drawableImage = ImageIO.read(this.getClass().getResource(path));
            else throw new IOException("Could not read: " + path);
		} catch (IOException e) {
            System.out.println("Could not read:" + path);
		}
	}

    /**
     * Beolvassa egy nagy képnek egy véletlenszerűen kiválasztott kis négyzet lalakú részét
     * @param path a nagy kép elérési útvonala
     * @param size a kis kép egy oldalának mérete
     */
	public void setSubImage(String path, int size) {
        if (this.getClass().getResource(path) != null) {
            try {
                BufferedImage bFullImage = ImageIO.read(this.getClass().getResource(path));
                int width = bFullImage.getWidth();
                int height = bFullImage.getHeight();
                int xParts = width / size;
                int yParts = height / size;
                int xMultiplier = new Random().nextInt(xParts);
                int yMultiplier = new Random().nextInt(yParts);
                drawableImage = bFullImage.getSubimage(xMultiplier * size, yMultiplier * size, size, size);
            } catch (IOException e) {
                System.out.println("Could not read:" + path);
            }
        }
        else System.out.println("Could not read:" + path);
    }

	/**
	 * visszaadja az Image-t
	 * @return az Image
	 */
	public Image getImage()
	{
		return drawableImage;
	}

	/**
	 * View getter fuggvenye
	 * @return visszaadja a View-t
	 */
	public View getView() {
		return this.view;
	}
	/**
	 * A View setter fuggvenye
	 *
	 * @param view a beallitando View
	 */
	public void setView (View view)
	{
		this.view = view;
	}

	/**
	 * draw fuggveny ami minden Drawable objektum megvalosit
	 */
	public abstract void draw(Graphics g);


}
