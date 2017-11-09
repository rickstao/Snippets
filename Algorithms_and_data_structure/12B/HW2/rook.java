/**
 * Subclass for rook
 */
public class rook extends Chesspiece {//rook inherits Chesspiece
    public rook(int a,int b) {
        this.row = a;
        this.col = b;
       
    }
    public boolean canAttack(Chesspiece r) {
        if(getCol()==r.col||getRow()==r.row) {//check if they are on the same row or col
            return true;
        }
        return false;
    }

    public String toString() { // return uppercase if color is 1, else return lowercase
        if(getColor()==1) {
            return "R";
        }
        return "r";
    }
}