/**
* Created by ruikangtao on 2/1/17.
*/

/**
 * This program generates a linked list that contains chess pieces with various types
 * read from input.txt. The main purpose is to check whether two pieces are aiming for
 * the same spot and whether there exist attacking targets. The result is printed out
 * in analysis.txt after execution.
 *
 * author: rtao6 & hxu24
 * additional reference/source: https://docs.oracle.com/javase/tutorial/java/javaOO/classes.html
 * http://stackoverflow.com/questions/10042/how-do-i-create-a-linked-list-data-structure-in-java
 * lab2
 * minor issues discuessed with hxu24 and xguo19
 * */


import java.io.*;
import java.util.*;

public class Chessboard {

    /*
    initialize a linked list with chess pieces
     */
    public static Linkedlist pieces;

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        Scanner input = new Scanner(file);
        PrintWriter output = new PrintWriter(new FileWriter("analysis.txt"));
        int count=0;

        /*
        read in content from input text
         */
        while(input.hasNextLine()) {
            /*
            While loop reads two lines at a time and generate a new Linkedlist of ChessPiece
             */
            if(count%2==0) {
                pieces = new Linkedlist();
                Linkedlist current = pieces;
                String[] tokens = input.nextLine().split("\\s+");

                for(int i = 1; i<tokens.length ;i=i+3) {
                    int x = Integer.parseInt(tokens[i+1]);
                    int y = Integer.parseInt(tokens[i+2]);
                    if(i==1) {
                        /*
                        switch statement defines type of subclasses in linked list
                         */
                        switch(tokens[i]){
                            case "k": case "K"://create king
                                current.setChess(new king(x,y));
                                if(tokens[i].equals("K"))
                                    current.getChess().setBlack();
                                break;

                            case "q": case "Q"://create queen
                                current.setChess(new queen(x,y));
                                if(tokens[i].equals("Q"))
                                    current.getChess().setBlack();
                                break;

                            case "n": case "N"://create knight
                                current.setChess(new knight(x,y));
                                if(tokens[i].equals("N"))
                                    current.getChess().setBlack();
                                break;

                            case "r": case "R"://create rook
                                current.setChess(new rook(x,y));
                                if(tokens[i].equals("R"))
                                    current.getChess().setBlack();
                                break;

                            case "b": case "B"://create bishop
                                current.setChess(new bishop(x,y));
                                if(tokens[i].equals("B"))
                                    current.getChess().setBlack();
                                break;

                            case "p": case "P"://create pawn
                                current.setChess(new pawn(x,y));
                                if(tokens[i].equals("P"))
                                    current.getChess().setBlack();
                                break;

                        }
                    }
                    else {
                        /*
                        linking objects after the first chess piece
                         */
                        switch (tokens[i]) {
                            case "k": case "K":
                                current.setNext(new Linkedlist(new king(x, y)));
                                if (tokens[i].equals("K"))
                                    current.getNext().getChess().setBlack();
                                break;

                            case "q": case "Q":
                                current.setNext(new Linkedlist(new queen(x, y)));
                                if (tokens[i].equals("Q"))
                                    current.getNext().getChess().setBlack();
                                break;

                            case "n": case "N":
                                current.setNext(new Linkedlist(new knight(x, y)));
                                if (tokens[i].equals("N"))
                                    current.getNext().getChess().setBlack();
                                break;

                            case "r": case "R":
                                current.setNext(new Linkedlist(new rook(x, y)));
                                if (tokens[i].equals("R"))
                                    current.getNext().getChess().setBlack();
                                break;

                            case "b": case "B":
                                current.setNext(new Linkedlist(new bishop(x, y)));
                                if (tokens[i].equals("B"))
                                    current.getNext().getChess().setBlack();
                                break;

                            case "p": case "P":
                                current.setNext(new Linkedlist(new pawn(x, y)));
                                if (tokens[i].equals("P"))
                                    current.getNext().getChess().setBlack();
                                break;
                        }
                        current = current.getNext();
                    }
                }
            }
            /*
            Printwriter prints results in analysis text file
             */
            else {
                if(!isValid()) {
                    output.println("Invalid");
                    input.nextLine();
                }
                else {
                    String[] location = input.nextLine().split("\\s+");
                    int col = Integer.parseInt(location[0]);
                    int row = Integer.parseInt(location[1]);
                    Chesspiece target = findQuery(col,row);
                    if(target==null) {
                        output.print("- ");
                    }
                    else {
                        output.print(target.toString()+" ");
                    }
                    isAttacking(output);
                }
            }
            count++;
        }
        input.close();
        output.close();
    }


    /*
    If attacking is true, print both the attacking chess piece and the target chess piece
    into analysis text
     */
    public static void isAttacking(PrintWriter output) {
        Linkedlist head = pieces;
        Linkedlist current = head.getNext();

        while(head.getNext()!=null){
            current=head.getNext();
            while(current!=null){
                if(head.getChess().canAttack(current.getChess())){
                    output.print(head.getChess().toString()+" ");
                    output.print(head.getChess().getCol()+" ");
                    output.print(head.getChess().getRow()+" ");
                    output.print(current.getChess()+" ");
                    output.print(current.getChess().getCol()+" ");
                    output.print(current.getChess().getRow());
                    output.println();
                    return;
                }
                current=current.getNext();
            }
            head=head.getNext();
        }
        output.println("-");
    }


    /*
    This method checks whether two chess pieces are not occupying the same place
    and there exist a black king and a white king. It returns true if premises are
    valise; return false otherwise.
     */
    public static boolean isValid() {
        int bKing=0;
        int wKing=0;
        Linkedlist head = pieces;
        Linkedlist current = head.getNext();
        if(current==null) {
            return false;
        }

        /*
        traversing through the linked list
         */
        while(head.getNext()!=null) {
            current = head.getNext();
            while(current!=null) {
                if(head.getChess().getCol()==current.getChess().getCol() &&
                        head.getChess().getRow()==current.getChess().getRow()){
                    return false;
                }
                current=current.getNext();
            }
            /*
            check kings status using using color
             */
            if(head.getChess() instanceof king) {
                if(head.getChess().getColor()==1) {
                    bKing++;
                }
                else {
                    wKing++;
                }
            }
            head=head.getNext();
        }
        if(bKing!=1||wKing!=1) {
            return false;
        }
        return true;
    }


    /*
    method tests whether the query exist on chessboard
     */
    public static Chesspiece findQuery(int col, int row) {
        Linkedlist current = pieces;
        while(current!=null) {
            if(current.getChess().getCol()==col && current.getChess().getRow()==row) {
                return current.getChess();
            }
            current=current.getNext();
        }
        return null;
    }
}

