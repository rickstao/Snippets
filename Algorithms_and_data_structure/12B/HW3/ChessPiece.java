// Filename: ChessPiece.java
//
// Contains the class ChessPiece that represents a chesspiece
// which is the super class for all the chesspieces


class ChessPiece {
    
    public int row; // row where the chesspiece is present
    public int col; // col where the chesspiece is present
    private boolean color; // color of the chesspiece
    
    // constructor without any args
    public ChessPiece() {
        this.row = -1;
        this.col = -1;
        this.color = false;
    }
    
    // constructor with args
    public ChessPiece(int row, int col, boolean color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }
    
    // More like a copy constructor
    public ChessPiece(ChessPiece piece) {
        this.row = piece.row;
        this.col = piece.col;
        this.color = piece.color;
    }
    
    // return the row of the current chesspiece
    public int getRow() {
        return this.row;
    }
    
    // return the col of the current chesspiece
    public int getCol() {
        return this.col;
    }
    
    // return the color of the current chesspiece
    public boolean getColor() {
        return this.color;
    }
    
    // Dummy method to check attack
    // It will be overridden by each of the child classes that inherit ChessPiece
    // Input: integer row and column to look for
    // Output: boolean which returns false (dummy) at all point of time
    public boolean isAttacking(int row, int col) {
        // Do nothing. Just return false for everything
        return false;
    }
    public boolean checkPath(int row, int col,Chessmoves c){
        return true;
    }
    public int[] getMoves(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int[] arrayOfInt = new int[0];
    return arrayOfInt;
  }
}

// End
