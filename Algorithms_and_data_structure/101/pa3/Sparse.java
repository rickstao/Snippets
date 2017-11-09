/**
 *Ruikang Tao
 *rtao6
 *Programming Assignment 3
 */

import java.io.*;
import java.util.Scanner;

public class Sparse{
	public static void main(String[] args) throws IOException{
		if(args.length < 2){
			throw new RuntimeException ("Error: invalid number of files.");
		}
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));

		// scanner scans and stores # of NNZs
		int size = in.nextInt();
		int aNNZ = in.nextInt();
		int bNNZ = in.nextInt();

		Matrix A = new Matrix(size);
		Matrix B = new Matrix(size);

		// scanner scans and store values into two matrices
		in.nextLine();
		for(int i=1; i<aNNZ+1; i++){
			A.changeEntry(in.nextInt(), in.nextInt(), in.nextDouble());
		}
		in.nextLine();
		for(int i=1; i<bNNZ+1; i++){
			B.changeEntry(in.nextInt(), in.nextInt(), in.nextDouble());
		}

		// writing onto output file
		out.println("A has "+aNNZ+" non-zero entries:");
        out.println(A);
        out.println("B has "+bNNZ+" non-zero entries:");
        out.println(B);
        out.println("(1.5)*A = ");
        out.println(A.scalarMult(1.5));
        out.println("A+B = ");
        out.println(A.add(B));
        out.println("A+A = ");
        out.println(A.add(A));
        out.println("B-A = ");
        out.println(B.sub(A));
        out.println("A-A = ");
        out.println(A.sub(A));
        out.println("Transpose(A) = ");
        out.println(A.transpose());
        out.println("A*B = ");
        out.println(A.mult(B));
        out.println("B*B = ");
        out.println(B.mult(B));

        // clase scanner and writer
        in.close();
        out.close();
	}
}