package com.mcfari.projecteuler.IdempotentMatrices;

import java.util.Scanner;

public class Matrix {

	private String matrixName;
	private int rowSize;
	private int colSize;
	private int[][] matrix;
	private int status;
	
	public String getMatrixName() {
		return matrixName;
	}

	public void setMatrixName(String matrixName) {
		this.matrixName = matrixName;
	}
	
	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rows) {
		this.rowSize = rows;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int cols) {
		this.colSize = cols;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	//Default Constructor in case rows and cols are not passed in. We just need a 1 by 1 grid by default.
	public Matrix() {
		setRowSize(1);
		setColSize(1);
		this.matrix = new int[this.rowSize][this.colSize];
		this.matrixName = "DefaultMatrix";
		this.initializeElements(0);
		setStatus(0);
		
	}
	//Constructor for Matrix initializes a 2 dimensional array to store actual integer elements
	//The dimensions are required.
	public Matrix(int numRows, int numCols, String name) {
		this.matrix = new int[numRows][numCols];
		setRowSize(numRows);
		setColSize(numCols);
		setMatrixName(name);
		this.initializeElements(0);
		setStatus(0);
	}
	
	//get value at position (row, col) understanding using 0 as a base
	public int getElement(int row, int col) {
		return this.matrix[row][col];
	}
	
	//set matrix[row,col] to value using 0 as a base
	public void setElement(int row, int col, int value) {
		this.matrix[row][col] = value;
	}

	//Populate all values in matrix to be value
	public void initializeElements(int value) {
		for (int i=0; i<this.getRowSize(); i++) 
			for (int j=0; j<this.getColSize(); j++)
				this.setElement(i,j,value);
	}
	
	//Display contents of Matrix internal array in grid form.
	public void display() {
		System.out.println("These are the contents of Matrix:"+this.getMatrixName());
		for (int i=0; i<getRowSize(); i++) {
			for (int j=0; j<getColSize(); j++) {
				System.out.print(this.matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/* Fill matrix with elements based on its row size and col size properties */
	public void populate() {
		
		int num;
		System.out.println("RowCount:" + this.getRowSize() + " ColSize:"+this.getColSize());
		Scanner in=new Scanner(System.in);
			
		for (int i=0; i<this.getRowSize(); i++) {
			for (int j=0; j<this.getColSize(); j++) {
				System.out.printf("Please enter a value for element " + this.getMatrixName()+ "[" + i + "," + j + "]:");
				num=in.nextInt();
				this.setElement(i, j, num);
			}
		}
		
		//in.close();
	}
	
	//Compare all elements in Matrix A with elements in Matrix B and if they don't
	//match each other in all locations then return false.
	public static boolean equals(Matrix A, Matrix B) {
		for (int i=0; i<A.getRowSize(); i++) 
			for (int j=0; j<B.getColSize(); j++)
				if (A.getElement(i, j) != B.getElement(i, j) )
					return false;
		
		return true;
	}
	
	public static Matrix multiply(Matrix A, Matrix B) {
				
		//do some checking of the dimensions of Matrix A and B are compatible for matix multiplication.
		// The number of cols for A must match the number of rows for B! 
		
		int numRowsA=A.getRowSize(); //n
		int numColsA=A.getColSize(); //m
		int numRowsB=B.getRowSize(); //m
		int numColsB=B.getColSize(); //p
		
		//If Matrices are incompatible for multiplication, just set an invalid status and 
		if (numColsA != numRowsB) {
			Matrix m=new Matrix();
			m.setStatus(-1);
			return m;
		}
		
		Matrix m=new Matrix(numRowsA,numColsB,"MatrixDotProduct");
		
		//Now run the calcs for A and B using nested looping
		int i,j,k;
		for (i=0; i<numRowsA; i++) {//We need to loop through Rows 0 through numRowsA of Matrix A 
			for (j=0; j<numColsB; j++) {//We need to loop through Cols 0 through NumColsB of MAtrix B
				//Multiply A's Row i by B's Col j. But how? We loop again with new counter k!
				int matrixElementSum=0;
				for (k=0; k<numColsA; k++) {//Move along the columns of A's Row i and rows of B's col j at the same time.
					//Each time k increments we need to calculate A[i,k]*B[k,j] and add it to a running sum.
					//System.out.printf("i:%d j:%d k:%d Currently dealing with A[%d,%d] and B[%d,%d]\n",i,j,k,i,k,k,j);
					matrixElementSum = matrixElementSum + A.getElement(i, k) * B.getElement(k, j);
					
				}
				m.setElement(i, j, matrixElementSum);
			}
		}
		
		return m;
	}
}
