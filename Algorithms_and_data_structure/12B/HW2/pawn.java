/*
 * Subclass for pawn
 */
public class pawn extends Chesspiece {// pawn inherits Chesspiece
    public pawn(int a,int b) {
        this.row = a;
        this.col = b;
    }
    public boolean canAttack(Chesspiece p) {
        if(getColor()==0) { //  it's lowercase
            if(p.row-1==getRow()) {// check if row -1 has any pieces
                if(p.col-getCol()==1||p.col-getCol()==-1) { 
                    return true;
                }
            }
        }
        else {//uppercase
            if(p.row+1==getRow()) {// check if row -1 has any pieces
                if(p.col-getCol()==1||p.col-getCol()==-1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {// return uppercase if color is 1, else return lowercase
        if(getColor()==1) {
            return "P";
        }
        return "p";
    }
}
