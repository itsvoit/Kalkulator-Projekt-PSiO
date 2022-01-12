package com.voit.Calc.View.MatrixDisplay;

import com.voit.Calc.Model.MatrixModel.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MatrixDisplayPanel extends JPanel {
	private final Font FIELD_FONT = new Font("TimesRoman", Font.PLAIN, 16);
	private final Font NAME_FONT = new Font("TimesRoman", Font.BOLD, 15);

	private JTextField [][] matrix;
	private int width;
	private int height;
	private JTextField name;
	private String nameString;

	private JPanel matrixPanel;

	public MatrixDisplayPanel(int width, int height){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		if (width <= 0 && height <= 0) {
			matrix = createMatrix(3, 3);
			this.width = 3;
			this.height = 3;
		}
		else {
			matrix = createMatrix(width, height);
			this.width = width;
			this.height = height;
		}
		nameString = "";

		prepareMatrix(matrix, width, height);
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
		JTextField[][] tmpMatrix = createMatrix(x, y);

		prepareMatrix(tmpMatrix, x, y);

		for (int i = 0; i < x && i < width; i++) {
			for (int j = 0; j < y && j < height; j++) {
				tmpMatrix[i][j].setText(matrix[i][j].getText());
			}
		}
		matrix = tmpMatrix;
		width = x;
		height = y;
		layoutMatrix();
		showDebug();
	}

	public void loadMatrix(Matrix m){
		if (m == null) return;
		width = m.getWidth();
		height = m.getHeight();
		matrix = createMatrix(width, height);
		prepareMatrix(matrix, width, height);

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
		this.removeAll();
		name = new JTextField(nameString);
		name.setFont(NAME_FONT);
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);

				name.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);

				nameString = name.getText();
			}
		});
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

	private JTextField[][] createMatrix(int x, int y){
		if (x <= 0 || y <= 0) return null;

		JTextField[][] tmpMatrix = new JTextField[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				tmpMatrix[i][j] = new JTextField("0");
			}
		}
		return tmpMatrix;
	}

	private void prepareMatrix(JTextField[][] tmpMatrix, int x, int y){
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				JTextField field = tmpMatrix[i][j];
				field.setText("0");
				field.setHorizontalAlignment(SwingConstants.CENTER);
				field.setFont(FIELD_FONT);
				field.setEditable(true);
				field.setOpaque(false);
				field.setBorder(BorderFactory.createEmptyBorder());
				field.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						super.focusLost(e);

						if (field.getText().matches(".*[a-z].*")){
							field.setText("0");
						}
						else {
							try {
								Double.parseDouble(field.getText());
							} catch (Exception ex) {
								field.setText("0");
							}
						}
					}

					@Override
					public void focusGained(FocusEvent e){
						super.focusGained(e);

						field.setText("");
					}
				});
			}
		}
	}

	private void showDebug(){
		if (nameString == null) System.out.println("name is null");
		else System.out.println("Matrix name: " + nameString);

		if (matrix == null) System.out.println("Matrix is null");
		else {
			System.out.println("Matrix fields:");
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (matrix.length <= i){
						System.out.println("Za duzo wierszy ");
					} else if (matrix[i].length <= j){
						System.out.println("Za duzo kolumn ");
					} else
						System.out.print(matrix[i][j].getText() + " ");
				}
				System.out.println();
			}
		}
	}

}
