import java.awt.Color;
import java.util.Random;
/**
 * The player holds all info needed to draw a player to screen.
 * It also holds methods essental to movement, Color theme and difficulty
 * @author Amelia
 *
 */
public class Player {
	private int x;
	private int y;
	private double speed;
	private double speedSet;
	private int length;
	private int width;
	private Color c;
	private double score = 0;
	private int coins = 0;
	private String name = "Player 1";
	private boolean gameOver = true;
	private String diff = "medium";
	private int makeObsticleFreq = 150;
	private int makeCoinFreq = 300;
	
	private Color backColor = Color.white;
	

	/**
	 * Player doesn't currently take inputs since everything is preset. 
	 */
	public Player(){
		x = 300;
		y = 600;
		length = 10;
		width = 40;
		c = Color.black;
		speed = 0;
		speedSet = 4;
	}
	
	//add to the int holding coins gotten
	public void addCoin(int coin){
		coins+=coin;
	}
	public int getCoins(){
		return coins;
	}

	
	/**
	 * Move speed number of steps, takes no inputs
	 */
	public void move(){
		x+=speed;
	}
	
	/**
	 * Sets the speed to negative base speed(speedSet) so object moves left
	 */
	public void setLeft(){
		speed = -speedSet;
	}
	
	/**
	 * Sets speed to positive base speed(speedSet) so object moves right
	 */
	public void setRight(){
		speed = speedSet;
	}
	/**
	 * Set speed to 0, DOES NOT effect base speed
	 */
	public void stop(){
		speed = 0;
	}
	
	/**
	 * Set player x position to left edge.
	 * Used to stop players from exiting screen.
	 */
	public void setLeftEdge(){
		x = 0;
	}
	
	/**
	 * Set player X position to right edge.
	 * Used to stop players from exiting screen.
	 */
	public void setRightEdge(){
		x = 550;
	}
	
	/**
	 * Print info about object
	 */
	public void printInfo(){
		System.out.println("Speed: "+ speed);
		System.out.println("Base speed "+ speedSet);
		System.out.println("Width = "+width+ " Length = "+ length);
		System.out.println("Color = "+c.toString());
		System.out.println("Background = "+ backColor);
	}
	
	public void setScore(){
		score+=.1;
	}
	
	
	//set varous values based on difficulty
	public void setDiff(String diff){
		this.diff = diff;
		if(diff.equalsIgnoreCase("easy")){
			makeObsticleFreq = 200;
			makeCoinFreq = 300;
			length = 20;
			width = 60;
		}else if(diff.equalsIgnoreCase("hard")){
			makeObsticleFreq = 50;
			makeCoinFreq = 450;
			speedSet = 5;
			
		}else{
			makeObsticleFreq = 100;
			makeCoinFreq = 350;
			speedSet = 4.5;
		}
	}
	
	
	/**
	 * reset all player stastics to default
	 */
	
	public void reset(){
		x = 300;
		y = 600;
		length = 10;
		width = 40;
		c = Color.black;
		backColor = Color.white;
		speed = 0;
		speedSet = 4;
		name = "Player 1";
		score = 0;
		coins = 0;
	}
	
	
	//GETTER AND SETTERS
	public void setColorsDark(){
		backColor = Color.black;
		c = new Color(91, 59, 255);
	}
	
	public Color getBackColor(){
		return backColor;
	}
	public String getDiff(){
		return diff;
	}
	public int getMakeObsticleFreq(){
		return makeObsticleFreq;
	}
	public int getMakeCoinFreq(){
		return makeCoinFreq;
	}
	public int getScore(){
		return (int)score;
	}
	public int getX() {
		return x;
	}
	public int getY() {
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

	public Color getColor() {
		return c;
	}
	public void setName(String n){
		name = n;
	}
	public String getName(){
		return name;
	}
	
	
}
