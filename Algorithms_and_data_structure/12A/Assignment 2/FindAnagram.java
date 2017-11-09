/*	This program was done by myself (Ruikang Tao). My knowledge knowledge in java
 	is, in fact, very limited, so I did put a good amount of time in this program.
 	I spent about 5 hours working on this program. Thank you for taking your time
 	to grade this assignment.
*/

/**
	*Assignment #2.
	*This program generates a finders that looks for all the possible anagrams 
	*based on the user input.
	*Author: Ruikang Tao (rtao6@ucsc.edu)
*/

import java.util.*;
import java.io.*;

//declare class FinadAnagram
public class FindAnagram{
	//creates an arraylist function
	private static ArrayList<String> textWords = new ArrayList<String>();

	public static void main(String[] args){
		try{
			Scanner wordsScanner = new Scanner(new File(args[0])); //scan file
			scanTextFile(wordsScanner); //call out function scanTextFile

		} catch(FileNotFoundException exception){ //exit out to the console
			System.out.println(exception.getMessage()); 
			System.exit(1);
		}

		Scanner keyboardscanner = new Scanner(System.in); //scans the user input
		startLoop(keyboardscanner); //call out function startLoop
	}

	//scanner scan word list and loop puts them into an arraylist
	private static void scanTextFile(Scanner scanner){
		while(scanner.hasNext()){ //while loop puts words into an arraylist
			String word = scanner.nextLine();
			textWords.add(word);
		}
	}

	//scanner scans user input
	private static void startLoop(Scanner scanner){
		System.out.println("Type a string of letters:");

		String inputWord = scanner.nextLine(); //scanner scans user input

		checkIfAnagramExists(inputWord); //call out function checkIfAnagramExists

		System.out.println("Do another (y/n)?");

		char inputChar = scanner.next().charAt(0); //put word into char type

		switch(inputChar){ //switch statement tests if the user wants to repeat
			case 'y':
				startLoop(new Scanner(System.in));
				break;
			case 'n':
				System.exit(0);
			default:
				System.exit(0);
				break;
		}
	}

	//create boolean method to check wheter two words are anagrams
	private static boolean isAnagram(String keyboard, String txtFile){
		char[] keyboard1 = keyboard.toCharArray(); //converts string keyboard 
		//to a new array
		char[] txtFile2 = txtFile.toCharArray(); //converts string 
		//txtFile to a new array
		Arrays.sort(keyboard1); //sort the characters in the array
		Arrays.sort(txtFile2); //sort the characters in the array
		return Arrays.equals(keyboard1,txtFile2); //compares if both arrays 
		//contain the same element and return result
	}

	//loop goes through words and print them out
	private static void checkIfAnagramExists(String word){
		for(int i = 0; i < textWords.size(); i ++){ //loops through wordlist
			if(isAnagram(word, textWords.get(i)) && 
				!word.equals(textWords.get(i))) //check if anagram exists
				System.out.println(textWords.get(i));
		}
	}
}