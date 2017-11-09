import java.util.*;
import java.io.*;

public class TicTacToe{

	
	static boolean aiMoved = false;

	
	static final int[][][] lines = {
	{{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each lev
	{{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
	{{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
	{{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
	{{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
	{{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
	{{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2     
	{{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
	{{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
	{{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
	{{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
	{{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
	{{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
	{{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
	{{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
	{{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
	{{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   cols in each lev
	{{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1    
	{{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
	{{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
	{{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
	{{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
	{{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
	{{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
	{{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
	{{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
	{{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
	{{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
	{{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
	{{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
	{{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
	{{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
        {{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
        {{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
        {{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
        {{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
        {{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
        {{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
        {{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
        {{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
        {{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
        {{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
        {{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
        {{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
        {{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
        {{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
        {{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
        {{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
        {{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
        {{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
        {{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
        {{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
        {{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
        {{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
        {{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
        {{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
        {{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
        {{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
        {{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
        {{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
        {{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
        {{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
        {{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
        {{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
        {{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
        {{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
        {{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
        {{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
        {{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right      
        {{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
        {{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
        {{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
        {{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
        {{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
        {{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
        {{3,3,0},{2,2,1},{1,1,2},{0,0,3}}
    };



    public static void main(String[] args) throws Exception{

    	aiMoved = false;

    	int [][][] board = new int[4][4][4];

    	while(true){

    		aiMoved = false;

    		printBoard(board);
			//print the board	

    		System.out.print("Type a 3 digit number where each number can only be 0-3: ");

    		Scanner in =  new Scanner(System.in);

    		int userMove = in.nextInt();

    		int level = userMove/100;

    		int row = userMove%100/10;

    		int column = userMove%10;

    		board[level][row][column] = 5;
    		
    		checkRow(board);

    		comRand(board);
    	}


    }

    public static void comRand(int[][][] board){
    	if (!aiMoved) {
    		while(true){
    			int a=(int)(Math.random()*4);
    			int b=(int)(Math.random()*4);
    			int c=(int)(Math.random()*4);
    			if (board[a][b][c] == 0) {
    				board[a][b][c] = 1;
    				break;
    				
    			}
    		}
    		
    	}
    }
    






    public static void printBoard(int[][][] board){
    	int draw = 0;
	//this part creates the format for the board
    	for(int i=3; i>=0; i--){

    		for(int j=3; j>=0; j--){

    			for(int h=j+1; h>0 ;h--)

    				System.out.print(" ");

    			System.out.print(i + "" + j + " ");

    			for(int k=0; k<4; k++){
    				if(board[i][j][k] == 0)

    					System.out.print("_ ");

    				if(board[i][j][k] == 1){

    					System.out.print("o ");
    					draw++;
    				}
    				if(board[i][j][k] == 5){
    					System.out.print("x ");
    				}
    			}
    			System.out.println();
    		}
    		System.out.println();
    	}
    	System.out.println("   0 1 2 3");
    }

    public static void checkRow(int [][][] board){
    	for (int i=0; i<76; i++){
    		int sum=0;
    		for (int j=0; j<4;j++){
			int a=lines[i][j][0];  //searches for values in the array
			int b=lines[i][j][1];
			int c=lines[i][j][2];	
			sum+= board[a][b][c];
			}

			if (sum == 3 && aiMoved == false) {
				for (int j=0; j<4 ;j++) {
					int a=lines[i][j][0];
					int b=lines[i][j][1];
					int c=lines[i][j][2];
					if (board[a][b][c] == 0) {
						board[a][b][c] = 1;
						printBoard(board);
						System.out.println("The AI is the winner!");
						System.exit(1);
						
					}
					
				}
			
			}
			if (sum == 15 && aiMoved == false) {
				for (int j=0; j<4 ;j++) {
					int a=lines[i][j][0];
					int b=lines[i][j][1];
					int c=lines[i][j][2];
					if (board[a][b][c] == 0) {
						board[a][b][c] = 1;
						aiMoved = true;

					}			



				}
			}

			if (sum == 20 && aiMoved == false) {
				printBoard(board);
				System.out.println("The User is the winner!");
				System.exit(1);
			

			}

		}	

	}

	public static void diag(int [][][] board){
		if (!aiMoved) {
			for (int i=0; i<4 ;i++ ) {
				for (int j=0;j<4 ;j++ ) {
					int sum1 = 0;
					for (int k=0;k<4 ;k++ )     //gets sum of row being stationary
					sum1 += board[i][j][k];
					for (int p=0;p<4 ;p++ ) {
						int sum2 = sum1;
						for (int h=0;h<4 ;h++ ) {
							if (j!=h) {
								sum2 += board[i][h][p];
								
							}//end of h if statement
							
						}//end of h loop
						if (!aiMoved && (sum2 == 4 || sum2 == 20)) {
							board[i][j][p] = 1;
							aiMoved = true;
						}//end of if statement with 4 and 20
					}//end of p loop



					
					
				}//end of j loop


				
			}//end of i loop

			
		}//end if
	}//end of function

	public static void diag2(int[][][] board){
		if (!aiMoved) {
			for (int i=0;i<4 ;i++ ) {
				int fRow=0;
				int bRow=0;
				int t=3;
				for (int h=0;h<4 ;h++,t-- ) {
					fRow += board[i][h][h];
					bRow += board[i][h][t];
				 }//end of h forloop 
				 for (int j=0;j<4 ;j++ ) {
				 	t =3;
				 	int m=0;
				 	int valueRow1 = fRow;
				 	int valueRow2 = bRow;
				 	for (int k=0;k<4 ;k++ ) {
				 		if (j!=k) 
				 			valueRow1 = board[i][j][k];
				 		if (j!=t) 
				 			valueRow2 = board[i][j][k];
				 		else
				 			m=k;		
					// if statement for valuerow2

					// if statement for valuerow1

					}//end of k loop
					if (!aiMoved && (valueRow1 == 4 || valueRow1 == 20)) {
						board[i][j][j] = 1;
						aiMoved = true;
						
					}//end of if statement with 4 and 20 valuerow1 
					if (!aiMoved && (valueRow2 == 4 || valueRow2 == 20)) {
						board[i][j][m] = 1;
						aiMoved = true;

								}// end of 2nd if statement with 4 and 20 for valuerow2 			

				}//end of j loop

				for (int j=0;j<4 ;j++ ) {
					t =3;
					int m=0;
					int vRow1 = fRow;
					int vRow2 = bRow;
					for (int k=0;k<4 ;k++ ) {
						if (j!=k) 
							vRow1 = board[i][k][j];
						if (j!=t) 
							vRow2 = board[i][k][j];
						else
							m=k;

					}//end of k loop
					if (!aiMoved && (vRow1 == 4 || vRow1 == 20)) {
						board[i][j][j] = 1;
						aiMoved = true;
						
					}//end of if statement with 4 and 20 valuerow1 
					if (!aiMoved && (vRow2 == 4 || vRow2 == 20)) {
						board[i][m][j] = 1;
						aiMoved = true;
					}

				}//end of i loop

			}//end of first if statement


		}
	}//end of function
}