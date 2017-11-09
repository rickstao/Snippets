/*
	This program was done by myself (Ruikang Tao). My knowledge knowledge in java is, in 
	fact, very limited, so I did put a good amount of time in this program. I spent about
	6 hours working on this program. Thank you for taking your time to grade this assignment.
*/

/**
	*Assignment #1.
	*This program generates a word scrambler/anagram.
	*Author: Ruikang Tao (rtao6@ucsc.edu)
*/

import java.util.*;
import java.io.*;

class AnagramPuzzleGenerator {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new FileInputStream(args[0]));
		int size = in.nextInt();//first item is the number of words
		Random r = new Random();
		int random = r.nextInt(size) + 1;//set random int according to word length
		
		int i = 1;//int starts at 1 because the word at index o is the number of the words
		while (i<random){ //while loop scans the word by each letter
			in.next();
			i++;
		}
		StringBuffer w = new StringBuffer (in.next());//declaring the first string buffer
		StringBuffer sb = new StringBuffer ("");//declaring the second string buffer with null
		
		int n;
		int count =0;
		while(w.length() != 0){//while loop randomly selects a letter and puts into sb.
			n = r.nextInt(w.length());//randomly selects letter at index n
			sb.append(w.charAt(n));//puts letter into new string buffer sb
			w.deleteCharAt(n);//deletes the letter from string buffer w so it does not get
			//selected again
			count++;

		}
		System.out.println(sb);//prints out the new word
	}
}