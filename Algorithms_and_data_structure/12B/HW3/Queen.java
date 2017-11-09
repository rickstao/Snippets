// Filename: Queen.java
//
// Contains the class Queen that represents a queen chesspiece


class Queen extends ChessPiece {
    

    // Default constructor sets row and col to infeasible (negative) values
    public Queen()
    {
        super();
    }

    // Constructor creates Queen with col c and row r
    public Queen(int row, int col, boolean color)
    {
        super(row, col, color);
    }

    // Boolean function that determines if self (which is a queen) is attacking another chesspiece, given as argument
    // Input: integer row and col
    // Output: True if self is attacking another chesspiece at (row, col), false otherwise
    public boolean isAttacking(int row, int col)
    {
        if (this.getRow() == row || this.getCol() == col) // if self has same row or column as chesspiece, self is attacking
            return true;
        else if (Math.abs(this.getRow() - row) == Math.abs(this.getCol() - col)) // if self is on same diagonal as chesspiece, this is attack. we use absolute values to determine diagonal
            return true;
        else
            return false; // self is not attacking chesspiece
    }
    public boolean checkPath(int row, int col,Chessmoves c){
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
        
        if((row-this.getRow())==0&& (col-this.getCol())>0){//target at +x-axis
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
        
        return false;
    }
}

// End
