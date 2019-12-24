package hw4;
import hw4.CFPlayer;
import java.util.Random;
import java.util.Scanner;

public class ConsoleCF extends CFGame{
	private CFPlayer player1;
	private CFPlayer player2;
	Random rand = new Random();
	Scanner readMe = new Scanner(System.in);

	private boolean play1Wins = false;
	private boolean play2Wins = false;
	private boolean drawWins = false;
	private boolean marked = true;


	private boolean play1first; //will decide who goes first 

	//implements CFPlayer. HumanPlayer’s nextMove implementation should print the state of 
	//the board to the command line and ask the user for the next move. 
	//If the provided move is invalid, say so and ask again for a valid move. 
	//HumanPlayer’s getName implementation should return "Human Player".
	private class HumanPlayer implements CFPlayer{
		public int nextMove(CFGame g) {
			int[][] gameState = g.getState();
			//print board
			printBoardStatus(g);
			
			//get column
			System.out.println("Pick a column");
			int inputColumn = readMe.nextInt();

			//figure out if column is playable
			while(marked)
			{
				for (int i = 0; i < 6; i++) {
					if(gameState[i][inputColumn-1] == 0)
						marked = false;
					else {
						System.out.println("Invalid. Pick a new column.");
						inputColumn = readMe.nextInt();
					}
				}
			}

			return (inputColumn-1);
		}

		public String getName() {
			return "Human Player";
		}

	}
	//sets up a human vs. AI game, where the red player 
	//(the player who goes first) is randomly decided.
	public ConsoleCF(CFPlayer ai) {
		super();
		//set up two players 
		player1 = this.new HumanPlayer();
		player2 = ai; 

		play1first = rand.nextBoolean(); //randomly assign if player 1 will go first or not
	}
	//sets up a AI vs. AI game, where the red player 
	//(the player who goes first) is randomly decided.
	public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {
		super();
		player1 = ai1;
		player2 = ai2;
		play1first = rand.nextBoolean();
	}

	//plays the game until the game is over.
	public void playOut() {
		//while loop until game is over 
		while(!this.isGameOver()) {
			if(play1first) {
				this.play(player1.nextMove(this));
				if(this.isGameOver()) {play1Wins = true; break;}

				this.play(player2.nextMove(this));
				if(this.isGameOver()) {play2Wins = true; break;}
			}
			else {
				this.play(player2.nextMove(this));
				if(this.isGameOver()) {play2Wins = true; break;}

				this.play(player1.nextMove(this));
				if(this.isGameOver()) {play1Wins = true; break;}
			}
		}

		printBoardStatus(this);
		if(play1Wins) 
			System.out.println(player1.getName() + " wins!!");
		else if(play2Wins)
			System.out.println(player2.getName() + " wins!!");
		else
			System.out.println("Game Over. Draw.");
	}

	//return either "draw", "Human Player", or the AI’s name given by CFPlayer’s getName method.
	//The private inner class
	public String getWinner() {
		if (play1Wins) {
			return player1.getName();
		}
		else if (play2Wins) {
			return player2.getName();
		}
		else 
			return "draw";
	}

	private void printBoardStatus(CFGame g) {
		int[][] statusState = g.getState();
        for(int i = 0; i<6;i++) {
            for(int j = 0; j< 7; j++) {
            	if (statusState[i][j] == 1) {
                	System.out.print(statusState[i][j] + "    ");
            	}
                if (statusState[i][j] == -1) {
                	System.out.print(statusState[i][j] + "   ");
                }
                if (statusState[i][j] == 0) {
                	System.out.print(statusState[i][j] + "    ");
                }
            }
            System.out.println();
        }
	}
}