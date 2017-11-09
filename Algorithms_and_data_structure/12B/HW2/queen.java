/**
 * Subclass for queen
 */

public class queen extends Chesspiece {//queen inherits Chesspiece
    public queen(int a,int b) {
        this.row = a;
        this.col = b;
       
    }

    public boolean canAttack(Chesspiece q) {
        if(getCol()==q.col||getRow()==q.row) {// check if they are on the same row or col
            return true;
        }
        if(Math.abs(getCol()-q.col)==Math.abs(getRow()-q.row)) {// check id they are on the same diagonal.
            return true;
        }
        return false;
    }

    public String toString() {// return uppercase if color is 1, else return lowercase
        if(getColor()==1) {
            return "Q";
        }
        return "q";
    }
}