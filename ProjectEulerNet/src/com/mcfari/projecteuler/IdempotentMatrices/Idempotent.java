package com.mcfari.projecteuler.IdempotentMatrices;

import java.text.DecimalFormat;

public class Idempotent {

	public static int calcIdempotent(Matrix A,int n) {
		//
		//See original problem description @ https://projecteuler.net/problem=572
		//For rules on Matrix multiplication, see these links:
		//https://www.mathsisfun.com/algebra/matrix-multiplying.html &
		//https://en.wikipedia.org/wiki/Matrix_multiplication
		
		/*
		 * Note: For sizable n's e.g. 100 or 200, this will run for a long time. 
		 * Some number theory knowledge might be required to make this faster.
		 * In addition, the Matrix class is not as needed in this latest iteration since
		 * matrix multiplication can be done independently of a matrix.
		 */
		if (A.getRowSize() != 3) {
			System.out.println("Only 3 by 3 matrices will be allowed in this method!");
			return -1;
		}
			
		int min=-n;
		int max=n;
		int countIndempotent=0;
		//double iteration=0;
		//double countIndempotent=0;
		
		//Let's solve via equations based on matrix multiplication rules instead.
	
		for (int a=min; a<=max; a++ ) {
			for (int b=min; b<=max; b++ ) {
				for (int c=min; c<=max; c++ ) {
					for (int d=min; d<=max; d++ ) {
						for (int e=min; e<=max; e++ ) {
							for (int f=min; f<=max; f++ ) {
								for (int g=min; g<=max; g++ ) {
									if (a*a + b*d + c*g	!= a) //solution for A dot A - [0,0]
										continue;
									if (d*a + e*d + f*g	!= d) //solution for A dot A - [1,0]
										continue;
									for (int h=min; h<=max; h++ ) {
										if (a*b + b*e + c*h != b) //solution for A dot A - [0,1]
											continue;
										if (d*b + e*e + f*h != e) //solution for A dot A - [1,1]
											continue;
										for (int i=min; i<=max; i++ ) {
											
											//iteration++;
											//System.out.printf("%d %d %d %d %d %d %d %d %d \tIteration:%6d\n",a,b,c,d,e,f,g,h,i,iteration);
																						
											if (a*c + b*f + c*i != c) //solution for A dot A - [0,2]
												continue;		
											if (d*c + e*f + f*i != f) //solution for A dot A - [1,2]
												continue;
											if (g*a + h*d + i*g	!= g) //solution for A dot A - [2,0]
												continue;
											if (g*b + h*e + i*h != h) //solution for A dot A - [2,1]
												continue;
											if (g*c + h*f + i*i != i)//solution for A dot A - [2,2]
												continue;
											
											countIndempotent++;
											//System.out.printf("\nFound an indempotent matrix at iteration:%f \n",Math.floor(iteration));
											System.out.println("\nFound an idempotent matrix. (See below):");
											
											A.setElement(0, 0, a);	A.setElement(0, 1, b);	A.setElement(0, 2, c);	
											A.setElement(1, 0, d);	A.setElement(1, 1, e);	A.setElement(1, 2, f);	
											A.setElement(2, 0, g);	A.setElement(2, 1, h);	A.setElement(2, 2, i);	
											
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
		
		return countIndempotent;
	}
	
	
	public static void main(String[] args) {
		
		
		Matrix A=new Matrix(3,3,"A");
		A.display();
				
		int n=200;
		
		//override default n if there is an argument
		if (args.length > 0) 
			n=Integer.parseInt(args[0]);
		
		DecimalFormat decimalFormat = new DecimalFormat("#.#");
		System.out.printf("Number of idempotent 3*3 matrices found when each element between %d and %d inclusive is:%s.\n",
				-n, n, decimalFormat.format(calcIdempotent(A,n)));
		
	}

}
