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

	private MatrixControllerInterface controller;

	private JPanel matrix1;
	private JPanel matrix2;

	public MatricesJPanel(MatrixModelInterface model, MatrixControllerInterface controller){
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		makeMatrices();
	}

	private void makeMatrices(){
		JPanel centerPanel = new JPanel();
		matrix1 = new InnerMatrixPanel();
		matrix2 = new InnerMatrixPanel();
		centerPanel.add(matrix1);
		centerPanel.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
		centerPanel.add(matrix2);
		this.add(Box.createVerticalGlue());
		this.add(centerPanel);
		this.add(Box.createVerticalGlue());
	}

	private void loadMatrices(int option){
		//todo load matrices
		controller.deserializeMatrices(option);
	}

	private void saveMatrices(){
		controller.serializeMatrices();
	}

	@Override
	public void update(ModelUpdateEvent e) {
		MatrixModelUpdateEvent event = (MatrixModelUpdateEvent) e;
		((InnerMatrixPanel) matrix1).makeTopButtons();
		((InnerMatrixPanel) matrix2).makeTopButtons();
		System.out.println("Updated matrix view");
		this.revalidate();
		this.repaint();
	}

	private class InnerMatrixPanel extends JPanel{
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
			makeTopButtons();
			matrixPanel = new MatrixDisplayPanel(3, 3);
			makeBottomButtons();

			this.add(topButtons);
			this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			this.add(matrixPanel);
			this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
			this.add(bottomButtons);
		}

		private void makeTopButtons(){
			topButtons = new JPanel();
			topButtons.setLayout(new BoxLayout(topButtons, BoxLayout.X_AXIS));
			comboBox = new JComboBox<>(controller.getMatricesNames());
			comboBox.addItemListener(e -> {
				//todo itemstate change for combo box in inner matrix
			});

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
			bottomButtons = new JPanel();
			bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));

			loadButton = new JButton("Load");
			saveButton = new JButton("Save");
			clearButton = new JButton("Clear");

			loadButton.setFont(BUTTON_FONT);
			saveButton.setFont(BUTTON_FONT);
			clearButton.setFont(BUTTON_FONT);

			loadButton.addActionListener(e -> {
				int index = comboBox.getSelectedIndex();
				matrixPanel.loadMatrix(controller.getMatrix(index));
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

		private void refresh(){
			this.revalidate();
			this.repaint();
		}

	}
}
