import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/****************************************************
 * Creates a button with an image as the icon. 
 * Creates shape behind image with same size and quardnates that handles clicks.
 * Use .contains on .getIconCover to resteger clicks.
 * Draw image using .getX .getY etc to make sure all works correctly. 
 * @author Amelia
 *
 */
public class Button {
	private int x, y, height, width;
	private BufferedImage Icon;
	private Shape iconCover;
	
	/**
	 * Make button
	 * @param x - int 
	 * @param y - int 
	 * @param height - int
	 * @param width - int 
	 * @param filePath - String pointing to image file
	 */
	public Button(int x, int y, int height, int width, String filePath){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		iconCover = new Rectangle2D.Float(x, y, height, width);
	    try {
			this.Icon = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public BufferedImage getIcon() {
		return Icon;
	}


	public Shape getIconCover() {
		return iconCover;
	}
	
}
