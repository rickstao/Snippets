// This homework was done through group work of rtao6, xliu66, hxu24
//
// Filename: Chessmoves.java
//
// Contains class Chessmoves that creates a linkedlist of chessboard where each node holds a chesspiece to be placed
//
// Tests include checking valid path, capturing chesspieces, determine checkmate
//
// This needs to be compiled with ChessPiece.java, Node.java, Utilities.java, King.java, Queen.java, Knight.java, Rook.java, Bishop.java and Pawn.java.
//
// This homework took reference from the solution files posted on the class website: https://users.soe.ucsc.edu/~sesh/Teaching/CMPS12B-17/hw.html
//
// Output:
//     - analysis.txt file is created corresponding board and validity check in input.txt
//
//
import java.io.*;

class Chessmoves {
    
    private static Node head; // linkedlist to store chesspieces
    private static int board_size; // board_size
    public static BufferedWriter writer; // write to write to file
    public static Utilities xhn = new Utilities();
    
    // constructor
    
    public Chessmoves() {
        head = new Node();
    }
    
    // Method to perform insertion at the front of the list
    // Input: Node to be inserted
    // Output: void
    public Node insert(Node piece) {
        Node temp = head.getNext();
        head.setNext(piece);
        piece.setNext(temp);
        return head;
    }
    
    // Method to find Node in a given location
    // Input: integer row and column to look for
    // Output: Node found
    public static Node findChessPiece(int row, int col) {
        Node piece = head.getNext();
        while (piece != null) {
            if (piece.getRow() == row && piece.getCol() == col) {
                return piece;
            }
            piece = piece.getNext();
        }
        return null;
    }
    
    // Method to count the number of chesspieces for a given type
    // This method will helps us check the validity case
    // Input: character color
    // Output: returns the count
    public int countPiecesOfType(char pieceType) {
        Node piece = head.getNext();
        int pieceCtr = 0;
        // loop through to check if the same piece type is found
        while (piece != null) {
            if (Utilities.returnChessPieceType(piece) == pieceType) {
                pieceCtr++;
            }
            piece = piece.getNext();
        }
        return pieceCtr;
    }
    
    // Method to count the number of chesspieces on a single location
    // This method will helps us check the validity case
    // Input: integer row and column
    // Output: returns the count
    public int countPiecesInLocation(int row, int col) {
        Node piece = head.getNext();
        int pieceCtr = 0;
        // loop through to check if any two pieces overlap
        while (piece != null) {
            if (piece.getRow() == row && piece.getCol() == col) {
                pieceCtr++;
            }
            piece = piece.getNext();
        }
        return pieceCtr;
    }
    
    // Method to check if two pieces occupy the same place
    // This method utilizes the countPiecesInLocation method to see
    // if there are more than two pieces in a single location
    // Input: none
    // Output: returns true if two pieces occupy same position
    public boolean twoPiecesOccupySamePosition() {
        Node piece = head.getNext();
        // loop through and see if any of the pieces overlap
        while (piece != null) {
            if (countPiecesInLocation(piece.getRow(), piece.getCol()) > 1) {
                return true;
            }
            piece = piece.getNext();
        }
        return false;
    }
    
    // Method to check validity
    // basically looks if there are not two chesspieces in the same location and
    // one each colored king is present
    // Input: none
    // Output: returns if it is valid or not
    public boolean checkValidity() {
        if (!twoPiecesOccupySamePosition() && countPiecesOfType('k') == 1 && countPiecesOfType('K') == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    // Method to check if any piece exists in the query location
    // Input: integer array query
    // Output: returns the piece (in character) if found otherwise just returns
    // '-'
    public char findChessPiece(int[] query) {
        int col = query[1];
        int row = query[0];
        Node foundPiece = findChessPiece(row, col);
        if (foundPiece != null) {
            return Utilities.returnChessPieceType(foundPiece);
        }
        return '-';
    }
    
    // Method to check if two nodes given are different or the same ones
    // It serves as a helper when trying to find the attack
    // Input: two nodes
    // Output: returns if they are same or different pieces
    public boolean isDifferent(Node one, Node other) {
        if (one.getRow() == other.getRow() && one.getCol() == other.getCol() && one.getColor() == other.getColor()) {
            return false;
        }
        return true;
    }
    
    // Method to see if any of the pieces attack
    // as soon as you encounter the first attack, just print it and return
    // Input: none
    // Output: returns nothing
    public void isOneAttackingOther() {
        // get the first valid chesspiece (remember not the head)
        Node piece = head.getNext();
        // loop through each of the remaining chesspieces and check for attack
        while (piece != null) {
            Node other = head.getNext();
            while (other != null) {
                if (isDifferent(piece, other) && piece.getChessPiece().isAttacking(other.getRow(), other.getCol())) {
                    writeToAnalysisFile(Utilities.returnChessPieceType(piece) + " " + piece.getCol() + " "
                        + piece.getRow() + " " + Utilities.returnChessPieceType(other) + " " + other.getCol() + " "
                        + other.getRow() + "\n");
                    return;
                }
                other = other.getNext();
            }
            piece = piece.getNext();
        }
        writeToAnalysisFile("-\n");
    }
    
    // Method to write to the analysis.txt file
    // Input: String to write
    // Output: void, just write
    public static void writeToAnalysisFile(String stringToWrite) {
        try {
            writer.write(stringToWrite);
        } catch (Exception e) {
            Utilities.errExit("Exception occurred while trying to write to file: writeToAnalysisFile"); // throw
            // a
            // generic
            // exception
            // if
            // failure
            // to
            // read
            // occurs
        }
    }
    
    // Method to iterate through the list and update a 2D array for printing the
    // board
    // onto the console
    // Input: integer board number read from input.txt
    // Output: void, jusr print the solution
    public static void convertFromListToMatrixAndPrint(int board_no) {
        // Initialize isFilled board
        char[][] isFilled = new char[board_size + 1][board_size + 1];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                isFilled[i][j] = '-';
            }
        }
        // iterate through the list and update isFilled matrix
        Node piece = head.getNext();
        while (piece != null) {
            isFilled[piece.getRow()][piece.getCol()] = Utilities.returnChessPieceType(piece);
            piece = piece.getNext();
        }
        
        System.out.println("Board No: " + (board_no / 2));
        Utilities.printSolution(isFilled, board_size);
    }
    
    // Method to read from input.txt
    // for each chessboard and query, perform all the required operations
    // an then proceed further
    // Input: none
    // Output: void, jusr read and perform requested operations
    public static void readFromInputFile() {
        
        int lineCtr = 0;
        
        Chessmoves c = null;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split(" ");
                // Reader assumes that the input format is as given in the
                // instruction
                // If the line is 2i, then I know that it is a configuration of
                // a Chessmoves
                // so create a new Chessmoves here, parse board size and insert
                // given chesspieces into the linked list
                if (lineCtr % 2 == 0) {
                    c = new Chessmoves();
                    board_size = Integer.parseInt(args[0]);
                    for (int i = 1; i < args.length; i += 3) {
                        head = c.insert(new Node(args[i].charAt(0), Integer.parseInt(args[i + 2]),
                           Integer.parseInt(args[i + 1])));
                        
                    }
                    
                    
                }
                
                
                else {
                  
                    
                 
                    int counter = 0;
                    int[] query = new int[args.length];
                    
                    for (int i = 0; i < args.length; i += 4) {
                        //System.out.println(args.length);
                        // as soon as you read the query perform the requested
                        // operations
                        
                        query[i] = Integer.parseInt(args[i+1]);
                        query[i + 1] = Integer.parseInt(args[i]);
                        query[i + 2] = Integer.parseInt(args[i + 3]);
                        query[i + 3] = Integer.parseInt(args[i + 2]);
                        //System.out.println(query[i]+""+query[i+1]+""+query[i+2]+""+query[i+3]);
                        
                        performOperations(c, query, lineCtr - 1, counter);
                        counter += 4;
                    }
                    c.writeToAnalysisFile("\n");
                       //About check
                    int j;
                    int[] arrayOfInt = new int[args.length];
                    for (j = 0; j < args.length; j++) {
                     arrayOfInt[j] = Integer.parseInt(args[j]);
                 }
                 
                 
                 Oper2(c, arrayOfInt);
                 writer.write("\n");
             }
             lineCtr++; 
                      // move to the next line
         }
         reader.close();
     } catch (NumberFormatException e) {
            Utilities.errExit("All arguments must be integers"); // throw error
            // incase
            // parsing
            // integer
            // fails
        } catch (IndexOutOfBoundsException e) {
            Utilities.errExit("Array index is out of bounds"); // throw error
            // when
            // inserting
            // elements into
            // arrays fail
        } catch (Exception e) {
            Utilities.errExit("Exception occurred trying to read file"); // throw
            // a
            // generic
            // exception
            // if
            // failure
            // to
            // read
            // occurs
        }
    }
    
    // Method to perform all the requested operations
    // namely, check validity, perform the search query
    // check for attack
    // Input: Chessmoves and the query
    // Output: returns the count
    public static void performOperations(Chessmoves c, int[] query, int board_no, int counter) {
        try {
          
            //convertFromListToMatrixAndPrint(board_no);
            //if(c.checkValidity() == false) {
            //   writer.write("Invalid\n");
            //   return;
            //System.out.println(query[counter+1]+" "+query[counter]+" "+query[counter+3]+" "+query[counter+2]+" ");
          if(c.findChessPiece(query[counter], query[counter + 1])==null){
            writer.write("Invalid"+" ");
            //System.out.println("first piece "+c.findChessPiece(query));
        }
        else{ 
            
            
            if(canMove(c.findChessPiece(query[counter], query[counter + 1]), query[counter + 2], query[counter + 3],c))
                writer.write("Valid"+" ");
            else
                writer.write("Invalid"+" ");
            
        }
        
        
            //}
        
        
            //writer.write("valid");
        
            // Find the chesspiece given in query location
            // writer.write(c.findChessPiece(query) + " ");
            // See if anyone attacks anyone else
            // c.isOneAttackingOther();
        
    }
    catch (Exception e) {
        Utilities.errExit("Error while performing operations");
    }
    
}

    // main method
public static void main(String[] args) {
    try {
            writer = new BufferedWriter(new FileWriter("analysis.txt")); // open
            // the
            // file
            // to
            // write
            readFromInputFile(); // read from input file and perform operations
            writer.close(); // close the writer
        } catch (Exception e) {
            Utilities.errExit("Error while creating BufferedWriter");
        }
        
    }

    // canMove method checks the validity of path
    // 
    public static boolean canMove(Node x, int row, int col,Chessmoves c) {
        try{
          
            ChessPiece firstPiece = x.getChessPiece();
            if (firstPiece.isAttacking(row, col))// check if firstPiece can get to the// destination
            {
                //System.out.println("It can go to the destination");
                if (c.findChessPiece(row, col) != null) {// then check if it is
                    // already a piece from
                    // linkedlist
                    if (c.findChessPiece(row, col).getColor() == x.getColor()) {//check if same color
                        return false;
                    }
                    
                    if (firstPiece.checkPath(row, col,c)) {//check if the path of firstpiece is blocked
                        //System.out.println("The deleted piece"+" "+(delete(c.findChessPiece(row, col)).getCol()+" "+delete(c.findChessPiece(row, col)).getRow()));
                        delete(c.findChessPiece(row, col));//delete second Node
                        //System.out.println("deleted node"+" "+x.getCol()+" "+x.getRow());
                        firstPiece.row = row;//move first piece to destination, only change row col
                        firstPiece.col= col;
                        return true;
                    }
                }
                else// not a piece
                {
                    if (firstPiece.checkPath(row, col,c))// check path
                    {
                        //System.out.println("before creating a new Node");
                       //System.out.println(xhn.returnChessPieceType(x));
                        head = c.insert(new Node(xhn.returnChessPieceType(x) ,row ,col));//create a new at destination
                        //System.out.println("This is the new Node" +" "+head.getNext().getCol()+" " +" "+head.getNext().getRow());
                        delete(x);
                        //System.out.println("deleted node"+" "+x.getCol()+" "+x.getRow());//delete first node
                       //System.out.println("The deleted piece"+" "+(delete(x).getCol()+" "+delete(x).getRow()));
                       // x.getChessPiece().row= row;
                        //x.getChessPiece().col= col;
                        return true;// move
                    }
                    return false;  
                }
                return false;
                
            }
            return false;
        }
        
        catch(Exception e){
            System.out.println("catched");
        }
        return false;
    }
    
    
    // method deletes the node that is captured
    public static Node delete(Node node) {
        Node prev = null;
        Node curr = head.getNext();
        while (curr != null && curr.getChessPiece()!= node.getChessPiece()) {
            prev = curr;
            curr = curr.getNext();
        }
        if (curr == null)
        {
            return curr;
        }
        if (prev == null)
        {
            head.next = head.next.next;
        }
        else
        {
            prev.next = curr.next;
        }
        return curr;
    }
    
    // Oper2 determines whether a king is in check
    public static void Oper2(Chessmoves xyz, int[] storeArr)
    {
     try{
        boolean situation1 = xyz.determineCheck(head, true);
        boolean situation2 = xyz.determineCheck(head, false);
        
        if ((!situation2) && (!situation1))
        {
            writeToAnalysisFile("All kings safe ");
        }
        else
        {
            if (situation1) {
              writeToAnalysisFile("White in check ");
          }
          if (situation2) {
              writeToAnalysisFile("Black in check ");
          }
      }
  }
  catch (Exception localException)
  {
      Utilities.errExit("Error while performing operations");
  }
}






      // method determines whether a king is checkmated
public boolean determineCheck(Node chessNode, boolean boo)
{
    Node hao1 = chessNode.getNext();
    Node hao2 = getKingNode(chessNode, boo);
    while (hao1 != null)
    {
      if ((isDifferent(hao1, hao2)) && (hao1.getChessPiece().isAttacking(hao2.getRow(),hao2.getCol())))
      {
        if ((Utilities.returnChessPieceType(hao1) == 'n') || (Utilities.returnChessPieceType(hao1) == 'N')) {
          return true;
      }
      int[] arrayOfInt = hao1.getChessPiece().getMoves(hao1.getRow(), hao1.getCol(), hao2.getRow(), hao2.getCol(), true);
      return checkBlock(chessNode, arrayOfInt, hao1.getRow(), hao1.getCol(), hao2.getRow(), hao2.getCol(), false);
  }
  hao1 = hao1.getNext();
}
return false;
}



// method returns a king node
public Node getKingNode(Node chessNode, boolean boo)
{
    Node hao = null;
    if (boo == true) {
      hao = findChessPiece(chessNode, 'k');
  } else {
      hao = findChessPiece(chessNode, 'K');
  }
  return hao;
}

//method checks if any piece is in path
public boolean checkBlock(Node chessNode, int[] storeArr, int r, int c, int newInt1, int newInt2, boolean boo)
{
    for (int i = 0; i < storeArr.length; i += 2)
    {
      Node hao = findChessPiece(chessNode, storeArr[(i + 1)], storeArr[i]);
      if (hao != null)
      {
        if (boo) {
          System.out.println("Invalid Move from " + c + " " + r + " to " + newInt2 + " " + newInt1 + ": Blocked by " + Utilities.returnChessPieceType(hao) + " at position " + storeArr[i] + " " + storeArr[(i + 1)]);
      }
      return false;
  }
}
return true;
}



  // method traverses to find piece
public Node findChessPiece(Node chessNode, int r, int c)
{
    Node hao = chessNode.getNext();
    while (hao != null)
    {
      if ((hao.getRow() == r) && (hao.getCol() == c)) {
        return hao;
    }
    hao = hao.getNext();
}
return null;
}
  // method finds pieces
public Node findChessPiece(Node chessNode, char chessChar)
{
    Node hao = chessNode.getNext();
    while (hao != null)
    {
      if (Utilities.returnChessPieceType(hao) == chessChar) {
        return hao;
    }
    hao = hao.getNext();
}
return null;
}


}
// End
