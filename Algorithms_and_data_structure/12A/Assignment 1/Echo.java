import java.util.*;
import java.io.*;

/**
   This is intended as a starter for a word scrambler (anagram generator)
   that scrambles a randomly selected word from a word file specified on the
   command line.

   This program simply echos the entire contents of the file to the console.
   It assumes that the first line of the input contains the number of words in the file
   (not including the count if you think of it as a word). 
   For this program, a word is any white space delimited sequence of characters.
   @author Charlie McDowell (minor mods Dean Bailey 08/23/07)
 */
class Echo {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in = new Scanner(new FileInputStream(args[0]));
	
	int size = in.nextInt();//first item is the number of words

	//while loop print out of words in file
	int i = 0;
	while ( i < size ) {
    
	    System.out.println(in.next());
	    i++;
	}


}