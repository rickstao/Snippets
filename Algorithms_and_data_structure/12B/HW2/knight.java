/**
 * Subclass for knight
 */

public class knight extends Chesspiece {// knight inherits Chesspiece
    public knight(int a,int b) {
        this.row = a;
        this.col = b;
        
    }
    public boolean canAttack(Chesspiece n) { 
        if(Math.abs((n.col-getCol())*(n.row-getRow()))==2) {
            return true;
        }//check if there are any other pieces on "L"
        return false;
    }

    public String toString() {// return uppercase if color is 1, else return lowercase
        if(getColor()==1) {
            return "N";
        }
        return "n";
    }
}