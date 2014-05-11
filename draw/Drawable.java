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
	
	public Image getImage()
	{
		return drawableImage;
	}

	
	public View getView(){
		return this.view;
	}
	
	public void setView (View view)
	{
		this.view = view;
	}
	public abstract void draw(Graphics g);
	
	
}
