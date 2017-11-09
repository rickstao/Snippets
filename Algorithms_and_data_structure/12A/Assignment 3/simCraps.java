/*	This program was done by myself (Ruikang Tao). My knowledge knowledge in java
 	is, in fact, very limited, so I did put a good amount of time in this program.
 	I spent about 1 hour1 working on this program. Thank you for taking your time
 	to grade this assignment.
*/

/**
	*Assignment #3.
	*This program generates a probability calculator called "simCraps"
	*Author: Ruikang Tao (rtao6@ucsc.edu)
*/

import java.util.*;
import java.io.*;

//declaring simsCraps class
public class simCraps {
	public static void main(String[] args) throws IOException{
		Random rando = new Random();
		boolean keepTrying = true;
		int trial = Integer.parseInt(args[0]);
		int wins =0;

		//for loop lets the player to keep playing 
		for(int i =0; i < trial; i++){
			if(winOrLose()==true) wins++;
		}
		System.out.println("The percentage for winning is:" + 1.0 *wins/trial);
	}
	
	//generate a random integer from o-n
	public static int dice(int n){
		return (int) (Math.random()*n);
	}

	//generata a random number for dice
	private static int sum() {
		int x=1 + dice(6);
		int y = 1 + dice(6);
		return x + y;
	}

	//check whether the player wins or loses
	private static boolean winOrLose(){
		int x = sum();
		if (x==7 || x==11) 
			return true; //the player wins
		if(x==2 || x==3 || x==12) 
			return false; //return nothing, the player loses
		while(true){
			int y = sum();
			if (y==7) return false;
			if (y==x) return true;
		}	
	}
}