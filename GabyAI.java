package hw4;
import java.util.Random;
//6 ROWS, 7 COLUMNS
//[row][column]
public class GabyAI implements CFPlayer{
    private int[][] gabyState;

    public int nextMove(CFGame g) {
        gabyState = g.getState();

        //just keep playing the first column over and over to beat the random
        for(int i = 0; i<6;i++) {
            for(int j = 0; j< 7; j++) {
                if(gabyState[i][j] == 0)
                    return j;
            }
        }

        //otherwise play a randum from next move RandomAI
        Random randomObj = new Random();
        int randomColumn = randomObj.nextInt(7);

        return randomColumn;


    }
		
	public String getName() {
		return "Gaby's AI";
    }
}

