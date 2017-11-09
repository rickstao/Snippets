/**
 * Subclass for bishop 
 */
public class bishop extends Chesspiece { // bishop inherits Chesspiece
    public bishop(int a,int b) {
        this.row = a;
        this.col = b;
      
    }
    public boolean canAttack(Chesspiece b) { // attack method: return true if they can attack each other
        if(Math.abs(getCol()-b.col)==Math.abs(getRow()-b.row)) { //check diagonals 
            return true; 
        }
        return false;
    }

    public String toString() {// return uppercase if color is 1, else return lowercase
        if(getColor()==1) {
            return "B";
        }
        return "b";
    }
}
