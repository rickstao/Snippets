import java.util.*;
import java.io.*;

class FindAnagrams{
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new FileInputStream(args[0]));
		Scanner inPut = new Scanner(System.in);

		int size = in.nextInt(); //read all the words in the list / length.		
		String [] list = new String [size]; //declaring a string array.

        int i = 0;
		while(i<size){ //while loop puts each word into the string array according to the index.
			list[i] = in.next();
			i++;
		}

		int [] prime = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
		char [] hashTable = new char {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z};
		while(i<26){
			(char)hashTable[i] = prime[i];
		}

		/*StringBuffer word = new StringBuffer (in.next()); //declaring StringBuffer


		System.out.println(word);
		char c;
		for (int i = 0; word.length(); i++){
			c = (char) ('a' + 1);
			System.out.println(c + " " + list[i])
		}
		*/

		/*int wV = 1;
		for (i = 0; i < w.length(), i++){
		wV = wV * word.getChar(i);			
		}
		*/
	}
}