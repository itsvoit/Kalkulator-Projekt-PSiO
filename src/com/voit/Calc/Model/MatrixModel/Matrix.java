package com.voit.Calc.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Matrix implements Serializable, Cloneable {
	private int width;
	private int height;
	private double[][] matrix;
	private String name; //todo implement name functionality

	public Matrix (int width, int height){
		if (width <= 0) width = 1;
		if (height <= 0) height = 1;
		this.width = width;
		this.height = height;
		matrix = new double[width][height];
	}

	public Matrix (int width, int height, double[]tab){
		this(width, height);
		for (int i = 0, j = 0, cnt = 0; cnt< width * height; cnt++, i++){
			if (i >= width) {
				i = 0;
				j++;
			}
			matrix[i][j] = tab[cnt];
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double[][] getMatrix(){
		return matrix.clone();
	}

	public double getField(int x, int y) throws IndexOutOfBoundsException{
		checkIndices(x, y);

		return matrix[x][y];
	}

	public void setMatrix(double[][] matrix) {
		if (matrix == null) return;
		if (matrix[0] == null) return;

		this.matrix = matrix;
		this.width = matrix.length;
		this.height = matrix[0].length;
	}

	public void setField(int x, int y, double val) throws IndexOutOfBoundsException{
		checkIndices(x, y);

		matrix[x][y] = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void checkIndices(int x, int y) throws IndexOutOfBoundsException{
		if (x >= this.width && y >= this.height) throw new IndexOutOfBoundsException("Indices are out of bounds");
		else if (x >= this.width) throw new IndexOutOfBoundsException("X index is out of bounds");
		else if (y >= this.height) throw new IndexOutOfBoundsException("Y index is out of bounds");
	}

	//Overrides for comparing Matrices

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Matrix matrix1 = (Matrix) o;
		return width == matrix1.width && height == matrix1.height && Arrays.deepEquals(matrix, matrix1.matrix);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(width, height);
		result = 31 * result + Arrays.deepHashCode(matrix);
		return result;
	}

	@Override
	public Matrix clone() {
		try {
			Matrix clone = (Matrix) super.clone();
			clone.setMatrix(matrix.clone());
			clone.height = height;
			clone.width = width;
			clone.setName(this.getName());
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
