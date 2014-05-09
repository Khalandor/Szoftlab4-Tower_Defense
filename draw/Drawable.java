package draw;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
