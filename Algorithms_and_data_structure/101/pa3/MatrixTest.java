/**
 *Ruikang Tao
 *rtao6
 *Programming Assignment 3
 */

/*
public class MatrixTest{
	public static void main(String[] args){
		Matrix A = new Matrix(3);
		Matrix B = new Matrix(3);
		Matrix C = new Matrix(10);
		Matrix D = new Matrix(1000);
		int count = 1;

		A.changeEntry(1,1,1); A.changeEntry(1,2,2); A.changeEntry(1,3,3);
		A.changeEntry(2,1,4); A.changeEntry(2,2,5); A.changeEntry(2,3,6);
		A.changeEntry(3,1,7); A.changeEntry(3,2,8); A.changeEntry(3,3,9);

		System.out.println("Matrix A has " + A.getNNZ() + " NNZ");
		System.out.println("Matrix A: ");
		System.out.println(A);
		System.out.println("");

		B.changeEntry(1,1,9); B.changeEntry(1,2,8); B.changeEntry(1,3,7);
		B.changeEntry(2,1,6); B.changeEntry(2,2,5); B.changeEntry(2,3,4);
		B.changeEntry(3,1,3); B.changeEntry(3,2,2); B.changeEntry(3,3,1);
		System.out.println("Matrix B has " + B.getNNZ() + " NNZ");
		System.out.println("Matrix B: ");
		System.out.println(B);
		System.out.println("");

		System.out.println("A + B: ");
		Matrix ha = A.add(B);
		System.out.println(ha);
		System.out.println("");

		System.out.println("A - B: ");
		Matrix hi = A.sub(B);
		System.out.println(hi);
		System.out.println("");

		System.out.println("A mult B: ");
		Matrix hey = A.mult(B);
		System.out.println(hey);
		System.out.println("");

		C.changeEntry(2,5,10);
		C.changeEntry(1,2,99);
		C.changeEntry(8,9,89);
		C.changeEntry(5,4,341);
		C.changeEntry(9,9,35);
		C.changeEntry(10,10,0);
		System.out.println("Matrix C has " + C.getNNZ() + " NNZ");
		System.out.println("Matrix C: ");
		System.out.println(C);
		System.out.println("After clearing:");
		C.makeZero();
		System.out.println(C);
		System.out.println("");

		for(int i=1; i<=1000; i++){
			for(int j=1; j<=1000; j++){
				D.changeEntry(i,j,count);
				count++;
			}
		}
		System.out.println("Matrix D:");
		System.out.println(D);
		System.out.println("");
		System.out.println("D x 10:");
		System.out.println(D.scalarMult(10));
	}
}
*/
