package com.voit.Calc.View;

import com.voit.Calc.Controller.ControllerInterface;
import com.voit.Calc.Model.Matrix;
import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelObservers.ModelObserver;
import com.voit.Calc.Model.ModelObservers.ModelUpdateEvent;
import com.voit.Calc.View.MatrixDisplay.MatrixDisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MatrixJPanel extends JPanel implements ModelObserver {
	private final int MAX_DIMENSION = 10;
	private final int H_GAP = 5;
	private final int V_GAP = 5;

	private ControllerInterface controller;

	JPanel matrix1;
	JPanel matrix2;

	public MatrixJPanel(ModelInterface model, ControllerInterface controller){
		this.controller = controller;
		makeMatrices();

	}

	private void makeMatrices(){
		matrix1 = new InnerMatrixPanel();
		matrix2 = new InnerMatrixPanel();
		this.add(matrix1);
		this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
		this.add(matrix2);
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
		//todo update from model
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
			this.add(matrixPanel);
			this.add(bottomButtons);
		}

		private void makeTopButtons(){
			topButtons = new JPanel();
			topButtons.setLayout(new BoxLayout(topButtons, BoxLayout.X_AXIS));
			comboBox = new JComboBox<>();
			comboBox.addItemListener(e -> {
				//todo itemstate change for combo box in inner matrix
			});

			width = new JTextField();
			height = new JTextField();
			dimButton = new JButton("OK");
			dimButton.addActionListener(e -> saveDimension());

			prepareDimJTextField(width);
			prepareDimJTextField(height);

			topButtons.add(comboBox);
			topButtons.add(width);
			topButtons.add(height);
			topButtons.add(dimButton);
		}

		private void saveDimension(){
			int x = Integer.parseInt(width.getText());
			int y = Integer.parseInt(height.getText());

			matrixPanel.changeDimensions(x, y);
		}

		private void makeBottomButtons(){
			bottomButtons = new JPanel();
			bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));

			loadButton = new JButton("Load");
			saveButton = new JButton("Save");
			clearButton = new JButton("Clear");

			loadButton.addActionListener(e -> {
				Object selectedItem = comboBox.getSelectedItem();
				//todo get matrix from selected item in combo box
				matrixPanel.loadMatrix(null); //switch with correct item
			});

			saveButton.addActionListener(e -> {
				Matrix savedMatrix = matrixPanel.saveMatrix();
				//todo do something with saved matrix
			});

			clearButton.addActionListener(e -> matrixPanel.clearMatrix());

			bottomButtons.add(loadButton);
			bottomButtons.add(saveButton);
			bottomButtons.add(clearButton);
		}

		private void prepareDimJTextField(JTextField field){
			field.setText("1");
			field.setOpaque(false);
			field.setBorder(BorderFactory.createEmptyBorder());
			field.setEditable(true);
			field.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					super.focusLost(e);

					if (field.getText().matches(".*[a-z].*")){
						field.setText("1");
					} else {
						double tmp = 0;
						try {
							tmp = Double.parseDouble(field.getText());
						} catch (Exception ex){
							field.setText("1");
						}
						int val = (int) tmp;
						if (val > 0 && val < MAX_DIMENSION){
							field.setText(Integer.toString(val));
						} else field.setText("1");
					}
				}
			});
		}

	}
}
