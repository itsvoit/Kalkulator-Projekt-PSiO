package com.voit.Calc.View;

import com.voit.Calc.Controller.ControllerInterfaces.ClassifControllerInterface;
import com.voit.Calc.Model.ClassifModel.*;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

//todo Things to implement:
// - training data choice (or a way to choose none)
// - data set choice
// - classification algorithm choice
// - for K-neighbors alg choice for the K parameter
public class ClassificationJPanel extends JPanel {
	//Constants
	private final int LOGISTIC_REGRESSION = 0;
	private final int NAIVE_BAYES = 1;
	private final int STOCHASTIC_GRADIENT_DESCENT = 2;
	private final int K_NEAREST_NEIGHBOURS = 3;
	private final int SUPPORT_VECTOR_MACHINE = 4;

	private ClassifControllerInterface controller;

	//Swing components
	private JPanel classifierPanel;
	private JPanel results;

	private JComboBox<String> algorithms;
	private JFileChooser trainingChooser;
	private JFileChooser dataSetChooser;

	private JPanel classifierSpecificPanel;

	private JButton submitButton;

	private JButton closeResultsButton;

	//Data classifier fields
	private ClassificationAlgorithmInterface classifier;
	private Dataset trainingData;
	private Dataset data;

	public ClassificationJPanel(ClassifControllerInterface controller){
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		makeGui();
	}

	private void makeGui(){
		classifier = new LogisticRegresion();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(e -> {
			getTestData();
			getData();
			if (trainingData == null || data == null){
				System.out.println("Cannot start classification without those files, choose different file(s) and try again... ");
			} else {
				classifier.train(trainingData);
				classifier.classify(data);
				showResults();
			}
		});

		algorithms = new JComboBox<>(controller.getAlgorithmsList());
		algorithms.addItemListener(e -> {
			final int index = algorithms.getSelectedIndex();
			switch (index){
				case LOGISTIC_REGRESSION:
					classifier = new LogisticRegresion();
					break;

				case NAIVE_BAYES:
					classifier = new NaiveByes();
					break;

				case STOCHASTIC_GRADIENT_DESCENT:
					classifier = new StochasticGradientDescent();
					break;

				case K_NEAREST_NEIGHBOURS:
					classifier = new KNearestNeighbours();
					break;

				case SUPPORT_VECTOR_MACHINE:
					classifier = new SupportVectorMachine();
					break;

				default:
					System.out.println("This option does not exist...");
					break;
			}
		});

		trainingChooser = new JFileChooser(System.getProperty("user.dir"));
		dataSetChooser = new JFileChooser(System.getProperty("user.dir"));

		classifierSpecificPanel = new JPanel();

		classifierPanel = new JPanel();
		classifierPanel.setLayout(new BoxLayout(classifierPanel, BoxLayout.Y_AXIS));

		classifierPanel.add(trainingChooser);
		classifierPanel.add(algorithms);
		classifierPanel.add(classifierSpecificPanel);
		classifierPanel.add(dataSetChooser);
		classifierPanel.add(submitButton);

		this.add(classifierPanel);

		results = new JPanel();
		closeResultsButton = new JButton("Close");
		closeResultsButton.addActionListener(e -> closeResults());


		results.add(closeResultsButton);
	}

	private void getTestData(){
		trainingData = getData(trainingChooser);
	}

	private void getData(){
		data = getData(dataSetChooser);
	}

	private Dataset getData(JFileChooser chooser){
		Dataset dataset = null;
		File file = chooser.getSelectedFile();
		try {
			dataset = FileHandler.loadDataset(file, ",");
		} catch (IOException e) {
			System.out.println("Couldn't load the file");
		}
		return dataset;
	}

	private void showResults(){
		this.removeAll();
		this.add(results);
		refresh();
	}

	private void closeResults(){
		this.removeAll();
		this.add(classifierPanel);
		refresh();
	}

	private void refresh(){
		this.revalidate();
		this.repaint();
	}
}
