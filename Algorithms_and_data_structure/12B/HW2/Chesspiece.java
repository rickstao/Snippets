/*
 *Superclass Chesspiece 
 */ 
public class Chesspiece{
    public int col;
    public int row;
    public int color=0;

    public boolean canAttack(Chesspiece c){// general attack method can be overrided in subclass
        return true;
    }
    // get instance variables below
    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getColor() {
        return color;
    }

    public void setBlack() {
        color=1;
    }
}