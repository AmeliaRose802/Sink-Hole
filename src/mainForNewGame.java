import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This runs the game and handles functions relating to pausing the game loop. 
 * It contains the main. 
 * @author Amelia
 *
 */
public class mainForNewGame {
	private static boolean gameOver = false;
	
	/**
	 * The main game loop 
	 * @param game - a game object
	 * @param p - a player object
	 * @throws InterruptedException - genrated by using sleep timer
	 */
	public static void runGame(newGame game, Player p) throws InterruptedException{
		//create int time, stores time in 10 milliseconds
		int time = 0;
		//move balls runs until collision dected
	    while(!gameOver){	
	    	
	    	//if gamePaused is set true in game
	    	//it will start a loop and check if pause is false every 10 mils
	    	//Rest of function will not exicute until pause is false again
	    	while(game.getPause() == true){
	    		Thread.sleep(10);
	    	}
	    	
	    	//add new ball according to difficulty level
			if(time%p.getMakeObsticleFreq() == 0){
				Obsticle a = new Obsticle(p.getDiff());
				game.addObsticle(a);
			}
			
			//make coins according to difficulty level
			if(time%p.getMakeCoinFreq() == 0){
				Coin c = new Coin(p.getDiff());
				game.addCoin(c);
			}
							
			//move ball should increment all ball objects
			//if move ball dects collision set game over to true
			if(!game.moveBall()){
				gameOver = true;
				game.repaint();
			}
			
			//move the player
			if(!gameOver){
				game.movePlayer();
				game.repaint();
			}
							
									
			//sleep for 10
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//increment time and set score
			time+=1;
			p.setScore();
			}
	}
		    
		
	/**
	 * Remove startscreen and replace it with game
	 * @param p1 - The innital panel to be removed
	 * @param game - will be added
	 * @param f - frame that contains everything
	 */
	public static void removePanel(JPanel p1, newGame game, JFrame f){
		System.out.println("removePanel is being run");
		f.getContentPane().remove(p1);
  		game.requestFocusInWindow();
  		f.add(game);
  		f.revalidate();
		f.repaint();
	}
	
	/**
	 * Reset the jFrame. Puts start screen back up and removes game. 
	 * Can only safly be called after collision has unready been dected
	 * TODO figure out why it breaks running games
	 * @param game - the game
	 * @param f - the frame that contains everything
	 * @param p - the player 
	 */
	public static void resetGame(newGame game, JFrame f, Player p){
		//reset stats stored in player such as coins and score
		p.reset();
		//set game over to false which lets game loop run again when called
		gameOver= false;
		//Remove the game from the screen
		f.getContentPane().remove(game);
		f.revalidate();
		f.repaint();
		//Create a new game and reassign it to game, create start screen
		game = new newGame(p, f);
		expStartScreen e = new expStartScreen(game, f, p);
		
		//add the start screen
		f.add(e.makePanel());
		
		//set f visable which refreshes it
		//not sure why this works when repaint dosent
		f.setVisible(true);
	}
	
	//sets everything in motion
	public static void main(String[] args) throws InterruptedException{
		//set stuff up
		JFrame f = new JFrame();
		Player p = new Player();
		newGame game = new newGame(p, f);
		game.setFocusable(true);
		
		//add stuff to f
		expStartScreen screen = new expStartScreen(game,f,p);
		JPanel p1 = screen.makePanel();
		f.add(p1);
		
		//Set up JFrame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 1000);
		f.setVisible(true);
		
		//BUCK passes to startSceen

	}
}

