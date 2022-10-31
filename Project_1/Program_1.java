package Project_1;
/**Neil Patrick Reyes
 * CS 3310 Section 03
 * Professor Young
 * 10/30/2022
 */

import java.util.*;
import java.lang.Math;

public class Program_1{
	
	public static int[][] randomMatrixValues(int rows){
		Random value = new Random();
		int[][] tempMatrix = new int[rows][rows];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < rows; j++) {
				tempMatrix[i][j] = value.nextInt(100) + 1; //values 1-100
			}
		}
		
		return tempMatrix;
	}
	
	public static void main(String[] args) {
		
		final int RUN_TIMES = 20;
		int numEquations;
		int[][] matrixA, matrixB;
		
		for(int i = 1; i <= 7; i++) { //based on previous courses my computer can handle up to 2^7 matrice rows/variables
			numEquations = (int)Math.pow(2, i); //number of equations is based on powers of 2
			
			matrixA = randomMatrixValues(numEquations);
			matrixB = randomMatrixValues(numEquations);
			
			
		}
		
		
	}
}