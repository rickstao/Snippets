/*
 * Subclass for king e
 */
public class king extends Chesspiece { //king inherits Chesspiece
    public king(int a, int b) {
        this.row = a;
        this.col = b;
    }


    public boolean canAttack(Chesspiece k) {
        if(Math.abs(k.row-k.row)<=1 && Math.abs(k.col-col)<=1){  
            return true;
        }//check if there are any peiece around king within 1 block
        return false;
    }

    public String toString() {// return uppercase if color is 1, else return lowercase
        if(getColor()==1) {
            return "K";
        }
        return "k";
    }
}