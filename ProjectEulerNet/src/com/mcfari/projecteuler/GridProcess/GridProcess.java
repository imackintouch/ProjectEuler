package com.mcfari.projecteuler.GridProcess;
import java.io.*;
import java.util.*;

public class GridProcess {

	public static void main(String[] args) {
		// Please see https://projecteuler.net/problem=11
		
		int width=20;
		int height=20;
		Grid eulerGrid = new Grid(width,height);
		
		//Substitute with another pathname if need be. A future enhancement will be to allow for this 
		//pathname to be passed in via an argument or via console input.
		String gridInputFilePath="C:\\users\\imack\\Documents\\euler_project_20x20_grid.txt";
		File gridInputFile = new File(gridInputFilePath);
				
		//Now populate grid from a file.
		eulerGrid.populateGridFromFile(gridInputFile);
		
		int groupSize=4;
		LinkedList<Integer> productList = new LinkedList<Integer>();
		
		
		//For each of the list of numbers returned from the methods below, add the last element which will be
		//a product.
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				processGridElement(i, j, groupSize, productList, eulerGrid);
				System.out.println("List of Product values derived as of "+i+"," + j + "is:" + productList);
			}
		}
		
		//Run through productList to get the maximum value
		System.out.printf("The maximum %d group product value is:%d",groupSize, getMax(productList));
		
	}

	public static int getMax(LinkedList<Integer> productList) {
		int max=0;
		for (Integer i: productList) {
			
			if (i.intValue() > max) {
				max=i.intValue();
			}
		}
		return max;
	}
	
	public static void processGridElement(int x, int y, int groupSize, 
				LinkedList<Integer> productList, Grid eulerGrid) {
		
		productList.add(eulerGrid.getNorthGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getSouthGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getWestGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getEastGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getNorthWestGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getNorthEastGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getSouthWestGroup(x, y, groupSize).getLast());
		productList.add(eulerGrid.getSouthEastGroup(x, y, groupSize).getLast());
		
	}
}
