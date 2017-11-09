// Filename: Pawn.java
//
// Contains the class Pawn that represents a pawn chesspiece


class Pawn extends ChessPiece {

  // Default constructor sets loc to infeasible (negative) values
  public Pawn()
  {
    super();
  }

  // Constructor creates Pawn with col, row and color
  public Pawn(int row, int col, boolean color)
  {
    super(row, col, color);
  }

  // Boolean function that determines if self (which is a pawn) is attacking another chesspiece at row and col, given as argument
  // Input: int row and col
  // Output: True if self is attacking the chesspiece at position row and col, false otherwise
  public boolean isAttacking(int row, int col)
  {
      int[] attackRow = {0, 0};
      int[] attackCol = {0, 0};

      if(this.getColor() == true) {
        // if it is a white pawn
        // then it advances from bottom to top (unidirectional) in a chessboard
        // attacks only one step diagonals
        attackRow[0] = 1;
        attackRow[1] = 1;
        attackCol[0] = -1;
        attackCol[1] = 1;
      }
      else if(this.getColor() == false) {
        // if it is a black pawn
        // then it advances from top to bottom (unidirectional) in a chessboard
        // attacks only one step diagonals
        attackRow[0] = -1;
        attackRow[1] = -1;
        attackCol[0] = -1;
        attackCol[1] = 1;
      }
      else {
        Utilities.errExit("Invalid color for a pawn");
      }
      for(int i = 0; i < 2; i++) {
        if(this.getRow() + attackRow[i] == row && this.getCol() + attackCol[i] == col) {
          return true;
        }
      }
      return false;
  }
    
  public boolean checkPath(int row, int col,Chessmoves c) { 
  if (row - this.getRow() == 1 && col - this.getCol() == 0) {// pawn moves 
                 // 1 step up 
   int a = this.getRow(); 
   int b = this.getCol(); 
   a++; 
   if (c.findChessPiece(a, b) != null) { 
    return false; 
   } 
   return true; 
  } 
 
  if (row - this.getRow() == 2 && col - this.getCol() == 0) {// pawn moves 
                 // 2 steps 
                 // up 
   for (int i = 0; i < 2; i++) { 
    int x = this.getRow(); 
    int y = this.getCol(); 
    x++; 
    if (c.findChessPiece(x, y) != null) { 
     return false; 
    } 
   } 
   return true; 
  } 
 
  if (row - this.getRow() == 1 && col - this.getCol() == 1) {// pawn moves 
                 // right top 
   int cw = this.getRow(); 
   int d = this.getCol(); 
   cw++; 
   d++; 
   if (c.findChessPiece(cw, d) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == 1 && col - this.getCol() == -1) {// move left 
                 // top 
   int e = this.getRow(); 
   int f = this.getCol(); 
   e++; 
   f--; 
   if (c.findChessPiece(e, f) != null) { 
    return false; 
   } 
   return true; 
  } 
  return false; 
 }
}

// End
