import java.util.Random;
import java.io.*;

public class simCraps{
	private static double chips = 1000;
	private static double bet = 10;
	private static int diceOne;
	private static int diceTwo;
	private static Random random = new Random(1234);
	private static int win;
	private static int point;
	private static int totalNumbersOfPlays;
	private static int winNumbers = 0;

	public static void main(String[] args){
		totalNumbersOfPlays = Integer.parseInt(args[0]);
		int i = 0;

		while(checkGameContinue(bet) && i < totalNumbersOfPlays){
			chips = 1000;
			i++;
			generateDices();
			point = diceOne + diceTwo;
			if(win == 1){
				chips += bet;
				winNumbers++;
				break;
			}else if(win == -1){
				chips -=bet;
				break;
			}
		}
		double pro = (winNumbers * 1.0)/totalNumbersOfPlays;
		System.out.println("Prob of Winning = " + pro);
	}

	public static int checkInitialWinOrLose(int dice){
		if(dice == 7 || dice == 11){
			return 1;
		}else if (dice == 2 || dice == 3 || dice == 12){
			return -1;
		}
		return 0;
	}

	public static int checkRollWin(){
		if (diceOne + diceTwo == point){
			return 1;
		}else if (diceOne + diceTwo == 7){
			return -1;
		}
		return 0;
	}

	public static boolean checkGameContinue(double bet){
		return(chips - bet) >= 0;
	}

	public static void generateDices(){
		diceOne = random.nextInt(6) + 1;
		diceTwo = random.nextInt(6) + 1;
	}
}








