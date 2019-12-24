package hw4;

import hw4.ConsoleCF;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;

public class GUICF extends CFGame{
	private GameBoard this_board;
	//player objects
	private CFPlayer player1;
	private CFPlayer player2;

	//booleans for who is going first 
	boolean play1First = false;
	boolean play2First = false;
	
	//gui objects 
	private JButton[] jButtons = new JButton[7];
	private JFrame jframe = new JFrame("Connect 4");
	private JPanel jpanel = new JPanel();
	private Container pane;
	private JLabel[][] colorGrid = new JLabel[6][7]; //will hold colors
	boolean click1 = false;
	boolean click2 = false;

	//ai game button to start
	private JButton pressPlay = new JButton("Play");

	//gameplay
	private int inputColumn;
	//who won
	boolean winner1 = true;
	boolean winner2 = false;
	boolean noWinner = false;

	//random
	Random rand = new Random();

	//reminders:
	//need to call super class to get access to some functions
	//need to write two different games (ai vs. )
	public GUICF(CFPlayer ai) {
		//sets up and starts a human vs AI game where red is randomly decided
		this_board = new GameBoard();

		//initialize players
		player1 = ai;
		player2 = this.new HumanPlayer(); //why is this getting an error

		//button pane and layout
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,7,20,20)); //initialize grid layout
		//add downward arrows, listeners and button actions DONT DO THIS FOR AI VS AI 
		//creating a whole panel of buttons that should sit above the grid.
		for(int i = 0; i <7; i++) {
			jButtons[i] = new JButton("\u2193"); //unicode for downward arrow
			jButtons[i].addActionListener(new myAction());
			buttonPanel.add(jButtons[i]); //add buttons to panel
		}
		pane.add(buttonPanel,BorderLayout.NORTH);

		//set who goes first
		play1First = rand.nextBoolean();
		if (play1First == false)
			play2First = true;
	}
	public GUICF(CFPlayer ai1, CFPlayer ai2) {
		//sets up and starts a human vs AI game where red is randomly decided
		this_board = new GameBoard();
		player1 = ai1;
		player2 = ai2;

		//set up play button to begin game
		pressPlay.addActionListener(new myPlay());
		pressPlay.setPreferredSize(new Dimension(20,20));

	}

	private boolean playGUI(int c) {
		this_board = new GameBoard();
		return true;
	}

	private class GameBoard extends JPanel { 
		private GameBoard() {
			// initialize empty board
			jframe.setSize(500,500);
			pane = jframe.getContentPane();
			jpanel.setLayout(new GridLayout(6,7));

			//set up grid of empty labels
			for (int i=0;i<6;i++){
				for (int j=0;j<7;j++){
					colorGrid[i][j] = new JLabel("");
					colorGrid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				}
			}		
			//add labels to panel
			for (int i=0;i<6;i++){
				for (int j=0;j<7;j++){
					jpanel.add(colorGrid[i][j]);
				}
			}
			pane.add(jpanel, BorderLayout.CENTER);		
		}
		private void paint(int x, int y, int color) {
		// paints specified coordinate red or black
			System.out.println("Painting "+x +" "+y);
			if (color==-1) {
				colorGrid[x][y].setBackground(Color.BLACK);
				colorGrid[x][y].setOpaque(true);
			} 
			if (color == 1) {
				colorGrid[x][y].setBackground(Color.RED);
				colorGrid[x][y].setOpaque(true);
			}
		}
	}

	private class HumanPlayer implements CFPlayer {
		public int nextMove(CFGame g) {
			int[][] gameState = g.getState();
			//print board
			//printBoardStatus(g);
			
			//looping variable
			boolean marked = true;
			//get column
			//System.out.println("Pick a column");
			//int inputColumn = readMe.nextInt();

			//figure out if column is playable
			while(marked)
			{
				for (int i = 0; i < 6; i++) {
					if(gameState[i][inputColumn-1] == 0)
						marked = false;
					else {
						System.out.println("Invalid. Pick a new column.");
						//inputColumn = readMe.nextInt(); need a way to randomize inputs of mouse click 
					}
				}
			}

			//reed too return value of column played when click is true
			int returnVal = inputColumn;
			inputColumn = 0;

			return (inputColumn);
		}

		public String getName() {
			return "Human Player";
		}

	}

	private class myAction implements ActionListener {
		//since the button has an action listener, every time it is pressed this will be completed
		//press the button and set input column = to action
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 7; i++) {
				if (jButtons[i] == e.getSource()) {//gives you a reference to an object in the event that it came from
					//if no one has won
					if (winner2 == false || winner1 == false || noWinner == false) {
						click1 = true;
						inputColumn = (i+1);
						break;
					}
				}
			}
		}
	}

	private class myPlay implements ActionListener {
		//since the button has an action listener, every time it is pressed this will be completed
		@Override
		public void actionPerformed(ActionEvent e) {
		//tell the computer that the click is registering play
		//if no one has won yet, we can keep going
			if (winner2 == false || winner1 == false || noWinner == false) 
				click2 = true;
		}
	}

	public static void main(String[] args) {

	}
	
}