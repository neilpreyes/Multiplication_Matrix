package Project_1;
/**Neil Patrick Reyes
 * CS 3310 Section 03
 * Professor Young
 * 11/04/2022
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
	
	public static int[][] classicMult(int[][] matrixA, int[][] matrixB, int numEquations){
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
	
	public static int[][] dcMult(int[][] matrixA, int[][] matrixB, int numEquations){
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
	
	public static int[][] subMatrix(int[][]matrixOne, int[][]matrixTwo, int num){
int[][] C = new int[num][num];
		
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				C[i][j] = matrixOne[i][j] - matrixTwo[i][j];
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
	
	public static int[][] strassenMult(int[][] matrixA, int[][]matrixB, int numEquations){
		int[][] result = new int[numEquations][numEquations];
		
		result = strassenRecursion(matrixA, matrixB, result, numEquations);
		
		return result;
	}
	
	public static int[][] strassenRecursion(int[][] matrixA, int[][] matrixB, int[][] matrixC, int numEquations) {
		
		if(numEquations == 2) {
			matrixC[0][0] = (matrixA[0][0] * matrixB[0][0]) + (matrixA[0][1] * matrixB[1][0]);
			matrixC[0][1] = (matrixA[0][0] * matrixB[0][1]) + (matrixA[0][1] * matrixB[1][1]);
			matrixC[1][0] = (matrixA[1][0] * matrixB[0][0]) + (matrixA[1][1] * matrixB[1][0]);
			matrixC[1][1] = (matrixA[1][0] * matrixB[0][1]) + (matrixA[1][1] * matrixB[1][1]);
			return matrixC;
		}else {
			int[][] A11 = new int[numEquations/2][numEquations/2];
			int[][] A12 = new int[numEquations/2][numEquations/2];
			int[][] A21 = new int[numEquations/2][numEquations/2];
			int[][] A22 = new int[numEquations/2][numEquations/2];
			int[][] B11 = new int[numEquations/2][numEquations/2];
			int[][] B12 = new int[numEquations/2][numEquations/2];
			int[][] B21 = new int[numEquations/2][numEquations/2];
			int[][] B22 = new int[numEquations/2][numEquations/2];
			
			int[][] P = new int[numEquations/2][numEquations/2];
			int[][] Q = new int[numEquations/2][numEquations/2];
			int[][] R = new int[numEquations/2][numEquations/2];
			int[][] S = new int[numEquations/2][numEquations/2];
			int[][] T = new int[numEquations/2][numEquations/2];
			int[][] U = new int[numEquations/2][numEquations/2];
			int[][] V = new int[numEquations/2][numEquations/2];
			
			splitMatrix(matrixA, A11, 0, 0);
			splitMatrix(matrixA, A12, 0, numEquations/2);
			splitMatrix(matrixA, A21, numEquations/2, 0);
			splitMatrix(matrixA, A22, numEquations/2, numEquations/2);
			splitMatrix(matrixB, B11, 0, 0);
			splitMatrix(matrixB, B12, 0, numEquations/2);
			splitMatrix(matrixB, B21, numEquations/2, 0);
			splitMatrix(matrixB, B22, numEquations/2, numEquations/2);
			
			strassenRecursion(addMatrix(A11, A22, A11.length), addMatrix(B11, B22, B11.length), P, P.length);
			strassenRecursion(addMatrix(A21, A22, A21.length), B11, Q, Q.length);
			strassenRecursion(A11, subMatrix(B12, B22, B12.length), R, R.length);
			strassenRecursion(A22, subMatrix(B21, B11, B21.length), S, S.length);
			strassenRecursion(addMatrix(A11, A12, A11.length), B22, T, T.length);
			strassenRecursion(subMatrix(A21, A11, A21.length), addMatrix(B11, B12, B11.length), U, U.length);
			strassenRecursion(subMatrix(A12, A22, A12.length), addMatrix(B21, B22, B21.length), V, V.length);
			
			int[][] C11 = addMatrix(subMatrix(addMatrix(P, S, P.length), T, T.length), V, V.length);
			int[][] C12 = addMatrix(R, T, numEquations/2);
			int[][] C21 = addMatrix(Q, S, numEquations/2);
			int[][] C22 = addMatrix(subMatrix(addMatrix(P, R, P.length), Q, Q.length), U, U.length);
			
			buildMatrix(C11, matrixC, 0, 0);
			buildMatrix(C12, matrixC, 0, numEquations/2);
			buildMatrix(C21, matrixC, numEquations/2, 0);
			buildMatrix(C22, matrixC, numEquations/2, numEquations/2);
			
			return matrixC;
		}
	}
	
	public static void main(String[] args) {
		
		final int RUN_TIMES = 20;
		final int randomMatrices = 1000;
		int numEquations;
		int[][] matrixA, matrixB, result;
		long start, end;
		long timeCM = 0;
		long timeDC = 0;
		long timeS = 0;
		
		for(int i = 1; i <= 7; i++) { //based on previous courses my computer can handle up to 2^7 matrice rows/variables
			numEquations = (int)Math.pow(2, i); //number of equations is based on powers of 2
			
			for(int k = 1; k <= randomMatrices; k++) {
				
				matrixA = randomMatrixValues(numEquations);
				/** Check if output is proper
				 * System.out.println("Matrix A: ");
				 * for(int num = 0; num < numEquations; num++){
				 * 	for(int num2 = 0; num2 < numEquations; num2++){
				 * 		System.out.print(matrixA[num][num2] + " ");
				 * 	}
				 * 	System.out.println();
				 * }
				 */
				matrixB = randomMatrixValues(numEquations);
				/** Check if output is proper
				 * System.out.println("Matrix B: ");
				 * for(int num = 0; num < numEquations; num++){
				 * 	for(int num2 = 0; num2 < numEquations; num2++){
				 * 		System.out.print(matrixB[num][num2] + " ");
				 * 	}
				 * 	System.out.println();
				 * }
				 */
				
				//iteration
				for(int j = 1; j <= RUN_TIMES; j++) {
					
					start = System.nanoTime();
					result = classicMult(matrixA, matrixB, numEquations);
					end = System.nanoTime();
					timeCM += end - start;
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
					end = System.nanoTime();
					timeDC += end - start;
					/** Check if output is proper
					 * for(int num = 0; num < numEquations; num++){
					 * 	for(int num2 = 0; num2 < numEquations; num2++){
					 * 		System.out.print(result[num][num2] + " ");
					 * 	}
					 * 	System.out.println();
					 * }
					 */
					
					start = System.nanoTime();
					result = strassenMult(matrixA, matrixB, numEquations);
					end = System.nanoTime();
					timeS += end - start;
					/** Check if output is proper
					 * for(int num = 0; num < numEquations; num++){
					 * 	for(int num2 = 0; num2 < numEquations; num2++){
					 * 		System.out.print(result[num][num2] + " ");
					 * 	}
					 * 	System.out.println();
					 * }
					 */
					
				}
				
			}
			
			
			
			timeCM = timeCM/(RUN_TIMES * randomMatrices);
			timeDC = timeDC/(RUN_TIMES * randomMatrices);
			timeS = timeS/(RUN_TIMES * randomMatrices);
			
			System.out.println("Average Time Evaluations(for " + numEquations + "x" + numEquations + "): ");
			System.out.println("Classic Multiplication Matrix Method: " + timeCM);
			System.out.println("Divide & Conquer Multiplication Matrix Method: " + timeDC);
			System.out.println("Strassen Multiplication Matrix Method: " + timeS);
		}
		
		
	}
}
