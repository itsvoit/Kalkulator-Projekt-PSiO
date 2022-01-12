package com.voit.Calc.View;

import com.voit.Calc.Controller.ControllerInterfaces.ClassifControllerInterface;

import javax.swing.*;

//todo Things to implement:
// - training data choice (or a way to choose none)
// - data set choice
// - classification algorithm choice
// - for K-neighbors alg choice for the K parameter
public class ClassificationJPanel extends JPanel {
	private ClassifControllerInterface controller;

	private JComboBox<String> algorithms;
	private JFileChooser trainingChooser;
	private JFileChooser dataSetChooser;

	private JButton submitButton;

	public ClassificationJPanel(ClassifControllerInterface controller){
		this.controller = controller;

		submitButton = new JButton("Submit");
		algorithms = new JComboBox<String>(controller.getAlgorithmsList());

		trainingChooser = new JFileChooser();
		dataSetChooser = new JFileChooser();
	}


}
