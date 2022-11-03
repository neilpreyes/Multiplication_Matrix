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
				tempMatrix[i][j] = value.nextInt(10); //values 0-9
			}
		}
		
		return tempMatrix;
	}
	
	public static int[][] emptyMatrixValues(int rows){
		int[][] tempMatrix = new int[rows][rows];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < rows; j++) {
				tempMatrix[i][j] = 0; //empty matrix with values of 0
			}
		}
		
		return tempMatrix;
	}
	
	public static int[][] classicMult(int[][] matrixA, int[][]matrixB, int numEquations){
		int[][] result = new int[numEquations][numEquations];
		result = emptyMatrixValues(numEquations);
		
		for(int i = 0; i < numEquations; i++) {
			for(int j = 0; j < numEquations; j++) {
				for(int k = 0; k < numEquations; k++) {
					result[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		final int RUN_TIMES = 20;
		int numEquations;
		int[][] matrixA, matrixB, result;
		long start;
		long timeCM = 0;
		long timeDC = 0;
		long timeS = 0;
		
		for(int i = 1; i <= 7; i++) { //based on previous courses my computer can handle up to 2^7 matrice rows/variables
			numEquations = (int)Math.pow(2, i); //number of equations is based on powers of 2
			
			matrixA = randomMatrixValues(numEquations);
			matrixB = randomMatrixValues(numEquations);
			
			start = System.nanoTime();
			result = classicMult(matrixA, matrixB, numEquations);
			timeCM = System.nanoTime();
			timeCM = timeCM - start;
			/** Check if output is proper
			 * for(int num = 0; num < numEquations; num++){
			 * 	for(int num2 = 0; num2 < numEquations; num2++){
			 * 		System.out.print(result[num][num2] + " ");
			 * 	}
			 * 	System.out.println();
			 * }
			 */
			
			
			//result = dcMult(matrixA, matrixB, numEquations); //assuming using same parameters
			//result = strassenMult(matrixA, matrixB, numEquations); //assuming using same parameters
			
			System.out.println("Time Evaluations(for " + numEquations + "x" + numEquations + "): ");
			System.out.println("Classic Multiplication Matrix Method: " + timeCM);
		}
		
		
	}
}
