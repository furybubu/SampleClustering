package util;

import java.util.Arrays;

public class SquareMatrix {
	
	private int size;
	
	private double[][] matrix;
	
	private String[] labels;
	
	
	public SquareMatrix(int size) {
		this.size = size;
		this.matrix = new double[size][size];
		this.labels = new String[size];
	}
	
	public SquareMatrix(double[][] matrix) {
		this.matrix = matrix;
		this.size = matrix.length;
		this.labels = new String[size];
	}

	
	public void set(int row, int col, double value) {
		matrix[row][col] = value;
	}
	
	public double get(int row, int col) {
		return matrix[row][col];
	}
	
	public void setLabel(String lab, int i) {
		this.labels[i] = lab;
	}
	
	public String getLabel( int i) {
		return this.labels[i];
	}
	
	
	public String[] getLabels() {
		return labels;
	}


	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	

	public int size() {
		return size;
	}
	
	public double getSumRow(int row) {
		double sum = 0;
		for (int j=0;j<size;j++) {
				sum+=matrix[row][j];
		}
		return sum;
	}
	
	public double getSumCol(int col) {
		double sum = 0;
		for (int i=0;i<size;i++) {
				sum+=matrix[i][col];
		}
		return sum;
	}
	
	public void removeEntry (int index) {
		
		double [][] tempMatrix = new double[size-1][size-1];

        //row and column counter for the new matrix
        int tmpX=-1;
        int tmpY=-1;


        //re-populate new matrix by searching through the original copy of matrix, while skipping useless row and column
        // works only for 1 row and 1 column in a 2d array but by changing the conditional statement we can make it work for n number of rows or columns in a 2d array.
        for (int i=0; i<size; i++) {
			tmpX++;
			if(i==index){
			    tmpX--;
			}
			tmpY=-1;
			for (int j=0; j<size; j++) {
				tmpY++;
				if(j==index){
					tmpY--;
				}
			    if(i != index && j != index) {
			       	tempMatrix[tmpX][tmpY] = matrix[i][j];
			    }
			}
        }
        this.size = size - 1;
		this.matrix=tempMatrix;
	}
	
	public void insertEntry (double[] entry) {
		double [][] tmpMatrix = new double[size+1][size+1];
		
		tmpMatrix[tmpMatrix.length-1]=entry;
		
		for (int i=0; i<tmpMatrix.length-1; i++) {
			for (int j=0; j<tmpMatrix.length; j++) {
				if (j<tmpMatrix.length-1) {
					tmpMatrix[i][j] = matrix[i][j];
				}else {
						tmpMatrix[i][j] = entry[i];
				}
			}
		}
		this.size = size + 1;
		this.matrix = tmpMatrix;
	}
	
	
	public void setEntry (double[] entry, int index) {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (i==index) {
					matrix [i][j] = entry[j];
				}else if (j==index) {
					matrix [i][j] = entry[i];
				}
			}
		}
	}
	
	public int[] getMinRowCol() {
		int[] minRowCol = {0,0};
		double minValue = this.get(0,0);
		
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				if (this.get(i,j)<minValue) {
					minValue = this.get(i,j);
					minRowCol[0] = i;
					minRowCol[1] = j;
				}
			}
		}
		return minRowCol;
	}
	
	@Override
	public String toString() {
	  
	    String str = "";
	    for (int i = 0; i < size; i++)
	        for (int j = 0; j < size; j++)
	        {

	        	if (j<size-1 ) {
	        		str += matrix[i][j]+"\t";
	        	}else {
	        		str += matrix[i][j]+"\n";
	        	}
	         
	        }

	     return str;  

	 }
	
	
}
