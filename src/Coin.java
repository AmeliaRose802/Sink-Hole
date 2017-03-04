import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Coin{
	Random rand = new Random();
	protected int x;
	protected double y;
	private double speed;
	protected int length;
	protected int width;
	private Color c;
	String diff;
	
	private BufferedImage Icon;
	
	/**********************************************
	 * This creates a new obsticle with random stats.
	 * It requires a string representing difficulty. 
	 */
	public Coin(String diff){
		c = new Color(0,0,0);
		x = (int) (Math.random()*600);
		this.diff = diff;
		try {
			Icon = ImageIO.read(new File("res/goldCoin.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(diff.equalsIgnoreCase("easy")){
			y = -150;
			speed =  (Math.random()*3)+1;
			length = 30;
			width = 30;
			
		}else if(diff.equalsIgnoreCase("hard")){
			y = -50;
			speed =  (Math.random()*3)+3;
			length = 10;
			width = 10;
		}else{
			y = -150;
			speed =  (Math.random()*3)+2;
			length = 20;
			width = 20;
		}
		
	}
	
	
	/**
	 * This function moves the obsticle down a given number of pixels.
	 * The higher steps is the faster the obsticle will move.
	 * @param steps - an int, Also called volosity or speed.
	 */
	public void moveBox(double steps){
		y+=steps;
	}
	
	/**
	 * This function moves box above top of screen and randomises all stats according to difficulty
	 * 
	 * It takes no inputs
	 */
	public void reset(){
		c = new Color(0,0,0);
		x = (int) (Math.random()*600);
		
		if(diff.equalsIgnoreCase("easy")){
			y = -150;
			speed =  (Math.random()*3)+1.5;
			length = 30;
			width = 30;
			
		}else if(diff.equalsIgnoreCase("hard")){
			y = -50;
			speed =  (Math.random()*3)+3;
			length = 10;
			width = 10;
		}else{
			y = -150;
			speed =  (Math.random()*3)+2;
			length = 20;
			width = 20;
		}
		
	}
	
	/**
	 * This function prints info about a given object
	 */
	public void printInfo(){
		System.out.println("x = "+ x+ " Y = "+ y);
		System.out.println("speed = "+speed);
		System.out.println("Length = "+ length + " width = "+ width);
		System.out.println("Color is "+ c.toString());
		System.out.println();
	}
	
	

	//GETTERS ONLY BELOW HERE
	public int getX() {
		return x;
	}

	public double getY() {
		return y;
	}
		
	public double getSpeed() {
		return speed;
	}

	public int getLength() {
		return length;
	}
	
	public int getWidth() {
		return width;
	}

	public Color getColor(){
		return c;
	}
	public BufferedImage getImage(){
		return Icon;
	}
	
	
}
