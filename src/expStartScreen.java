import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This object creates a start screen. It makes a panel and handles button presses.
 * It sends inputs to approate locations for storage, mostly player
 * @author Amelia
 *
 */
public class expStartScreen{
	//set up all nessary stuff
	private JFrame f;
	private String name;
	private String diff;
	private boolean gameOver;
	private boolean startGame;
	private newGame game;
	protected JButton exit;
	private Player p;
	
	/**
	 * Creates a start screen
	 * @param game2 - a game
	 * @param f - a jframe that holds the start screen
	 * @param p - a player object
	 */
	public expStartScreen(newGame game2, JFrame f, Player p){
		this.game = game2;
		this.f = f;
	    this.p = p;
	}
	
	/**
	 * This fuction makes a start panel, and handles input from it.
	 * @return a JPanel
	 */
	    public JPanel makePanel(){
		
	    //THIS PART JUST MAKES VISUAL COMPONENTS TO GO ON SCREEN
	    //GO TO BOTTOM TO SEE IMPORTANT PART 
	    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
	    	
	    	
		//SET UP THE STARTSCREEN PANEL
		JPanel startScreen = new JPanel();
		startScreen.setFocusable(true);
		startScreen.setBackground(Color.magenta);
		startScreen.setLayout(new BoxLayout(startScreen, BoxLayout.Y_AXIS));
	
		
	    
	       //SET UP PANEL CONTAING LINE AND WHITE SPACE WHICH IS USED LATER
	  		JPanel spaceing = new JPanel();
	  		spaceing.setFocusable(false);
	  		spaceing.setBackground(Color.WHITE);
	  		spaceing.setLayout(new BoxLayout(spaceing, BoxLayout.Y_AXIS));
	  		
	  		JLabel line1 = new JLabel("______________________________________");
	  	    line1.setFocusable(false);
	  	    line1.setFont(new Font("French Script MT", Font.ITALIC, 30));
	  	    line1.setForeground(Color.black);
	  	    line1.setAlignmentX(line1.CENTER_ALIGNMENT);
	  	    
	  	    JLabel spacer1 = new JLabel(" ");
	  	    spacer1.setFocusable(false);
	  	    spacer1.setFont(new Font("French Script MT", Font.ITALIC, 30));
	  	    spacer1.setForeground(Color.black);
	  	    spacer1.setAlignmentX(spacer1.CENTER_ALIGNMENT);
	  		
	  	    spaceing.add(line1);
	  	    spaceing.add(spacer1);
	  	    
	  	    
		//set up panel containing title and tag line
		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));

		JLabel gameName = new JLabel("Sink Hole");
	    gameName.setFont(new Font("Courier New", Font.ITALIC, 60));
	    gameName.setForeground(Color.black);
	    gameName.setAlignmentX(gameName.CENTER_ALIGNMENT);
	    
	    JLabel tagLine = new JLabel("A Meditative Modern Art Experience ");
	    tagLine.setFont(new Font("French Script MT", Font.ITALIC, 50));
	    tagLine.setForeground(Color.black);
	    tagLine.setAlignmentX(tagLine.CENTER_ALIGNMENT);
	    
		JLabel line = new JLabel("______________________________________");
	    line.setFont(new Font("French Script MT", Font.ITALIC, 30));
	    line.setForeground(Color.black);
	    line.setAlignmentX(line.CENTER_ALIGNMENT);
	    
	    JLabel spacer = new JLabel(" ");
	    spacer.setFont(new Font("French Script MT", Font.ITALIC, 20));
	    spacer.setForeground(Color.black);
	    spacer.setAlignmentX(line.CENTER_ALIGNMENT);
	    
	    JLabel spacer2 = new JLabel(" ");
	    spacer2.setFont(new Font("French Script MT", Font.ITALIC, 40));
	    spacer2.setForeground(Color.black);
	    spacer2.setAlignmentX(line.CENTER_ALIGNMENT);
	    
	    
	    title.add(gameName);
	    title.add(spacer);
	    title.add(tagLine);
	    title.add(line);
	    title.add(spacer2);
	   
	    //ADDING TITLE PANEL TO STARTSCREEN
	    startScreen.add(title);

	    
	    //THIS PANEL CONTAINS BOX TO GET THE NAME
	    JPanel getName = new JPanel();
	    getName.setFocusable(false);
	    getName.setBackground(Color.WHITE);
	    
	    JLabel nameText = new JLabel("Enter Name:   ");
	    
	    nameText.setFont(new Font("French Script MT", Font.PLAIN, 40));
	    nameText.setForeground(Color.black);
	    
	    
	    JTextField nameBox = new JTextField();
	    nameBox.setColumns(10);
	    nameBox.setFont(new Font("Freestyle Script", Font.PLAIN, 30));
	    nameBox.setHorizontalAlignment(JTextField.CENTER);
	    
	    
	    getName.add(nameText);
	    getName.add(nameBox);
	    
	    startScreen.add(getName);
	    
	    
	    
	    
	    //GET DIFFICULTY
	    JPanel getDiff = new JPanel();
	    getDiff.setFocusable(true);
	    getDiff.setBackground(Color.WHITE);
	    ButtonGroup diffSel = new ButtonGroup();
	    JRadioButton easy = new JRadioButton(" Easy  ");
	    JRadioButton medium = new JRadioButton(" Medium  ");
	    JRadioButton hard = new JRadioButton(" Hard  ");
	   
	    easy.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    medium.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    hard.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    
	    easy.setFocusPainted(false);
	    easy.setContentAreaFilled(false);
	    easy.setFocusable(false);
	    
	    medium.setFocusPainted(false);
	    medium.setContentAreaFilled(false);
	    easy.setFocusable(false);
	    
	    hard.setFocusPainted(false);
	    hard.setContentAreaFilled(false);
	    easy.setFocusable(false);
	    
	    diffSel.add(easy);
	    diffSel.add(medium);
	    diffSel.add(hard);
	    getDiff.add(easy);
	    getDiff.add(medium);
	    getDiff.add(hard);
	    
	    startScreen.add(getDiff);
	    
	    startScreen.add(spaceing);
	    
	    //GET DIFFICULTY
	    JPanel getBackground = new JPanel();
	    getBackground.setBackground(Color.WHITE);
	    JLabel backText = new JLabel("Select color scheme: ");
	    
	    ButtonGroup backSel = new ButtonGroup();
	    JRadioButton white = new JRadioButton(" Light  ");
	    JRadioButton black = new JRadioButton(" Dark ");
	   
	    backText.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    white.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    black.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    hard.setFont(new Font("French Script MT", Font.PLAIN, 45));
	    
	    white.setFocusPainted(false);
	    white.setContentAreaFilled(false);
	    
	    black.setFocusPainted(false);
	    black.setContentAreaFilled(false);
	    
	    
	    
	    backSel.add(white);
	    backSel.add(black);
	    
	    getBackground.add(backText);
	    getBackground.add(white);
	    getBackground.add(black);
	    
	    
	    startScreen.add(getBackground);
	    
	    startScreen.add(spaceing);
	    
		
	    //THIS PANEL CONTAINS THE START BUTTON aka exit
	    JPanel exitPanel = new JPanel();
	    exitPanel.setFocusable(true);
	    exitPanel.setBackground(Color.white);
	    
	    exit = new JButton(" Start ");
	    exit.setFont(new Font("Freestyle Script", Font.PLAIN, 50));
	    exit.setFocusable(true);
	    
	    
	    exit.setFocusPainted(false);
	    exit.setContentAreaFilled(false);
	    
	    
	    exitPanel.add(exit);
	    
	    startScreen.add(exitPanel);
	    
	  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	  //THE IMPORTANT PART
	    
	    //add an action listner to the exit button
	    exit.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  
	    	  //set player.name to the text from the name box if it cotains any input
	    	  name = nameBox.getText();
	    	  if(name.length()>0){
	    		  p.setName(name);
	    	  }
	    	  
	    	  
	    	  //get the difficulty and send it to player
	    	  if(easy.isSelected()){
	    		  diff="easy";
	    	  }else if(medium.isSelected()){
	    		  diff = "medium";
	    	  }else if(hard.isSelected()){
	    		  diff = "hard";
	    	  }else{
	    		  diff = "medium";
	    	  }
	    	  p.setDiff(diff);
	    	  
	    	  
	    	  //see if dark theme was selected, if so inform player object
	    	  if(black.isSelected()){
	    		p.setColorsDark();
	    		game.setBackground();
	    		p.printInfo();
	    	  }
	    	
	    	  //Multible threads nessary for stuff to not break
	    	  //Otherwise it waits for game loop to end before removing panel
	    	  Thread thread1 = new Thread() {
	    		    public void run() {
	    		    	//remove the start panel
	    		    	mainForNewGame.removePanel(startScreen, game, f);
	    		    }
	    		};
	    		Thread thread2 = new Thread() {
	    		    public void run() {
	    		    		try {
	    		    			//start the main game loop
								mainForNewGame.runGame(game, p);
									
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
	    		    	
	    		    }
	    		};
	    		
	    	//start the threads
			thread1.start();
			thread2.start();
			  
	      }
	      
	    });
	   //return the panel startScreen
	   return startScreen;
	    }    
	}