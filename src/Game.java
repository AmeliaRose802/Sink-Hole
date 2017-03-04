import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Draws the boxes to the screen
 * Takes an input of an array of Obsticles and a Player
 * Constructor only requires player, obsticles are added later
 * Performs collision dection
 * Moves player based on input
 * @author Amelia
 *
 */
public class Game extends JPanel{
	private static Action moveRight;
	private static Action moveLeft;
	private static Action stop;
	ArrayList<Obsticle> boxes = new ArrayList<Obsticle>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	Player p;
	boolean gameGoing = true;
	Image image;
	JFrame j;

	public Game(Player p){
		this.p = p;
		moveRight = new moveRight();
		moveLeft = new moveLeft();
		stop = new stopMoving();
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("RIGHT"), "shouldMoveRight" );
        getActionMap().put( "shouldMoveRight", moveRight);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("released RIGHT"), "shouldStop");
        getActionMap().put( "shouldStop", stop);
        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("LEFT"), "shouldMoveLeft" );
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("released LEFT"), "shouldStop" );
        getActionMap().put( "shouldMoveLeft", moveLeft);
        
        setBackground(p.getBackColor());
	}
	
	public void setBackground(){
		setBackground(p.getBackColor());
	}
	
	public void addObsticle(Obsticle b){
		boxes.add(b);
	}
	
	public void addCoin(Coin c){
		coins.add(c);
	}
	
	/**
	 * Print info about each obsticle and the player
	 */
	public void printInfo(){
		for(Obsticle o : boxes){
			o.printInfo();
		}
		p.printInfo();
	}
	
	
	/**
	 * Move the obstacles
	 * @return false if collision is dected
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
	 * Actually draws all objects to the screen, both obsticles and players
	 * overrides the paint methould draws Objects to screen
	 */
	@Override
	public void paint(Graphics g){
		//run through boxes
		 super.paint(g);
		 Graphics2D g2d = (Graphics2D) g;
		 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(Obsticle o : boxes){
			 g2d.setColor(o.getColor());
			 //create a rectangle using the stats genrated for each Obsticle
			 g2d.fillRect(o.getX(), (int)o.getY(), o.getWidth(), o.getLength());
		}
		
		
		for(Coin c : coins){
			 g2d.setColor(c.getColor());
			 g2d.setColor(new Color(255,215,0));
			 g2d.fillRect(c.getX(), (int) c.getY(), c.getWidth(), c.getLength());
		}
		//paint the player
		g2d.setColor(p.getColor());
		g2d.fillRect(p.getX(), p.getY(), p.getWidth(), p.getLength());
		g2d.fillRect(0, 0, 1000, 50);
		g2d.fillRect(0, 890, 1000, 70);
		g2d.setFont(new Font("French Script MT", Font.BOLD, 40));
		g2d.setColor(Color.WHITE);
		System.out.println(p.getName());
		
		g2d.drawString("Name: "+p.getName(), 0,935);
		g2d.drawString("Mode: "+p.getDiff(), 250,935);
		
		g2d.drawString("Score: "+p.getScore(), 450, 40);
		g2d.drawString("Coins: "+p.getCoinsAsString(), 300, 40);
	//	g2d.drawString(p.getName(), 250, 30);
		 
		 if(!gameGoing){
			 System.out.println("You are here");
			 try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 g2d.setColor(Color.black);
			 g2d.fillRect(0, 0, 1000, 1000);
			 g2d.setFont(new Font("French Script MT", Font.BOLD, 80));
			 g2d.setColor(Color.WHITE);
			 g.drawString("Game Over", 170, 400);
			 g2d.setFont(new Font("French Script MT", Font.BOLD, 40));
			 g.drawString(p.getName()+" scored "+ p.getScore(), 180, 470);
			 g.drawString(p.getName()+" got "+p.getCoinsAsString() + " Coins", 180, 510);
			 
			 
		 }
	}
	
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
