/**
 * Created by ruikangtao on 2/1/17.
 */
public class Linkedlist{
    public Linkedlist() {}
    public Chesspiece chess;
    Linkedlist next = null;

    public Linkedlist(Chesspiece c){
        chess = c;
    }

    public Linkedlist(Chesspiece c, Chesspiece n){
        chess = c;
        next = new Linkedlist(n);
    }

    public void setChess(Chesspiece c ){
        chess = c;
    }

    public void setNext(Linkedlist c) {
        next = c;
    }

    public Linkedlist getNext()
    {
        return next;
    }
    public Chesspiece getChess()
    {
        return chess;
    }
}