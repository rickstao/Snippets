// TwentyOnePickup.java
import java.util.*;
public class TwentyOnePickup {
  /**
  * Play the game of Twenty-One Pickup. 
  * The user and the computer take turns removing
  * from 1 to 3 stones from a pile. There are 21
  * stones in the pile to start with. 
  * The last one to remove a stone wins.
  */
  public static void main(String[] args) {
    printInstructions();
    // create the initial pile with 21 stones
    int numberOfStones = 21;
    // keep track of who moved last
    boolean playerMovedLast = false;
    while (numberOfStones > 0) {
      numberOfStones = playerMove(numberOfStones);
      playerMovedLast = true;
      if (numberOfStones > 0){
        numberOfStones = computerMove(numberOfStones);
        playerMovedLast = false;
      }
    }
    // print the outcome
    if (playerMovedLast)
      System.out.println("Congratulations, you won.");
    else
      System.out.println("Better luck next time.");
  }
  /**
  * printInstructions prints the initial instructions
  */
  static void printInstructions() {
    System.out.println(
      "The object of this game is to remove the last"
      + " stone.\n"
      + "There are 21 stones in the pile to start with.\n"
      + "You may remove from 1 to 3 stones on each move.\n"
      + "Good Luck!");
  }
  /**
  * playerMove completes one move by the player.
  * @param numberOfStones 
  *     The number of stones reamining in the pile.
  * @return 
  *    The number of stones remaining after the user's move.
  */
  static int playerMove(int numberOfStones) {
    int move = getUserMove(numberOfStones);
    numberOfStones = numberOfStones - move;
    System.out.println("There are " + numberOfStones
                       + " stones remaining.");
    return numberOfStones;
  }
  /**
  * computerMove completes one move by the computer.
  * @param numberOfStones 
  *    The number of stones reamining in the pile.
  * @return 
  *    The numberOfStones remaining after the
  *    computer's move.
  */
  static int computerMove(int numberOfStones) {
    int move;
    if (numberOfStones <= 3) {
      move = numberOfStones; /* remove the rest */
    }
    else {
      move = numberOfStones % 4;
      if (move == 0) move = 1;
    }
    numberOfStones = numberOfStones - move;
    System.out.println("The computer removes " + move
        + " stones leaving " + numberOfStones + ".");
    return numberOfStones;
  }
  /**
  * getUserMove reads in the user's move, only
  *    accepting legal inputs.
  * @param numberOfStones 
  *    The number of stones reamining in the pile.
  * @return 
  *    The number of stones selected for removal by
  *    the user.
  */
  static int getUserMove(int numberOfStones) {
    System.out.println("Your move - how many stones"
                       + " do you wish to remove?");
    int move = scan.nextInt();
    while (move > numberOfStones || move < 1 || move > 3) {
      if (numberOfStones >= 3)
        System.out.println("Sorry," +
            " you can only remove 1 to 3 stones.");
      else
        System.out.println("Sorry, you can only "
            + "remove 1 to " + numberOfStones + " stones.");
      System.out.println("How many stones"
                         + " do you wish to remove?");
      move = scan.nextInt();
    }
    return move;
  }
  static final Scanner scan = new Scanner(System.in);
}
