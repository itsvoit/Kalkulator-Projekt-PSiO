package com.voit.Calc.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Matrix implements Serializable {
	private int x;
	private int y;
	private int[][] matrix;

	public Matrix (int x, int y){
		if (x <= 0) x = 1;
		if (y <= 0) y = 1;
		this.x = x;
		this.y = y;
		matrix = new int[x][y];
	}

	public Matrix (int x, int y, int[] tab){
		this(x, y);
		for (int i=0, j=0, cnt=0; cnt<x*y; cnt++, i++){
			if (i >= x) {
				i = 0;
				j++;
			}
			matrix[i][j] = tab[cnt];
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[][] getMatrix(){
		return matrix.clone();
	}

	public int getField(int x, int y) throws IndexOutOfBoundsException{
		checkIndices(x, y);

		return matrix[x][y];
	}

	public void setMatrix(int[][] matrix) {
		if (matrix == null) return;
		if (matrix[0] == null) return;

		this.matrix = matrix;
		this.x = matrix.length;
		this.y = matrix[0].length;
	}

	public void setField(int x, int y, int val) throws IndexOutOfBoundsException{
		checkIndices(x, y);

		matrix[x][y] = val;
	}

	private void checkIndices(int x, int y) throws IndexOutOfBoundsException{
		if (x >= this.x && y >= this.y) throw new IndexOutOfBoundsException("Indices are out of bounds");
		else if (x >= this.x) throw new IndexOutOfBoundsException("X index is out of bounds");
		else if (y >= this.y) throw new IndexOutOfBoundsException("Y index is out of bounds");
	}

	//Overrides for comparing Matrices

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix matrix1 = (Matrix) o;
		return x == matrix1.x && y == matrix1.y && Arrays.deepEquals(matrix, matrix1.matrix);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(x, y);
		result = 31 * result + Arrays.deepHashCode(matrix);
		return result;
	}
}
