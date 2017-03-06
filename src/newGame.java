import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Draws the boxes to the screen
 * Takes an input of a Player and a JFrame
 * Constructor only requires player and JFrame, obsticles are added later
 * Performs collision dection
 * Moves player based on input
 * Handles mouse inputs for pause and reset buttons
 * @author Amelia
 *
 */
public class newGame extends JPanel implements MouseListener{
	
	//Used for key bindings
	private static Action moveRight;
	private static Action moveLeft;
	private static Action stop;
	
	//Arrays that hold obsticles and coins
	ArrayList<Obsticle> boxes = new ArrayList<Obsticle>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	
	//Create nessary varibles
	private Player p;
	private boolean gameGoing = true;
	private boolean pauseGame = false;
	JFrame f;
	newGame game = this;
	
	//create the play and pause buttons
	private Button pause = new Button(50,5,40,40, "res/pauseIcon.png");
	private Button reset= new Button(7,10,30,30,"res/resetWhite.png");
	
	
	
	/**
	 * Creates a game object. 
	 * @param p - A player object
	 * @param f - The JFrame that the game is being added to
	 */
	public newGame(Player p, JFrame f){
		//set up varibles
		this.f = f;
		this.p = p;
		
		//add mouse listner to the game component, ie to the entire screen
		addMouseListener(this);
		
		//set up key bindings actions
		moveRight = new moveRight();
		moveLeft = new moveLeft();
		stop = new stopMoving();
		
		//link actions to words
		getActionMap().put( "shouldStop", stop);
		getActionMap().put( "shouldMoveRight", moveRight);
		getActionMap().put( "shouldMoveLeft", moveLeft);
		
		//Get input for left and right pressed, link to moveRight and moveLeft,
		//Get input for right and left released and link them to stop
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("RIGHT"), "shouldMoveRight" );
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("released RIGHT"), "shouldStop");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("LEFT"), "shouldMoveLeft" );
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("released LEFT"), "shouldStop" );
        
        //Set background color according to user settings
        setBackground(p.getBackColor());
	}
	
	/**
	 * reset the background to players preferences
	 * Takes no input but uses info from player object
	 */
	public void setBackground(){
		setBackground(p.getBackColor());
	}
	
	public int getNumOfCoins(){
		return coins.size();	
	}
	
	
	/**
	 * Add an obstacle to array boxes
	 * @param b - Obstacle object
	 */
	public void addObsticle(Obsticle b){
		boxes.add(b);
	}
	
	/**
	 * Add coin to array coins
	 * @param c - Coin object
	 */
	public void addCoin(Coin c){
		coins.add(c);
	}
	
	public boolean getPause(){
		return pauseGame;
	}
	
	/**
	 * Move the obstacles, dect collisions. 
	 * set gameGoing to false if collison dected.
	 * @return false if collision is dected.
	 */
	public boolean moveBall(){
		
		
		//run through boxes
		for(Obsticle o : boxes){
			
			//detects collision
			//need to change rect2 to player object
			if (o.getX() < p.getX() + p.getWidth() &&
					   o.getX() +o.getWidth()> p.getX() &&
					   o.getY() < p.getY() + p.getLength() &&
					   o.getLength() + o.getY() > p.getY()) {
				
					    // collision detected!
						System.out.println("Collison Dected");
						//set hold return to false this will be returned later 
						gameGoing = false;
						}
			
			o.moveBox(o.getSpeed());
			int y = (int) o.getY();
			
			//if obsticle is past bottom of screen move it to top
			if(y > 1100){
				o.reset();
			}
		}
		
		//Move coins, dect collisions, reset coin if collison is dected
		for(Coin c : coins){
			if (c.getX() < p.getX() + p.getWidth() &&
					   c.getX() +c.getWidth()> p.getX() &&
					   c.getY() < p.getY() + p.getLength() &&
					   c.getLength() + c.getY() > p.getY()) {
				
						c.reset();
						System.out.println("Got coin");
						p.addCoin(1);
				}
			
			
			c.moveBox(c.getSpeed());
		}
		//return false if collison is dected
		return gameGoing;
	}
		
	
	
	/**
	 * move the player as long as it is within the limits of the screen. 
	 * If player goes off screen move back to edge of same side.
	 */
	public void movePlayer(){
		if(p.getX() < 0){
			p.setLeftEdge();
		}else if(p.getX() > 550){
			p.setRightEdge();
		}else{
			p.move();
		}
	}
	
	
	/**
	 * Actually draws all objects to the screen, both obsticles and players. 
	 * Also draws buttons, score, and layout to screen.
	 * Handles drawing game over screen when game ends
	 * overrides the paint methould draws Objects to screen
	 */
	@Override
	public void paint(Graphics g){
		//Set stuff up
		 super.paint(g);
		 Graphics2D g2d = (Graphics2D) g;
		 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//draw each coin
		for(Coin c : coins){
			g2d.drawImage(c.getImage(), c.getX(), (int) c.getY(), c.getWidth(), c.getLength(), null);
		}
		 
		//draw each box
		for(Obsticle o : boxes){
			 g2d.setColor(o.getColor());
			 //create a rectangle using the stats genrated for each Obsticle
			 g2d.fillRect(o.getX(), (int)o.getY(), o.getWidth(), o.getLength());
		}
		
		//Draw the player to the screen
		g2d.setColor(p.getColor());
		g2d.fillRect(p.getX(), p.getY(), p.getWidth(), p.getLength());
		
		;
		
		
		
		//draw static components to screen, buttons, text, top and bottom bars, etc
		//TODO figure out a way for these to not draw everytime in order to reduce stress on graphics
		g2d.setBackground(Color.black);
		g2d.fillRect(0, 0, 1000, 50);
		g2d.fillRect(0, 890, 1000, 70);
		
		//change settings 
		g2d.setFont(new Font("French Script MT", Font.BOLD, 40));
		g2d.setColor(Color.WHITE);
		
		//Draw score and coins to screen
		g2d.drawString("Score: "+p.getScore(), 450, 40);
		g2d.drawString("Coins: "+p.getCoins(), 300, 40);
		g2d.drawImage(reset.getIcon(), reset.getX(), reset.getY(), reset.getWidth(), reset.getHeight(), null);
		g2d.drawImage(pause.getIcon(), pause.getX(), pause.getY(), pause.getWidth(), pause.getHeight(), null);
		g2d.drawString("Name: "+p.getName(), 0,935);
		g2d.drawString("Mode: "+p.getDiff(), 250,935);
		
		
				
		 //if a collison has been dected and therefore the game is over
		 if(!gameGoing){
			 
			 //wait 500 milisecons 
			 try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 //draw end screen
			 g2d.setColor(Color.black);
			 g2d.fillRect(0, 0, 1000, 1000);
			 
			 //Reset color and draw messages and reset button
			 g2d.setFont(new Font("French Script MT", Font.BOLD, 80));
			 g2d.setColor(Color.WHITE);
			 g.drawString("Game Over", 170, 400);
			 g2d.setFont(new Font("French Script MT", Font.BOLD, 40));
			 g.drawString(p.getName()+" scored "+ p.getScore(), 180, 470);
			 g.drawString(p.getName()+" got "+p.getCoins() + " Coins", 180, 510);
			 g2d.drawImage(reset.getIcon(), reset.getX(), reset.getY(), reset.getWidth(), reset.getHeight(), null);
		 }
	}
	
	
	//MOUSE INPUT STUFF
	public void mouseClicked(MouseEvent e) {
		
		//checks if you have clicked the reset button and that the game is over
		//TODO figure out a way for reset to work when game is going
		 if ((e.getButton() == 1) && !gameGoing && reset.getIconCover().contains(e.getX(), e.getY()) ) {
		      System.out.println("Reset Clicked");
		      mainForNewGame.resetGame(game, f, p);
		 }
		 //Checks if pause button is clicked and game is in progress
		 else if((e.getButton() == 1) && gameGoing && pause.getIconCover().contains(e.getX(), e.getY())){
			 System.out.println("Pause Clicked");
			 //set pause game to the oppsite of what it is
			 if(pauseGame == false){pauseGame = true;}
			 else{pauseGame = false;}
		 }
   }

	
   //Must be implemented but are not currently used
   public void mouseExited(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   
	
	
	//KEYBORD INPUT SECTION FOR PLAYER OBJECT
	
	class moveRight extends AbstractAction{
        public void actionPerformed( ActionEvent tf ){
            p.setRight();
        } 
        
    }
	
	class moveLeft extends AbstractAction{
        public void actionPerformed( ActionEvent tf ){
            p.setLeft();
        }
        
    }
	class stopMoving extends AbstractAction{
        public void actionPerformed( ActionEvent tf ){
            p.stop();
        }
        
    }
        
    }


