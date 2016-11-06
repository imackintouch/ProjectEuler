package com.mcfari.projecteuler;

public class Indempotent {

	public static int calcIndempotent(Matrix A,int n) {
	
		//generate all possible 3*3 matrices where each element can only be -1,0 or 1. Square those matrices
		//and then see if the square matches the values of the original matrix.
		//See original problem description @ https://projecteuler.net/problem=572	
		
		Matrix B = new Matrix(A.getRowSize(), A.getRowSize(), "B");
		int min=-n;
		int max=n;
		int countIndempotent=0;
		int iteration=0;
		for (int a=min; a<=max; a++ ) {
			A.setElement(0, 0, a);	B.setElement(0, 0, a);
			for (int b=min; b<=max; b++ ) {
				A.setElement(0, 1, b);	B.setElement(0, 1, b);
				for (int c=min; c<=max; c++ ) {
					A.setElement(0, 2, c);	B.setElement(0, 2, c);
					for (int d=min; d<=max; d++ ) {
						A.setElement(1, 0, d);	B.setElement(1,0, d);
						for (int e=min; e<=max; e++ ) {
							A.setElement(1, 1, e);	B.setElement(1, 1, e);
							for (int f=min; f<=max; f++ ) {
								A.setElement(1, 2, f);	B.setElement(1, 2, f);
								for (int g=min; g<=max; g++ ) {
									A.setElement(2, 0, g);	B.setElement(2, 0, g);
									for (int h=min; h<=max; h++ ) {
										A.setElement(2, 1, h);	B.setElement(2, 1, h);
										for (int i=min; i<=max; i++ ) {
											A.setElement(2, 2, i);	B.setElement(2, 2, i);
											iteration++;
											//System.out.printf("%d %d %d %d %d %d %d %d %d \tIteration:%6d\n",a,b,c,d,e,f,g,h,i,iteration);
											if (Matrix.equals(A,Matrix.multiply(A, B))) {
												countIndempotent++;
												System.out.printf("\nFound an indempotent matrix at iteration:%d \n",iteration);
												A.display();
												System.out.println();
											}
											
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		return countIndempotent;
	}
	
	
	public static void main(String[] args) {
		
		
		Matrix A=new Matrix(3,3,"A");
		A.display();
				
		int n=200;
		
		//override default n if there is an argument
		if (args.length > 0) 
			n=Integer.parseInt(args[0]);
		
		System.out.printf("Number of indempotent 3*3 matrices found when each element between %d and %d is:%d.\n",
				-n, n, calcIndempotent(A,n));
		
	}

}
