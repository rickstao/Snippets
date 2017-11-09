// Filename: Rook.java
//
// Contains the class Rook that represents a rook chesspiece


class Rook extends ChessPiece {
   

  // Default constructor sets loc to infeasible (negative) values
  public Rook()
  {
    super();
  }

  // Constructor creates Rook with row, col and color
  public Rook(int row, int col, boolean color)
  {
    super(row, col, color);
  }

  // Boolean function that determines if self (which is a rook) is attacking another chesspiece at location row, col, given as argument
  // Input: integer row and col
  // Output: True if self is attacking the chesspiece at position (row, col), false otherwise
  public boolean isAttacking(int row, int col)
  {
      if (this.getRow() == row || this.getCol() == col) // if self has same row or column as chesspiece, self is attacking
          return true;
      else
          return false; // self is not attacking chesspiece
  }
    public boolean checkPath(int row, int col,Chessmoves c){
        if(row-this.getRow() == 0&& (col-this.getCol())>0){//target at +x-axis
            int count= Math.abs(col-this.getCol())-1;
            for(int i=0;i<count;i++){
                int a=this.getRow();
                int b=this.getCol();
                b++;
                if(c.findChessPiece(a,b)!=null)
                    return false;
            }
            return true;
        }
        
        if((row-this.getRow()) == 0 && (col-this.getCol())<0){// target at -x-axis
            int count= Math.abs(col-this.getCol())-1;
            for(int i=0; i<count; i++){
                int a=this.getRow();
                int b=this.getCol();
                b--;
                if(c.findChessPiece(a,b)!=null);
                return false;
            }
            return true;
        }
        if((row-this.getRow())>0&& (col-this.getCol())==0){//target at +y-axis
            int count= Math.abs(row-this.getRow())-1;
            for(int i=0;i<count;i++){
                int a=this.getRow();
                int b=this.getCol();
                a++;
                if(c.findChessPiece(a,b)!=null)
                    return false;
            }
            return true;
        }
        if((row-this.getRow())<0&& (col-this.getCol())==0){//target at -y-axis
            int count= Math.abs(row-this.getRow())-1;
            for(int i=0;i<count;i++){
                int a=this.getRow();
                a--;
                int b=this.getCol();
                if(c.findChessPiece(a,b)!=null)
                    return false;
            }
            return true;
        }
        return false;
    }

}
// End
