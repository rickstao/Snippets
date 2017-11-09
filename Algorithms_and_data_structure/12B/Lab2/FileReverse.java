/*
//FileReverse.java
//This file reverse string input
//some references come from FileTokens.java and FileCopy.java from Lab2
*/

import java.io.*;
import java.util.Scanner;

class FileReverse{
	public static void main(String[] args) throws IOException{
		//check if command line arguments are valid
		if(args.length<2){
			System.out.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}

		int lineNumber = 0;

		//open file
		Scanner scan = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

		//read lines from in
		while(scan.hasNextLine()){
			lineNumber++;
			//trim
			String line = scan.nextLine().trim() + " ";
			//split around space
			String[] token = line.split("\\s+");
			//print out tokens
			int n = token.length;
			for(int i=0; i<n; i++){
				out.println(" " + stringReverse(token[i]));
			}
		}
		//close files
		scan.close();
		out.close();
	}

	//this function reverses string token
	public static String stringReverse(String s){
		char[] in = s.toCharArray();
    	int begin=0;
    	int end=in.length-1;
    	char temp;
    	while(end>begin){
        	temp = in[begin];
        	in[begin]=in[end];
        	in[end] = temp;
        	end--;
        	begin++;
    	}
    	return new String(in);
	}
}