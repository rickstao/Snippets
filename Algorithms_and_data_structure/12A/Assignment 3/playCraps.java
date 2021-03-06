/*	This program was done by myself (Ruikang Tao). My knowledge knowledge in java
 	is, in fact, very limited, so I did put a good amount of time in this program.
 	I spent about 7 hours working on this program. Thank you for taking your time
 	to grade this assignment.
*/

/**
	*Assignment #3.
	*This program generates a dice game called "Craps"
	*Author: Ruikang Tao (rtao6@ucsc.edu)
*/

import java.util.*;
import java.io.*;

public class playCraps{
	//choose chips
	private static double chips;
	//choose bet
	private static double bet;
	//the first dice #
	private static int dice1;
	//the second dice #
	private static int dice2;
	//generate a random number
	private static Random ram;
	//set parameter for winning and loosing
	private static int win;
	//take the point on the first roll
	private static int point;
	//reads user input
	private static Scanner sc = new Scanner(System.in);


	public static void main(String[] args){
		long seed;
		//given instruction
		System.out.println("Enter the seed:");
		seed = sc.nextLong();

		//given instruction
		System.out.println("How many chips do you want?");
		chips = sc.nextDouble();

		ram = new Random(seed);

		while (checkGameContinue(bet)){
			inputBet();
			generateDices();
			System.out.println("\nroll is :\n");

			//result after rolling the dice
			outputDiceImage(dice1, dice2);

			point = dice1 + dice2;

			//check whether wins or loses
			win = checkInitialWinOrLose(point);

			if (win == 1){
				chips += bet;
				System.out.println("You won, you now have "+chips);
			}else if (win == -1){
				chips -= bet;
				System.out.println("You lost, you now have "+chips);
			}else{
				System.out.println("The point is " + point);
				System.out.println("hit return to roll the dice");
				while(win != 1){
					generateDices();
					System.out.println("\nroll is :\n");
					outputDiceImage(dice1,dice2);

					win = checkRollWin();

					if(win==1){
						chips += bet;
						System.out.println("You win, you now have " + chips);
						break;
					}else if (win == -1){
						chips -= bet;
						System.out.println("You lost, you now have " + chips);
						break;
					}
				}
			}
		}
	}

	//return 1 if sum is 7 or 11;
	//return -1 if the sum is 2, 3, or 12;
	//otherwise, return 0.
	//
	public static int checkInitialWinOrLose(int dice){
		if (dice == 7 || dice ==11){
			return 1;
		}else if (dice == 2 || dice == 3 || dice == 12){
			return -1;
		}
		return 0;
	}

	//after the first roll, if sum euqals point, player wins;
	//if sum is 7, player loses;
	public static int checkRollWin(){
		if (dice1 + dice2 == point){
			return 1; //return 1 if sum is point
		}else if (dice1 + dice2 == 7){
			return -1; //return -1 if sum is 7
		}
		return 0; //else return 0
	}

	//check the player's bet
	public static boolean checkGameContinue(double bet){
		//return true if left chip is greater or eual than o
		return (chips - bet) >= 0; 
	}

	//check if the bet is valid
	public static void inputBet(){
		System.out.println("Enter bet.");
		bet = sc.nextDouble();
		while(!inputIsValid(bet)){
			System.out.println("Enter bet.");
			bet = sc.nextDouble();
		}
		System.out.println("hit return to rool the dice");
	}

	//check if player's input is valid
	public static boolean inputIsValid(double bet){
		if(chips<bet){
			System.out.println("Not an ok bet");
			return false;
		}
		return true;
	}

	//generate two dice numbers
	public static void generateDices(){
		dice1 = ram.nextInt(6) + 1;
		dice2 = ram.nextInt(6) + 1;
	}

	//print out dice numbers after rolling
	//parameter d1 is the first dice number
	//parameter d2 is the second dice number
	public static void outputDiceImage(int d1, int d2){
		String positions[][] = outputSingleDice();
		System.out.println(positions[dice1 - 1][0] + "\t" + positions[dice2-1][0]);
		System.out.println(positions[dice1 - 1][1] + "\t" + positions[dice2-1][1]);
		System.out.println(positions[dice1 - 1][2] + "\t" + positions[dice2-1][2]);
		System.out.println(positions[dice1 - 1][3] + "\t" + positions[dice2-1][3]);
		System.out.println(positions[dice1 - 1][4] + "\t" + positions[dice2-1][4]);
		System.out.println(positions[dice1 - 1][5] + "\t" + positions[dice2-1][5]);
	}

	//using String array to set the fomat of dice faces
	public static String[][] outputSingleDice(){
		String positions[][] = new String[6][6];
		positions[0][0]=" ------- ";
		positions[0][1]="|       |";
		positions[0][2]="|       |";
		positions[0][3]="|   *   |";
		positions[0][4]="|       |";
		positions[0][5]=" _______ ";

		positions[1][0]=" ------- ";
		positions[1][1]="|       |";
		positions[1][2]="|       |";
		positions[1][3]="| *   * |";
		positions[1][4]="|       |";
		positions[1][5]=" _______ ";

		positions[2][0]=" ------- ";
		positions[2][1]="|       |";
		positions[2][2]="|   *   |";
		positions[2][3]="|       |";
		positions[2][4]="| *   * |";
		positions[2][5]=" _______ ";

		positions[3][0]=" ------- ";
		positions[3][1]="|       |";
		positions[3][2]="| *   * |";
		positions[3][3]="|       |";
		positions[3][4]="| *   * |";
		positions[3][5]=" _______";

		positions[4][0]=" ------- ";
		positions[4][1]="|       |";
		positions[4][2]="| *   * |";
		positions[4][3]="|   *   |";
		positions[4][4]="| *   * |";
		positions[4][5]=" _______ ";

		positions[5][0]=" ------- ";
		positions[5][1]="|       |";
		positions[5][2]="| *   * |";
		positions[5][3]="| *   * |";
		positions[5][4]="| *   * |";
		positions[5][5]=" _______ ";

		return positions;
	}
}









