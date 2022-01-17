package com.voit.CalculatorApp.Model.MatrixModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Matrix implements Serializable, Cloneable {
	private static final long serialVersionUID = -3554416904878538017L;
	private int width;
	private int height;
	private double[][] matrix;
	private String name;

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

	public Matrix (double[][] tab){
		if (tab == null) {
			width = 3;
			height = 3;
		}
		else if (tab[0] == null) {
			width = tab.length;
		}
		else{
			width = tab.length;
			height = tab[0].length;
		}
		matrix = tab;
	}

	/**
	 * Add m to this matrix
	 *
	 * @param m Matrix to add
	 * @return new Matrix: result of addition (this + m)
	 */
	public Matrix add(Matrix m){
		if (height != m.getHeight()) return null;
		if (width != m.getWidth()) return null;

		Matrix out = new Matrix(width, height);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				double val = getField(i, j) + m.getField(i, j);
				out.setField(i, j, val);
			}
		}
		return out;
	}

	/**
	 * Subtract m from this matrix
	 *
	 * @param m Matrix to subtract
	 * @return new Matrix: result of subtraction (this - m)
	 */
	public Matrix subtract(Matrix m){
		if (height != m.getHeight()) return null;
		if (width != m.getWidth()) return null;


		Matrix out = new Matrix(width, height);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				double val = getField(i, j) - m.getField(i, j);
				out.setField(i, j, val);
			}
		}
		return out;
	}

	/**
	 * Multiply this by m
	 * Works only if this.width == m.height
	 * New matrix size will be [this.width, m.height]
	 *
	 * @param m Matrix to multiply by
	 * @return new Matrix: result of multiplication (this * m)
	 */
	public Matrix multiply(Matrix m){
		if (height != m.getWidth()) return null;

		int rows1 = width;
		int cols1 = height;
		int cols2 = m.getHeight();

		double[][] out = new double[rows1][cols2];

		for (int i=0; i<rows1; i++){
			for (int j=0; j<cols2; j++){
				for (int k=0; k<cols1; k++){
					System.out.printf("[%d][%d] += [%d][%d] * [%d][%d]\n", i, j, i, j, i, k);
					System.out.printf("  %f   +=   %f   *   %f  \n", out[i][j], getField(i, k), m.getField(k, j));
					out[i][j] += getField(i, k) * m.getField(k, j);
				}
			}
		}
		return new Matrix(out);
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

	private void checkIndices(int x, int y) throws IndexOutOfBoundsException{
		if (x >= this.width && y >= this.height) throw new IndexOutOfBoundsException("Indices are out of bounds");
		else if (x >= this.width) throw new IndexOutOfBoundsException("X index is out of bounds");
		else if (y >= this.height) throw new IndexOutOfBoundsException("Y index is out of bounds");
	}

	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append(name).append("\n");
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				out.append(getField(i, j)).append(" ");
			}
			out.append("\n");
		}
		return out.toString();
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
		int result = Objects.hash(width, height, name);
		result = 31 * result + Arrays.deepHashCode(matrix);
		return result;
	}

	@Override
	public Matrix clone() {
		try {
			Matrix clone = (Matrix) super.clone();
			clone.setMatrix(matrix.clone());
			clone.setName(name);
			clone.height = height;
			clone.width = width;
			clone.setName(this.getName());
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
