package hw4;
import java.util.Random;

//7 columns, 6 rows 
public class RandomAI implements CFPlayer{
	private int[][] randomState;
	boolean valueFound = false;

	public int nextMove(CFGame g) {
		Random randomObject = new Random();
		int randomColumn = randomObject.nextInt(7);
		randomState = g.getState();

		while(!valueFound) {
			for (int i = 0; i<6; i++) {
				if (randomState[i][randomColumn] == 0) {
					valueFound = true;				
					return randomColumn;
				}
			}
			randomColumn = randomObject.nextInt(7);
		}
		return randomColumn;
	}

	public String getName() {
		return "Random Player";
	}
}