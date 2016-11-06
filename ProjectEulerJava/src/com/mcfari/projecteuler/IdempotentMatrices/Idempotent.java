package com.mcfari.projecteuler.IdempotentMatrices;

import java.text.DecimalFormat;

public class Idempotent {

	public static int calcIdempotent(Matrix A,int n) {
		//
		//See original problem description @ https://projecteuler.net/problem=572
		//For rules on Matrix multiplication, see these links:
		//https://www.mathsisfun.com/algebra/matrix-multiplying.html &
		//https://en.wikipedia.org/wiki/Matrix_multiplication
		
		//Matrix B = new Matrix(A.getRowSize(), A.getRowSize(), "B");
		if (A.getRowSize() != 3) {
			System.out.println("Only 3 by 3 matrices will be allowed in this method!");
			return -1;
		}
			
		int min=-n;
		int max=n;
		//double countIndempotent=0;
		int countIndempotent=0;
		//double iteration=0;
		for (int a=min; a<=max; a++ ) {
			//A.setElement(0, 0, a);	//B.setElement(0, 0, a);
			for (int b=min; b<=max; b++ ) {
				//A.setElement(0, 1, b);	//B.setElement(0, 1, b);
				for (int c=min; c<=max; c++ ) {
					//A.setElement(0, 2, c);	//B.setElement(0, 2, c);
					for (int d=min; d<=max; d++ ) {
						//A.setElement(1, 0, d);	//B.setElement(1,0, d);
						for (int e=min; e<=max; e++ ) {
							//A.setElement(1, 1, e);	//B.setElement(1, 1, e);
							for (int f=min; f<=max; f++ ) {
								//A.setElement(1, 2, f);	//B.setElement(1, 2, f);
								for (int g=min; g<=max; g++ ) {
									//A.setElement(2, 0, g);	//B.setElement(2, 0, g);
									for (int h=min; h<=max; h++ ) {
										//A.setElement(2, 1, h);	//B.setElement(2, 1, h);
										for (int i=min; i<=max; i++ ) {
											//A.setElement(2, 2, i);	//B.setElement(2, 2, i);
											//iteration++;
											//System.out.printf("%d %d %d %d %d %d %d %d %d \tIteration:%6d\n",a,b,c,d,e,f,g,h,i,iteration);
											//Let's solve via equations based on matrix multiplication rules instead.
											if (a*a + b*d + c*g	!= a) //solution for A dot A - [0,0]
												continue;
											if (a*b + b*e + c*h != b) //solution for A dot A - [0,1]
												continue;
											if (a*c + b*f + c*i != c) //solution for A dot A - [0,2]
												continue;
											
											if (d*a + e*d + f*g	!= d) //solution for A dot A - [1,0]
												continue;
											if (d*b + e*e + f*h != e) //solution for A dot A - [1,1]
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
											System.out.println("\nFound an indempotent matrix. (See below):");
											
											A.setElement(0, 0, a);	
											A.setElement(0, 1, b);	
											A.setElement(0, 2, c);	
											A.setElement(1, 0, d);	
											A.setElement(1, 1, e);	
											A.setElement(1, 2, f);	
											A.setElement(2, 0, g);	
											A.setElement(2, 1, h);	
											A.setElement(2, 2, i);	
											
											A.display();
											System.out.println();
											
											/*if (Matrix.equals(A,Matrix.multiply(A, B))) {
												countIndempotent++;
												System.out.printf("\nFound an idempotent matrix at iteration:%f \n",iteration);
												A.display();
												System.out.println();
											}*/
											
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
