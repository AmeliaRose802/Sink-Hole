import java.awt.Color;
import java.util.Random;

import javax.swing.*;
/**
 * Creates a new obsticle.
 * Holds all info used to draw random colored boxes. 
 * Collision dection dosent happen here.
 * Takes no inputs.
 * @author Amelia
 *
 */
public class Obsticle {
	Random rand = new Random();
	protected int x;
	protected double y;
	private double speed;
	protected int length;
	protected int width;
	private Color c;
	
	/**
	 * This creates a new obsticle with random stats

	 * @param diff a string that represents difficulty
	 */
	public Obsticle(String diff){
		c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
		x = (int) (Math.random()*600);
		
		if(diff.equalsIgnoreCase("easy")){
			y = -150;
			speed =  (Math.random()*3)+1;
			length = (int)(Math.random()*30)+10;
			width = (int) (Math.random()*30)+10;
			
		}else if(diff.equalsIgnoreCase("hard")){
			y = -40;
			speed =  (Math.random()*3)+3.5;
			length = (int)(Math.random()*30)+30;
			width = (int) (Math.random()*30)+30;
		}else{
			y = -70;
			speed =  (Math.random()*3)+2;
			length = (int)(Math.random()*30)+20;
			width = (int) (Math.random()*30)+20;
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
	 * This function moves box above top of screen and randomises all stats
	 * It takes no inputs
	 */
	public void reset(){
		y = -50;
		x = (int) (Math.random()*1000);
		speed =  (Math.random()*3)+1;
		length = (int)(Math.random()*30)+20;
		width = (int) (Math.random()*30)+20;
		c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
		
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
	
}
