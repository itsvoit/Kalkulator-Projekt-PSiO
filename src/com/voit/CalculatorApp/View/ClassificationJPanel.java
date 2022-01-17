package com.voit.CalculatorApp.View;

import com.voit.CalculatorApp.Controller.ControllerInterfaces.ClassifControllerInterface;
import com.voit.CalculatorApp.Model.ClassifModel.*;
import net.sf.javaml.core.Dataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClassificationJPanel extends JPanel {
	//Constants
	private final int ZERO_R = 0;
	private final int NAIVE_BAYES = 1;
	private final int MEAN_FEATURE_VOTING = 2;
	private final int K_NEAREST_NEIGHBOURS = 3;
	private final int NEAREST_MEAN_CLASSIFIER = 4;

	private final Font RESULT_FIELD_FONT = new Font("Times New Roman", Font.PLAIN, 18);

	private ClassifControllerInterface controller;

	//Swing components
	private JPanel classifierPanel;
	private JPanel results;
	private JTextField correctField;
	private JTextField correctLabel;
	private JTextField wrongField;
	private JTextField wrongLabel;
	private JTextField percentField;
	private JTextField percentLabel;

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
	private int lastDataSize;

	public ClassificationJPanel(ClassifControllerInterface controller){
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		makeGui();
	}

	private void makeGui(){
		classifier = new ZeroR();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(e -> {
			getTestData();
			getData();
			if (trainingData == null || data == null){
				System.out.println("Cannot start classification without those files, choose different file(s) and try again... ");
			} else {
				classifier.train(trainingData);
				int correct = classifier.classify(data);
				makeResultFields(correct);
				showResults();
			}
		});

		algorithms = new JComboBox<>(controller.getAlgorithmsList());
		algorithms.setMaximumSize(new Dimension(400, 100));
		algorithms.setAlignmentX(0.5f);
		algorithms.addItemListener(e -> {
			final int index = algorithms.getSelectedIndex();
			switch (index){
				case ZERO_R:
					classifier = new ZeroR();
					break;

				case NAIVE_BAYES:
					classifier = new NaiveByes();
					break;

				case MEAN_FEATURE_VOTING:
					classifier = new MeanFeatureVoting();
					break;

				case K_NEAREST_NEIGHBOURS:
					classifier = new KNearestNeighbours();
					break;

				case NEAREST_MEAN_CLASSIFIER:
					classifier = new NearestMeanClassifier();
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
		results.setLayout(new BoxLayout(results, BoxLayout.X_AXIS));

		JPanel correctPanel = new JPanel();
		JPanel wrongPanel = new JPanel();
		JPanel percentPanel = new JPanel();

		correctPanel.setLayout(new BoxLayout(correctPanel, BoxLayout.Y_AXIS));
		wrongPanel.setLayout(new BoxLayout(wrongPanel, BoxLayout.Y_AXIS));
//		wrongPanel.setAlignmentX(0.5f);
		percentPanel.setLayout(new BoxLayout(percentPanel, BoxLayout.Y_AXIS));

		closeResultsButton = new JButton("Close");
		closeResultsButton.addActionListener(e -> closeResults());
		closeResultsButton.setAlignmentX(0.5f);

		correctField = new JTextField();
		wrongField = new JTextField();
		percentField = new JTextField();

		correctLabel = new JTextField("Correct:");
		wrongLabel = new JTextField("Wrong:");
		percentLabel = new JTextField("Percentage: ");

		setUpJTextField(correctLabel);
		setUpJTextField(wrongLabel);
		setUpJTextField(percentLabel);
		setUpJTextField(correctField);
		setUpJTextField(wrongField);
		setUpJTextField(percentField);

		correctPanel.add(correctLabel);
		correctPanel.add(correctField);
		correctPanel.add(Box.createVerticalGlue());

		wrongPanel.add(wrongLabel);
		wrongPanel.add(wrongField);
		wrongPanel.add(closeResultsButton);
		wrongPanel.add(Box.createVerticalGlue());

		percentPanel.add(percentLabel);
		percentPanel.add(percentField);
		percentPanel.add(Box.createVerticalGlue());

		results.add(correctPanel);
		results.add(wrongPanel);
		results.add(percentPanel);
	}

	private void getTestData(){
		trainingData = getData(trainingChooser);
		/*int i = 0;
		for (Instance instance: trainingData){
			if (i == 100) break;
			System.out.println(instance);
			i++;
		}*/
	}

	private void getData(){
		data = getData(dataSetChooser);
	}

	private Dataset getData(JFileChooser chooser){
		Dataset dataset = null;
		File file = chooser.getSelectedFile();
		try {
			dataset = MyFileHandler.loadDataset(file, 8, ","); //For abalone testing data from 'UCI-small'
			System.out.println("Loading: " + file);
			lastDataSize = MyFileHandler.lastDataSize;
		} catch (IOException e) {
			System.out.println("Couldn't load the file");
		}

		return dataset;
	}

	private void makeResultFields(int correct){
		correctField.setText(Integer.toString(correct));
		wrongField.setText(Integer.toString(lastDataSize-correct));
		percentField.setText((((double)correct / (double)lastDataSize) * 100) + "%");
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

	private void setUpJTextField(JTextField field){
		field.setEditable(false);
		field.setBorder(BorderFactory.createEmptyBorder());
		field.setOpaque(false);
		field.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setFont(RESULT_FIELD_FONT);
	}
}
