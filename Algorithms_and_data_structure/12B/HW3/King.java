// Filename: King.java
//
// Contains the class King that represents a king chesspiece


class King extends ChessPiece {

    // Default constructor sets row and col to infeasible (negative) values
    public King()
    {
        super();
    }

    // Constructor creates King with given row, col and color
    public King(int row, int col, boolean color)
    {
        super(row, col, color);
    }

    // Boolean function that determines if self (which is a king) is attacking another chesspiece, given as argument
    // Input: integer row, col
    // Output: True if self is attacking chesspiece at location (row, col), false otherwise
    public boolean isAttacking(int row, int col)
    {
        int attackRow[] = {-1, -1, 0, 1, 0, 1, 1, -1}; // possible attack row positions
        int attackCol[] = {0, -1, -1, -1, 1, 1, 0, 1}; // possible attack col positions

        for(int i = 0; i < 8; i++) {
          if(this.getRow() + attackRow[i] == row && this.getCol() + attackCol[i] == col) {
            return true;
          }
        }
        return false;
    }
    
    public boolean checkPath(int row, int col,Chessmoves c) { 
  if (row - this.getRow() == 1 && col - this.getCol() == 0) {// (1,0) 
   // 1 step up 
   int a = this.getRow(); 
   int b = this.getCol(); 
   a++; 
   if (c.findChessPiece(a, b) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == -1 && col - this.getCol() == 0) {// (-1,0) 
   // 1 step up 
   int cj = this.getRow(); 
   int d = this.getCol(); 
   cj--; 
   if (c.findChessPiece(cj, d) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == 0 && col - this.getCol() == -1) {// (0,-1) 
   // 1 step up 
   int e = this.getRow(); 
   int f = this.getCol(); 
   f--; 
   if (c.findChessPiece(e, f) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == 0 && col - this.getCol() == 1) {// (0,1) 
   // 1 step up 
   int g = this.getRow(); 
   int h = this.getCol(); 
   h++; 
   if (c.findChessPiece(g, h) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == 1 && col - this.getCol() == -1) {// (1,-1) 
   // 1 step up 
   int i = this.getRow(); 
   int j = this.getCol(); 
   i++; 
   j--; 
   if (c.findChessPiece(i, j) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == -1 && col - this.getCol() == -1) {// (-1,-1) 
   // 1 step up 
   int k = this.getRow(); 
   int l = this.getCol(); 
   k--; 
   l--; 
   if (c.findChessPiece(k, l) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == 1 && col - this.getCol() == 1) {// (1,1) 
   // 1 step up 
   int m = this.getRow(); 
   int n = this.getCol(); 
   m++; 
   n++; 
   if (c.findChessPiece(m, n) != null) { 
    return false; 
   } 
   return true; 
  } 
  if (row - this.getRow() == -1 && col - this.getCol() == 1) {// (-1,1) 
   // 1 step up 
   int p = this.getRow(); 
   int q = this.getCol(); 
   p--; 
   q++; 
   if (c.findChessPiece(p, q) != null) { 
    return false; 
   } 
   return true; 
  } 
  return true; 
 }
}

// End
