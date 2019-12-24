package hw4;

public class CFGame {
	//state[i][j] = 0 //empty
	//=1 is red
	//=-1 is black
    //[ROW][COLUMN] == [6][7]
	private final int[][] state;
	private boolean isRedTurn;
    private boolean redWon;
    private boolean blackWon;
	{
		state = new int[6][7];
		for(int i = 0; i<6;i++)
			for(int j = 0; j< 7; j++)
				state[i][j] =0;
			isRedTurn = true; //red goes first and has first play advantage
	}

	public int[][] getState() {
		int[][] ret_arr = new int[6][7];
		for (int i = 0; i<6; i++)
			for (int j = 0; j<7; j++)
				ret_arr[i][j] = state[i][j];
		return ret_arr;
	}

	public boolean isRedTurn() {
		return isRedTurn;
	}

	public boolean play(int column) {
		//check if its within valid columns
        //System.out.println("The column in bool play is " + column);
		if((column < 0) || (column>7)) {
            //System.out.println("Invalid Selection.");
			return false;
        }
		int indexPlayColumn = column;

        for (int i = 5; i > -1; i--) {
            if (state[i][indexPlayColumn] == 0) {
                if (isRedTurn) {
                    state[i][indexPlayColumn] = 1;
                    isRedTurn = false;
                    //printBoard();
                    return true;
                }
                else {
                    state[i][indexPlayColumn] = -1;
                    isRedTurn = true;
                    //printBoard();
                    return true;
                }

            }
	   }       
       return false;

    }   

    public boolean isGameOver() {


        //evaluate game being over by looking for winners horizontally, vertically
        //and diagonally both ways through the game board 

        //horizontal
        for(int j = 0; j < 7; j++) {
            for(int i = 0; i < 3; i++) {
                if (state[i][j] == state[i+1][j] && state[i+1][j] == state[i+2][j] && state[i+2][j] == state[i+3][j]) {
                    if (state[i][j] == 1) { redWon = true; return true;}
                    if (state[i][j] ==-1) { blackWon = true; return true;}
                }
            }
        }

        //vertical
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (state[i][j] == state[i][j+1] && state[i][j+1] == state[i][j+2] && state[i][j+2] == state[i][j+3]) {
                    if (state[i][j] ==1) { redWon = true; return true;}
                    if (state[i][j] ==-1) { blackWon = true; return true;}
                }
            }
        }

        //diagonal up left to right 
        for(int i = 5; i >2; i--) {
            for (int j = 0; j < 4; j++) {
                if (state[i][j] == state[i-1][j+1] && state[i-1][j+1] == state[i-2][j+2] && state[i-2][j+2] == state[i-3][j+3]) {
                    if (state[i][j] ==1) { redWon = true; return true;}
                    if (state[i][j] ==-1) { blackWon = true; return true;}
                }
            }
        }

        //diagonal down left to right 
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (state[i][j] == state[i+1][j+1] && state[i+1][j+1] == state[i+2][j+2] && state[i+2][j+2] == state[i+3][j+3]) {
                    if (state[i][j] ==1) { redWon = true; return true;}
                    if (state[i][j] ==-1) { blackWon = true; return true;}
                }
            }
        }

        //can any other move be made
        boolean movePossible = false;
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if(state[i][j] == 0)
                    movePossible = true;
            }
        }
        if (movePossible == true)
            return false;
        else {
            System.out.println("It's a draw!");
            return true;
        }

    }

	public int winner() {
		if (redWon) 
            return 1;
        if (blackWon) 
            return -1;
		//if inconclusive / draw  
		return 0;
	}

    public void printBoard() {
        for(int i = 0; i<6;i++) {
            for(int j = 0; j< 7; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }
}