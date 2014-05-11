package draw;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
			drawableImage = ImageIO.read(new File (path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

    public void setSubImage(String path, int size)
    {
        try {
            BufferedImage bFullImage= ImageIO.read(new File(path));
            int width = bFullImage.getWidth();
            int height = bFullImage.getHeight();
            int xParts = width / size;
            int yParts = height / size;
            int xMultiplier = new Random().nextInt(xParts);
            int yMultiplier = new Random().nextInt(yParts);
            drawableImage = bFullImage.getSubimage(xMultiplier * size, yMultiplier * size, size, size);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
	public View getView(){
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
	 * @param g
	 */
	public abstract void draw(Graphics g);
	
	
}
