package com.voit.CalculatorApp.View;

import com.voit.CalculatorApp.Controller.ControllerInterfaces.MatrixControllerInterface;
import com.voit.CalculatorApp.Model.MatrixModel.Matrix;
import com.voit.CalculatorApp.Model.ModelInterfaces.MatrixModelInterface;
import com.voit.CalculatorApp.Model.ModelObservers.MatrixModelObserver;
import com.voit.CalculatorApp.Model.ModelObservers.MatrixModelUpdateEvent;
import com.voit.CalculatorApp.Model.ModelObservers.ModelObservable;
import com.voit.CalculatorApp.Model.ModelObservers.ModelUpdateEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MatricesJPanel extends JPanel implements MatrixModelObserver {
	private final int MAX_DIMENSION = 10;
	private final int H_GAP = 5;
	private final int V_GAP = 5;

	private final Font DIM_FONT = new Font("TimesRoman", Font.BOLD, 15);
	private final Font BUTTON_FONT = new Font("TimesRoman", Font.BOLD, 14);
	private final Font FIELD_FONT = new Font("TimesRoman", Font.PLAIN, 16);

	private MatrixControllerInterface controller;

	private InnerMatrixPanel matrix1;
	private OperationsPanel operations;
	private InnerMatrixPanel matrix2;

	private OutputMatrixPanel outputMatrix;

	public MatricesJPanel(MatrixModelInterface model, MatrixControllerInterface controller){
		this.controller = controller;
		((ModelObservable) model).registerObserver(this);
		System.out.println("Registering MatricesJPanel as observer of Model");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		makeMatrices();
	}

	private void makeMatrices(){
		JPanel centerPanel = new JPanel();
		matrix1 = new InnerMatrixPanel();
		operations = new OperationsPanel();
		matrix2 = new InnerMatrixPanel();
		centerPanel.add(matrix1);
		centerPanel.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
		centerPanel.add(operations);
		centerPanel.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
		centerPanel.add(matrix2);
		outputMatrix = new OutputMatrixPanel(new Matrix(3,3));
		this.add(Box.createVerticalGlue());
		this.add(centerPanel);
		this.add(Box.createVerticalGlue());
		this.add(outputMatrix);
		this.add(Box.createVerticalGlue());
	}

	@Override
	public void update(ModelUpdateEvent e) {
		MatrixModelUpdateEvent event = (MatrixModelUpdateEvent) e;
		matrix1.makeTopButtons();
		matrix2.makeTopButtons();
		System.out.println("Updated matrix view");
		this.revalidate();
		this.repaint();
	}

	private void setMatrix(int i, Matrix m){
		if (i == 0) matrix1.setMatrix(m);
		else if (i == 1) matrix2.setMatrix(m);
	}

	public class InnerMatrixPanel extends JPanel{
		private JPanel topButtons;
		private JComboBox<String> comboBox;
		private JTextField width;
		private JTextField height;
		private JButton dimButton;

		private MatrixDisplayPanel matrixPanel;

		private JPanel bottomButtons;
		private JButton loadButton;
		private JButton saveButton;
		private JButton clearButton;

		public InnerMatrixPanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			topButtons = new JPanel();
			makeTopButtons();
			matrixPanel = new MatrixDisplayPanel(3, 3);
			bottomButtons = new JPanel();
			makeBottomButtons();

			this.add(topButtons);
			this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			this.add(matrixPanel);
			this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			this.add(bottomButtons);
		}

		public Matrix getMatrix(){
			return matrixPanel.getMatrix();
		}

		public void setMatrix(Matrix m){
			matrixPanel.setMatrix(m);
		}

		public void makeTopButtons(){
			topButtons.removeAll();
			topButtons.setLayout(new BoxLayout(topButtons, BoxLayout.X_AXIS));
			comboBox = new JComboBox<>(controller.getMatricesNames());

			width = new JTextField();
			height = new JTextField();
			width.setFont(DIM_FONT);
			height.setFont(DIM_FONT);
			prepareDimJTextField(width);
			prepareDimJTextField(height);

			dimButton = new JButton("OK");
			dimButton.addActionListener(e -> saveDimension());
			dimButton.setFont(BUTTON_FONT);

			topButtons.add(comboBox);
			topButtons.add(width);
			topButtons.add(height);
			topButtons.add(dimButton);
		}

		private void saveDimension(){
			int x = Integer.parseInt(width.getText());
			int y = Integer.parseInt(height.getText());

			matrixPanel.changeDimensions(x, y);
			System.out.println("New dimensions: " + x + ", " + y);
			refresh();
		}

		private void makeBottomButtons(){
			bottomButtons.removeAll();
			bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));

			loadButton = new JButton("Load");
			saveButton = new JButton("Save");
			clearButton = new JButton("Clear");

			loadButton.setFont(BUTTON_FONT);
			saveButton.setFont(BUTTON_FONT);
			clearButton.setFont(BUTTON_FONT);

			loadButton.addActionListener(e -> {
				int index = comboBox.getSelectedIndex();
				if (index > -1) matrixPanel.setMatrix(controller.getMatrix(index));
			});

			saveButton.addActionListener(e -> {
				Matrix savedMatrix = matrixPanel.saveMatrix();
				controller.saveMatrix(savedMatrix);
			});

			clearButton.addActionListener(e -> matrixPanel.clearMatrix());

			bottomButtons.add(loadButton);
			bottomButtons.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			bottomButtons.add(saveButton);
			bottomButtons.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			bottomButtons.add(clearButton);
		}

		private void prepareDimJTextField(JTextField field){
			field.setText("3");
			field.setHorizontalAlignment(SwingConstants.CENTER);
			field.setOpaque(false);
			field.setBorder(BorderFactory.createEmptyBorder());
			field.setEditable(true);
			field.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					super.focusLost(e);

					if (field.getText().matches(".*[a-z].*")){
						field.setText("3");
					} else {
						double tmp = 0;
						try {
							tmp = Double.parseDouble(field.getText());
						} catch (Exception ex){
							field.setText("3");
						}
						int val = (int) tmp;
						if (val > 0 && val < MAX_DIMENSION){
							field.setText(Integer.toString(val));
						} else field.setText("3");
					}
				}

				@Override
				public void focusGained(FocusEvent e){
					super.focusGained(e);

					field.setText("");
				}
			});
		}

		private void updateDimensions(int x, int y){
			width.setText(Integer.toString(x));
			height.setText(Integer.toString(y));
		}

		private void refresh(){
			this.revalidate();
			this.repaint();
		}

		public class MatrixDisplayPanel extends JPanel{
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

			public Matrix getMatrix(){
				Matrix out = new Matrix(width, height);

				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						out.setField(i, j, Double.parseDouble(matrix[i][j].getText()));
					}
				}

				return out;
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
				nameString = "";
				name.setText("");
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

			/**
			 * Loads the matrix
			 *
			 * @param m matrix to load
			 */
			public void setMatrix(Matrix m){
				System.out.println("Loading matrix: " + m);
				if (m == null) return;
				nameString = m.getName();
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
				updateDimensions(width, height);
				layoutMatrix();
			}

			/**
			 * Converts the matrix from gui to a Matrix object
			 *
			 * @return Matrix object converted from gui
			 */
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
				tmpMatrix.setName(name.getText());
				return tmpMatrix;
			}

			/**
			 * Lays out the 2d JTextField array inside the JPanel
			 *
			 */
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
				this.revalidate();
				this.repaint();
			}

			/**
			 * Creates 2d array of JTextFields with text set to "0" in each cell
			 *
			 * @param x width
			 * @param y height
			 * @return JTextField[][] with '0' in each cell
			 */
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
	}

	private class OperationsPanel extends JPanel{
		private JButton add;
		private JButton subtract;
		private JButton multiply;

		private Matrix m1;
		private Matrix m2;

		public OperationsPanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			add = new JButton("ADD");
			subtract = new JButton("SUB");
			multiply = new JButton("MULTI");

			add.setAlignmentX(0.5f);
			subtract.setAlignmentX(0.5f);
			multiply.setAlignmentX(0.5f);

			add.addActionListener(e -> add());
			subtract.addActionListener(e -> subtract());
			multiply.addActionListener(e -> multiply());

			this.add(add);
			this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			this.add(subtract);
			this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			this.add(multiply);
		}

		private void add(){
			getMatrices();
			Matrix out = m1.add(m2);
			if (out == null) {
				outputMatrix.cannotPerformOperation();
				return;
			}
			Matrix emptyOut = new Matrix(out.getWidth(), out.getHeight());
			out.setName("");
			emptyOut.setName("");
			if (!out.equals(emptyOut)) outputMatrix.setOutputMatrix(out);
		}

		private void subtract(){
			getMatrices();
			Matrix out = m1.subtract(m2);
			if (out == null) {
				outputMatrix.cannotPerformOperation();
				return;
			}
			Matrix emptyOut = new Matrix(out.getWidth(), out.getHeight());
			out.setName("");
			emptyOut.setName("");
			if (!out.equals(emptyOut)) outputMatrix.setOutputMatrix(out);
		}

		private void multiply(){
			getMatrices();
			Matrix out = m1.multiply(m2);
			if (out == null) {
				outputMatrix.cannotPerformOperation();
				return;
			}
			Matrix emptyOut = new Matrix(out.getWidth(), out.getHeight());
			out.setName("");
			emptyOut.setName("");
			if (!out.equals(emptyOut)) {
				outputMatrix.setOutputMatrix(out);
			}
		}

		private void getMatrices(){
			m1 = matrix1.getMatrix();
			m2 = matrix2.getMatrix();
		}
	}

	private class OutputMatrixPanel extends JPanel{
		private int width;
		private int height;
		private JTextField[][] matrixFields;
		private JButton swap1Button;
		private JButton swap2Button;
		private JButton clearButton;

		private JPanel matrixPanel;

		private boolean error;

		public OutputMatrixPanel(Matrix m){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.width = m.getWidth();
			this.height = m.getHeight();
			matrixFields = new JTextField[width][height];
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					matrixFields[i][j] = new JTextField();
					JTextField field = matrixFields[i][j];
					field.setText("0");
					field.setHorizontalAlignment(SwingConstants.CENTER);
					field.setFont(FIELD_FONT);
					field.setEditable(false);
					field.setFocusable(false);
					field.setOpaque(false);
					field.setBorder(BorderFactory.createEmptyBorder());
				}
			}

			swap1Button = new JButton("Swap 1");
			swap2Button = new JButton("Swap 2");
			clearButton = new JButton("Clear");

			swap1Button.addActionListener(e -> swap1());
			swap2Button.addActionListener(e -> swap2());
			clearButton.addActionListener(e -> clear());

			setOutputMatrix(m);
		}

		public void cannotPerformOperation(){
			error = true;
			this.width = 1;
			this.height = 1;
			matrixFields = new JTextField[1][1];
			matrixFields[0][0]= new JTextField("Cannot perform this operation");
			JTextField field = matrixFields[0][0];
			field.setHorizontalAlignment(SwingConstants.CENTER);
			field.setFont(FIELD_FONT);
			field.setEditable(false);
			field.setFocusable(false);
			field.setOpaque(false);
			field.setBorder(BorderFactory.createEmptyBorder());
			layoutOutputMatrix();
		}

		private void layoutOutputMatrix(){
			this.removeAll();
			matrixPanel = new JPanel();
			this.add(matrixPanel);

			matrixPanel.setLayout(new GridLayout(width, height, 3, 3));

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					matrixPanel.add(matrixFields[i][j]);
				}
			}

			JPanel buttons = new JPanel();
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
			buttons.add(Box.createHorizontalGlue());
			buttons.add(swap1Button);
			buttons.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			buttons.add(swap2Button);
			buttons.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			buttons.add(clearButton);
			buttons.add(Box.createHorizontalGlue());

			this.add(Box.createVerticalGlue());
			this.add(buttons);
			this.add(Box.createVerticalGlue());

			this.revalidate();
			this.repaint();
		}

		public void setOutputMatrix(Matrix m){
			error = false;
			this.width = m.getWidth();
			this.height = m.getHeight();

			matrixFields = new JTextField[width][height];

			for (int i = 0; i < m.getWidth(); i++) {
				for (int j = 0; j < m.getHeight(); j++) {
					double val = m.getField(i, j);
					if (val == (int)val){
						matrixFields[i][j] = new JTextField(Integer.toString((int)val));
					} else {
						matrixFields[i][j] = new JTextField(Double.toString(val));
					}
					matrixFields[i][j].setHorizontalAlignment(SwingConstants.CENTER);
					matrixFields[i][j].setFont(FIELD_FONT);
					matrixFields[i][j].setEditable(false);
					matrixFields[i][j].setFocusable(false);
					matrixFields[i][j].setOpaque(false);
					matrixFields[i][j].setBorder(BorderFactory.createEmptyBorder());
				}
			}
			layoutOutputMatrix();
			this.setVisible(true);
		}

		private void swap1(){
			if (error) return;
			setMatrix(0, getMatrix());
			this.revalidate();
			this.repaint();
		}

		private void swap2(){
			if (error) return;
			setMatrix(1, getMatrix());
			this.revalidate();
			this.repaint();
		}

		private void clear(){
			if (error) return;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					matrixFields[i][j] = new JTextField();
					JTextField field = matrixFields[i][j];
					field.setText("0");
					field.setHorizontalAlignment(SwingConstants.CENTER);
					field.setFont(FIELD_FONT);
					field.setEditable(false);
					field.setFocusable(false);
					field.setOpaque(false);
					field.setBorder(BorderFactory.createEmptyBorder());
				}
			}
			layoutOutputMatrix();
			this.revalidate();
			this.repaint();
		}

		private Matrix getMatrix(){
			Matrix m = new Matrix(width, height);
			m.setName("");
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					m.setField(i, j, Double.parseDouble(matrixFields[i][j].getText()));
				}
			}
			return m;
		}
	}

	/**
	 * Prepares every cell of 2d JTextField array for display
	 *
	 * @param tmpMatrix
	 * @param x
	 * @param y
	 */
	public void prepareMatrix(JTextField[][] tmpMatrix, int x, int y){
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
}
