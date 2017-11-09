// Filename: Bishop.java
//
// Contains the class Bishop that represents a bishop chesspiece


class Bishop extends ChessPiece {

  // Default constructor sets loc to infeasible (negative) values
  public Bishop()
  {
    super();
  }

  // Constructor creates Bishop at location (col, row) and color
  public Bishop(int row, int col, boolean color)
  {
    super(row, col, color);
  }

  // Boolean function that determines if self (which is a bishop) is attacking another chesspiece at row and col, given as argument
  // Input: int row and col
  // Output: True if self is attacking the chesspiece at position row and col, false otherwise
  public boolean isAttacking(int row, int col)
  {
      if (Math.abs(this.getRow() - row) == Math.abs(this.getCol() - col)) // if self is on same diagonal as chesspiece, this is attack. we use absolute values to determine diagonal
          return true;
      else
          return false; // self is not attacking chesspiece at position l
  }
    public boolean checkPath(int row, int col,Chessmoves c){
        
        if((row-this.getRow()) > 0 && (col-this.getCol())>0){ //first quad
            int count= Math.abs(row-this.getRow())-1;
            for(int i=0;i<count;i++){
                int e = this.getRow();
                int f =this.getCol();
                e++;
                f++;
               if(c.findChessPiece(e,f)!=null)
                    return false;
            }
            return true;
        }
        if((row-this.getRow()) > 0 && (col-this.getCol())<0){ //second quad
            int count= Math.abs(row-this.getRow())-1;
            for(int i=0;i<count;i++){
                int g = this.getRow();
                int h =this.getCol();
                g++;
                h--;
                if(c.findChessPiece(g,h)!=null)
                    return false;
            }
            return true;
        }
        
        //third quad
        if((row-this.getRow()) < 0 && (col-this.getCol())<0){
            int count= Math.abs(row-this.getRow())-1;
            for(int i=0;i<count;i++){
                int cl = this.getRow();
                int d =this.getCol();
                cl--;
                d--;
                if(c.findChessPiece(cl,d)!=null)
                    
                    return false;
                
            }
            return true;
        }
        
        if((row-this.getRow()) < 0 && (col-this.getCol())>0){ //fourth quad
            
            int count= Math.abs(row-this.getRow())-1;
            for(int i=0;i<count;i++){
                int k = this.getRow();
                int l =this.getCol();
                k++;
                l--;
                if(c.findChessPiece(k,l)!=null)
                    return false;
            }
            return true;
        }
        
        return false;
        
    }
}

// End
