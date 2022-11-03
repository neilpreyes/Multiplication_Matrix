package Project_1;
/**Neil Patrick Reyes
 * CS 3310 Section 03
 * Professor Young
 * 11/02/2022
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
	
	public static int[][] dcMult(int[][] matrixA, int[][]matrixB, int numEquations){
		int[][] result = new int[numEquations][numEquations];
		result = emptyMatrixValues(numEquations);
		
		int[][] A11 = new int[numEquations/2][numEquations/2];
		int[][] A12 = new int[numEquations/2][numEquations/2];
		int[][] A21 = new int[numEquations/2][numEquations/2];
		int[][] A22 = new int[numEquations/2][numEquations/2];
		int[][] B11 = new int[numEquations/2][numEquations/2];
		int[][] B12 = new int[numEquations/2][numEquations/2];
		int[][] B21 = new int[numEquations/2][numEquations/2];
		int[][] B22 = new int[numEquations/2][numEquations/2];
		int[][] C11 = new int[numEquations/2][numEquations/2];
		int[][] C12 = new int[numEquations/2][numEquations/2];
		int[][] C21 = new int[numEquations/2][numEquations/2];
		int[][] C22 = new int[numEquations/2][numEquations/2];
		
		
		splitMatrix(matrixA, A11, 0, 0);
		splitMatrix(matrixA, A12, 0, numEquations/2);
		splitMatrix(matrixA, A21, numEquations/2, 0);
		splitMatrix(matrixA, A22, numEquations/2, numEquations/2);
		splitMatrix(matrixB, B11, 0, 0);
		splitMatrix(matrixB, B12, 0, numEquations/2);
		splitMatrix(matrixB, B21, numEquations/2, 0);
		splitMatrix(matrixB, B22, numEquations/2, numEquations/2);
		
		C11 = addMatrix(classicMult(A11, B11, numEquations/2), classicMult(A12, B21, numEquations/2), numEquations/2);
		C12 = addMatrix(classicMult(A11, B12, numEquations/2), classicMult(A12, B22, numEquations/2), numEquations/2);
		C21 = addMatrix(classicMult(A21, B11, numEquations/2), classicMult(A22, B21, numEquations/2), numEquations/2);
		C21 = addMatrix(classicMult(A21, B12, numEquations/2), classicMult(A22, B22, numEquations/2), numEquations/2);
		
		buildMatrix(C11, result, 0, 0);
		buildMatrix(C12, result, 0, numEquations/2);
		buildMatrix(C21, result, numEquations/2, 0);
		buildMatrix(C22, result, numEquations/2, numEquations/2);
		
		
		return result;
	}
	
	public static void splitMatrix(int[][] Matrix, int[][] newMatrix, int row, int column) {
		int columnNum = column;
		
		for(int i = 0; i < newMatrix.length; i++) {
			for(int j = 0; j < newMatrix.length; j++) {
				newMatrix[i][j] = Matrix[row][columnNum++];
			}
			columnNum = column; //reset column num
			row++; //follow row to i
		}
		
	}
	
	public static int[][] addMatrix(int[][] matrixOne, int[][] matrixTwo, int num){
		int[][] C = new int[num][num];
		
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				C[i][j] = matrixOne[i][j] + matrixTwo[i][j];
			}
		}
		
		return C;
	}
	
	public static void buildMatrix(int[][] Matrix, int[][] newMatrix, int row, int column) {
		int columnNum = column;
		
		for(int i = 0; i < Matrix.length; i++) {
			for(int j = 0; j < Matrix.length; j++) {
				newMatrix[row][columnNum++] = Matrix[i][j];
			}
			columnNum = column; //reset column num
			row++; //follow row to i
		}
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
			
			start = System.nanoTime();
			result = dcMult(matrixA, matrixB, numEquations);
			timeDC = System.nanoTime();
			timeDC = timeDC - start;
			
			//result = strassenMult(matrixA, matrixB, numEquations); //assuming using same parameters
			
			System.out.println("Time Evaluations(for " + numEquations + "x" + numEquations + "): ");
			System.out.println("Classic Multiplication Matrix Method: " + timeCM);
			System.out.println("Divide & Conquer Multiplication Matrix Method: " + timeDC);
		}
		
		
	}
}