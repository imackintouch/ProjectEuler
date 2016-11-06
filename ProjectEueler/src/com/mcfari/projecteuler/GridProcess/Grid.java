package com.mcfari.projecteuler.GridProcess;
import java.io.*;
import java.util.*;

public class Grid {
	private final int MAX_GROUP_SIZE=4;
	private final int DEFAULT_MAX_GRID_WIDTH=20;
	private final int DEFAULT_MAX_GRID_HEIGHT=20;
	private int gridWidth;
	private int gridHeight;
	private int[][] grid;
	private int max4GroupProduct;
	
	public Grid() {
		grid = new int[DEFAULT_MAX_GRID_HEIGHT][DEFAULT_MAX_GRID_WIDTH];
	}
	
	public Grid(int height, int width) {
		this.grid = new int[height][width];
		setGridWidth(width);
		setGridHeight(height);
	}
	
	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	public void populateGridFromFile(File inputFile) {
				
		//open a file using the Scanner object:
		try (Scanner gridInputFile = new Scanner(inputFile))
		{
			
			int i=0; //height 
			int j=0; //width
			
				while (gridInputFile.hasNextInt()) //while there are more integers on a line to scan
				{
					
					this.grid[i][j] = gridInputFile.nextInt();
					//System.out.printf("%02d ",grid[i][j++]);
					
					//Move to next row in the grid once we reach the maximum gridwidth of grid object.
					j++;
					if (j == this.gridWidth) {
						j=0;
						i++;
					}
				}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void displayGrid() {
		
		for (int i=0; (i<this.gridHeight); i++) {
			for (int j=0; j<this.gridWidth; j++) {
				//System.out.printf("grid[%d,%d]%02d ",i,j,this.grid[i][j]);
				System.out.printf("%02d ",this.grid[i][j]);
				//Add a line break whenever we hit the widest edge of the grid.
				if (j == gridWidth-1) System.out.println();; 
			}
		}
	}
		
	/*
	 * Get all possible groups of groupSize numbers that pivot around a co-ordinate point (x,y)
	 * 
	 * @param x:		x co-ordinate i.e. the row value
	 * @param y:		y co-ordinate i.e. the column value
	 * @param groupSize: The number of values in a given group
	 */
	public void getNumGroups(int x, int y, int groupSize) {
		
		//HashSet NorthGroupNumSet;
		//NorthGroupNumSet = getNorthGroup(x, y, groupSize);
		//System.out.println(NorthGroupNumSet);
		
		System.out.println("Pivot Co-ordinate (x,y): (" + x + ", " + y + ")");
		System.out.println("North:" + this.getNorthGroup(x, y, groupSize));
		System.out.println("South:" + this.getSouthGroup(x, y, groupSize));
		System.out.println("East:" + this.getEastGroup(x, y, groupSize));
		System.out.println("West:" + this.getWestGroup(x, y, groupSize));
		System.out.println("NorthWest" + this.getNorthWestGroup(x, y, groupSize));
		System.out.println("NorthEast:" + this.getNorthEastGroup(x, y, groupSize));
		System.out.println("SouthWest:" + this.getSouthWestGroup(x, y, groupSize));
		System.out.println("SouthEast:" + this.getSouthEastGroup(x, y, groupSize));
		
		//return new HashSet();
	}
	
	
	public LinkedList<Integer> getNorthGroup(int x, int y, int groupSize) {
		
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ((x-(groupSize-1) >= 0) && (y<gridWidth)) { //validate direction possibility
			//Get north/up group
			for (int i=x,j=y; i>=(x-(groupSize-1)); i--) {
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j]; 
			}
			
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
		
	}
	
	public LinkedList<Integer> getSouthGroup(int x, int y, int groupSize) {
		
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ((x+(groupSize-1) < gridHeight) && (y<gridWidth)) { //validate direction possibility
			//Get north/up group
			for (int i=x,j=y; i<x+groupSize; i++) {
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j]; 
			}
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
	}
	
	public LinkedList<Integer> getEastGroup(int x, int y, int groupSize) {
		
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ((x < gridHeight) && (y+(groupSize-1) < gridWidth)) { //validate direction possibility
			//Get west group
			for (int i=x,j=y; j<=(y+(groupSize-1)); j++) {
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j]; 
				
			}
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
	
		
	}
	
	public LinkedList<Integer> getWestGroup(int x, int y, int groupSize) {
		
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ((x  < gridHeight) && (y-(groupSize-1) >= 0)) { //validate direction possibility
			//Get west group
			for (int i=x,j=y; j>=(y-(groupSize-1)); j--) {
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j]; 
			}
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
				
	}
	
	public LinkedList<Integer> getNorthWestGroup(int x, int y, int groupSize) {
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ( ((x - (groupSize-1)) >= 0) && ((y-(groupSize-1)) >= 0) ) { //validate direction possibility
			//Get NorthWest group
			//System.out.println("Foobar Get NorthWest");
			for (int i=x,j=y; (i>=(x-(groupSize-1)))&& (j>=(y-(groupSize-1))); i--,j--) {
				//System.out.println("Foobar");
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j];
			}
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
		
		
	}
	
	public LinkedList<Integer> getNorthEastGroup(int x, int y, int groupSize) {
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ( ((x - (groupSize-1)) >= 0) && ((y+(groupSize-1)) < gridWidth) ) { //validate direction possibility
			
			for (int i=x,j=y; (i>=(x-(groupSize-1)))&& (j<=(y+(groupSize-1))); i--,j++) {
				//System.out.println("Foobar");
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j];
			}
		}
		
				
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
		
	}
	
	public LinkedList<Integer> getSouthWestGroup(int x, int y, int groupSize) {
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		else if ( ((x + (groupSize-1)) < gridHeight) && 
					((y-(groupSize-1)) >= 0) ) 
		{ //validate direction possibility
				//System.out.println("Foobar get southwest");;
				for (int i=x,j=y; (i<=(x+(groupSize-1)))&& (j>=(y-(groupSize-1))); i++,j--) {
					numList.add( (Integer) grid[i][j] );
					product = product * grid[i][j];
				}
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
	}
	
	public LinkedList<Integer> getSouthEastGroup(int x, int y, int groupSize) {
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int product=-1;
		
		if (x<0 || y<0) {
			//do nothing if either coordinate is negative
		}
		
		else if ((x + (groupSize-1) < gridHeight) && (y+(groupSize-1) < gridWidth)) {
			for (int i=x,j=y; (i<=(x+(groupSize-1)))&& (j<=(y+(groupSize-1))); i++,j++) {
				numList.add( (Integer) grid[i][j] );
				product = product * grid[i][j];
			}
			
		}
		
		numList.add((Integer)(product));	/* Product of all the numbers in the list gets added to end. */
		return numList;
		
	}
}
