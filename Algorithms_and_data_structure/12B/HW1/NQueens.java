/*
author: Ruikang Tao (1491650) & Haonan Xu (1459517)
*/

import java.util.*;
import java.io.*;

public class NQueens {

    //declare an arraylist to store the value of solutions
    static List<String> resultList = new ArrayList<String>();
    
    //code starts here
    public static void main(String[] args) {
        //declare int to take in commandline args
        int N = Integer.parseInt(args[0]);
        int a = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        int[] position = new int[N];
        solveNQ(0, N, position);
        board(a,b);
        System.out.println(board(a,b));
        printTxt(a,b);
    }

    //printWriter function writes output to a text file
    static void printTxt(int a, int b){
        try{
        File file = new File("solution.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter pw = new PrintWriter(file);
        pw.println(board(a, b));
        pw.close();
      } catch(IOException e){
        e.printStackTrace();
      }
    }

    //function that sums up all the possible coordinates to a string
    static String board(int a, int b) {
        String result = "";
        String cord = a + " " + b;
        for (String str : resultList) {
            if (str.contains(cord)) {
                result=str.replace(",", "\r\n");
                break;
            }
        }
        //if string length is 0, no queen can be placed; otherwise return
        result = result.trim().length()==0 ? "No solution" : result;
        return result;
    }

    //function checks whether a spot is safe to place a queen
    static void isSave(int N, int[] position) {
        int i, j;
        String s = "";
        for (i = 0; i < N; i++) {
            String temp = "";
            for (j = 0; j < N; j++) {
                if (position[i] == j) {
                    //add 1 to the index to accomodate the chess board
                    temp = (i+1) + " " + (j+1) + ","; 
                }
            }
            s += temp;
        }
        //add the new string to the existing string
        resultList.add(s.substring(0, s.length() - 1));
    }

    //recursion function solves the n Queen problem
    static void solveNQ(int n, int N, int[] position) {
        int i, j;
        int count;
        if (n == N) {
            isSave(N, position);
            return;
        }
        for (i = 0; i < N; i++) {
            position[n] = i; // n is nth row; i is the queen's spot on nth row
            count = 1; //as if it's a safe spot
            for (j = 0; j < n; j++) {
                // first evaluation tests columns; second evaluation tests diagonals.
                if (position[n] == position[j]
                        || Math.abs(position[j] - position[n]) == (n - j))
                    count = 0;
            }
            if (count == 1) //if true, recursion to the next row
                solveNQ(n + 1,N,position);
            //next recursion, if false/count is 0, then goes into for loop
        }
    }
}