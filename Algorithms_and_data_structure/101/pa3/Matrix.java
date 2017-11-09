/**
 *Ruikang Tao
 *rtao6
 *Programming Assignment 3
 */

class Matrix{
	// inner class of Matrix
	private class Entry{
		// inner class fileds
		int column;
		double data;
		// inner class constructor
		Entry(int column, double data){
			this.column = column;
			this.data = data;
		}

		public boolean equals(Object ha){
			Entry xin;
			boolean isEqual = false;
			if(ha instanceof Entry){
				xin = (Entry)ha;
				isEqual = (this.column == xin.column && this.data == xin.data);
			}
			return isEqual;
		}

		public String toString(){
			return "(" + column + "," + data + ")";
		}
	}

	// private fields
	private List[] row;
	private int NNZ;
	private int size;

	// Constructor
	// Matrix(int n) 
	// Makes a new n x n zero Matrix. pre: n>=1
	Matrix(int size){
		if(size < 1){
			throw new RuntimeException ("Error: Matrix size must be at least 1.");
		}
		this.size = size;
		this.row = new List[size+1];
		this.NNZ = 0;
		for(int i = 1; i < (size+1); i++){
			this.row[i] = new List();
		}
	}

	// Access functions
	// int getSize() 
	// Returns n, the number of rows and columns of this Matrix
	int getSize(){
		return size;
	}

	// int getNNZ() 
	// Returns the number of non-zero entries in this Matrix
	int getNNZ(){
		return NNZ;
	}

	// public boolean equals(Object x) 
	// overrides Object's equals() method
	public boolean equals(Object x){
		if(size < 1){
			throw new RuntimeException ("Error: Matrix size must be at least 1.");
		}
		Matrix test = (Matrix)x;
		if(test.getSize() != size || test.getNNZ() != NNZ){
			return false;
		} else{
			for(int i=1; i<this.size + 1; i++){
				if(!test.row[i].equals(row[i])){
					return false;
				}
			}
			return true;
		}
	}

	// Manipulation procedures
	// makeZero()
	// sets this Matrix to the zero state 
	void makeZero(){
		for(int i=1; i<(size+1); i++){
			row[i].clear();
		}
		NNZ = 0;
	}

	// Matrix copy()
	// returns a new Matrix having the same entries as this Matrix 
	Matrix copy(){
		Matrix newM = new Matrix(size);
		for(int i=1; i<newM.getSize()+1; i++){
			row[i].moveFront();
			while(row[i].index() != -1){
				Entry elem = (Entry)row[i].get();
				newM.changeEntry(i, elem.column, elem.data);
				row[i].moveNext();
			}
		}
		return newM;
	}

	// changeEntry()
	// changes ith row, jth column of this Matrix to x
	// pre: 1<=i<=getSize(), 1<=j<=getSize()
	void changeEntry(int i, int j, double x){
		if ((i>size || i<=0) || (j>size || j<=0)){
			throw new RuntimeException ("Error: changeEntry() inindices out of bound.");
		}

		Entry newE = new Entry(j, x);
		List L = row[i];
		L.moveFront();
		while(L.index() != -1){
			Entry E = (Entry)L.get();
			if(E.column == j){
				if(x == 0){
					L.delete();
					NNZ--;
					break;
				} else{
					E.data = x;
					break;
				}
			} else if(E.column > j){
				break;
			}
			L.moveNext();
		}
		if(x != 0){
			if(L.index() < 0){
				L.append(newE);
				NNZ++;
			} else{
				Entry E = (Entry)L.get();
				if(j != E.column){
					L.insertBefore(newE);
					NNZ++;
				}
			}
		}
	}

	// scalarMult()
	// returns a new Matrix that is the scalar product of this Matrix with x
	Matrix scalarMult(double x){
		Matrix scalarProd = new Matrix(size);
		for(int i=1; i<scalarProd.getSize()+1; i++){
			row[i].moveFront();
			while(row[i].index() != -1){
				Entry elem = (Entry)row[i].get();
				scalarProd.changeEntry(i, elem.column, elem.data*x);
				row[i].moveNext();
			}
		}
		return scalarProd;
	}

	// add()
	// returns a new Matrix that is the sum of this Matrix with M
 	// pre: getSize()==M.getSize()
	Matrix add(Matrix M){
		if(this.size != M.getSize()){
			throw new RuntimeException ("Error: add() called on unequal matrix size.");
		}
		List A, B;
		Matrix ha = new Matrix(this.size);
		if(this == M){
			return this.scalarMult(2);
		}
		if(this != M){
			for(int i=1; i<size+1; i++){
				A = this.row[i];
				B = M.row[i];
				ha.row[i] = rowOperation(A, B);
			}
		}
		ha.NNZ = M.NNZ;
		return ha;
	}

	// sub()
	// returns a new Matrix that is the sum of this Matrix with M
 	// pre: getSize()==M.getSize()
	Matrix sub(Matrix M){
		if(this.size != M.getSize()){
			throw new RuntimeException ("Error: add() called on unequal matrix size.");
		}
		List A, B;
		Matrix ha = new Matrix(this.size);
		if(this == M){
			return ha;
		}
		if(this != M){
			ha = this.add(M.scalarMult(-1));
		}
		return ha;
	}

	// transpose()
 	// returns a new Matrix that is the transpose of this Matrix
	Matrix transpose(){
		Matrix T = new Matrix(size);
		for(int i=1; i<size+1; i++){
			row[i].moveFront();
			while(row[i].index() != -1){
				Entry elem = (Entry)row[i].get();
				T.changeEntry(elem.column, i, elem.data);
				row[i].moveNext();
			}
		}
		return T;
	}

	// mult()
	// returns a new Matrix that is the product of this Matrix with M
	 // pre: getSize()==M.getSize()
	Matrix mult(Matrix M){
		if(this.size != M.getSize()){
			throw new RuntimeException ("Error: mult() called on unequal matrix size.");
		}
		Matrix T = M.transpose();
		Matrix mult = new Matrix(size);
		for(int i=1; i < size+1; i++){
			if(row[i].length() > 0){
				for(int j = 1; j < size + 1; j++){
					if(T.row[j].length() > 0){
						mult.changeEntry(i,j,dot(this.row[i], T.row[j]));
					}
				}
			}
		}
		return mult;
	}

	// Other functions	
	// dot()
	// helper function that calculates the dot product of two lists
	private static double dot(List P, List Q){
		double dotProd = 0;
		for(P.moveFront(); P.index() != -1; P.moveNext()){
			Entry temp1 = (Entry)P.get();
			for(Q.moveFront(); Q.index() != -1; Q.moveNext()){
				Entry temp2 = (Entry)Q.get();
				if(temp1.column == temp2.column){
					dotProd += temp1.data * temp2.data;
					break;
				}
				
			}
		}
		return dotProd;
	}

	// rowOperation()
	// helper function that performs the addition or subtraction of two lists
	private List rowOperation(List A, List B){
		List C = new List();
		A.moveFront();
		B.moveFront();
		Entry aa;
		Entry bb;

		while(A.index() != -1 && B.index() != -1){
			aa = (Entry)A.get();
			bb = (Entry)B.get();
			if(aa.column == bb.column){
				if(aa.data + bb.data != 0){
					Entry cc = new Entry(aa.column, aa.data+bb.data);
					C.append(cc);
					A.moveNext();
					B.moveNext();
				} else{
					A.moveNext();
					B.moveNext();
				}
			}
			if(aa.column < bb.column){
				Entry cc = new Entry(aa.column, aa.data);
				C.append(cc);
				A.moveNext();
			}
			if(aa.column > bb.column){
				Entry cc = new Entry(bb.column, bb.data);
				C.append(cc);
				B.moveNext();
			}
		}
		while(A.index() != -1 || B.index() != -1){
			if(A.index() != -1){
				aa = (Entry)A.get();
				Entry cc = new Entry(aa.column, aa.data);
				C.append(cc);
				A.moveNext();
			}
			if(B.index() != -1){
				bb = (Entry)B.get();
				Entry cc = new Entry(bb.column, bb.data);
				C.append(cc);
				B.moveNext();
			}
		}
		return C;
	}

	// toString()
	// overrides Object's toString() method
	public String toString(){
		String str = "";
		for(int i=1; i<size+1; i++){
			if(row[i].length() > 0){
				str = str + i + ": " + row[i].toString() + "\n";
			}
		}
		return str;
	}
}