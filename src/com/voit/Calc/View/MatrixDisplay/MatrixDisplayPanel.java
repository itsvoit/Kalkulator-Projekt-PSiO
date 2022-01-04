package com.voit.Calc.View.MatrixDisplay;

import com.voit.Calc.Model.Matrix;

import javax.swing.*;
import java.awt.*;

public class MatrixDisplayPanel extends JPanel {
	private JTextField [][] matrix;
	private int width;
	private int height;
	private JTextField name;

	private JPanel matrixPanel;

	public MatrixDisplayPanel(int width, int height){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		if (width <= 0 && height <= 0) {
			matrix = new JTextField[1][1];
			this.width = 1;
			this.height = 1;
		}
		else {
			matrix = new JTextField[width][height];
			this.width = width;
			this.height = height;
		}
		prepareMatrix(matrix);
		layoutMatrix();
	}

	public void makeEditable(boolean editable){
		for (int i=0; i<width; i++){
			for (int j = 0; j < height; j++) {
				matrix[i][j].setEditable(editable);
			}
		}
	}

	public void setField(int x, int y, double value){
		if (x < 0 || y < 0 || x > width || y > height) return;

		if (value == (int)value) matrix[x][y].setText(Integer.toString((int)value));
		else matrix[x][y].setText(Double.toString(value));
	}

	public void clearMatrix(){
		for (int i=0; i<width; i++){
			for (int j = 0; j < height; j++) {
				matrix[i][j].setText("0");
			}
		}
	}

	public void changeDimensions(int x, int y){
		if (x < 0 || y < 0) return;
		JTextField[][] tmpMatrix = new JTextField[x][y];

		prepareMatrix(tmpMatrix);

		for (int i = 0; i < x && i < width; i++) {
			for (int j = 0; j < y && j < height; j++) {
				tmpMatrix[i][j].setText(matrix[i][j].getText());
			}
		}
	}

	public void loadMatrix(Matrix m){
		if (m == null) return;
		width = m.getWidth();
		height = m.getHeight();
		matrix = new JTextField[width][height];
		prepareMatrix(matrix);

		double[][] tmpMatrix = m.getMatrix();
		for (int i=0; i<width; i++){
			for (int j = 0; j < height; j++) {
				setField(i, j, tmpMatrix[i][j]);
			}
		}
	}

	public Matrix saveMatrix(){
		if (matrix == null) return null;

		Matrix tmpMatrix = new Matrix(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				double value = Double.parseDouble(matrix[i][j].getText());
				if (value == (int) value) {
					int intVal = (int) value;
					tmpMatrix.setField(i, j, intVal);
				}
				else{
					tmpMatrix.setField(i, j, value);
				}
			}
		}
		return tmpMatrix;
	}

	private void layoutMatrix(){
		name = new JTextField("");
		matrixPanel = new JPanel();
		this.add(name);
		this.add(matrixPanel);

		matrixPanel.setLayout(new GridLayout(width, height, 3, 3));

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrixPanel.add(matrix[i][j]);
			}
		}
	}

	private void prepareMatrix(JTextField[][] tmpMatrix){
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tmpMatrix[i][j].setText("0");
				tmpMatrix[i][j].setEditable(true);
				tmpMatrix[i][j].setOpaque(false);
				tmpMatrix[i][j].setBorder(BorderFactory.createEmptyBorder());
			}
		}
	}

}
