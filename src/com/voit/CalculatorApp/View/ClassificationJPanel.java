package com.voit.CalculatorApp.View;

import com.voit.CalculatorApp.Controller.ControllerInterfaces.ClassifControllerInterface;
import com.voit.CalculatorApp.Model.ClassifModel.*;
import com.voit.CalculatorApp.View.Customs.MyCustomScrollPane;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;

public class ClassificationJPanel extends JPanel {
	//Constants
	private final int ZERO_R = 0;
	private final int NAIVE_BAYES = 1;
	private final int MEAN_FEATURE_VOTING = 2;
	private final int K_NEAREST_NEIGHBOURS = 3;
	private final int NEAREST_MEAN_CLASSIFIER = 4;

	private final Font FILE_ATTACHED_FONT = new Font("Arial", Font.PLAIN, 18);
	private final Font FILE_LABEL_FONT = new Font("Arial", Font.BOLD, 20);
	private final Font RESULT_FIELD_FONT = new Font("Times New Roman", Font.PLAIN, 18);

	private final int CLASS_LABEL_HEIGHT = 35;

	private ClassifControllerInterface controller;

	//Swing components
	private JPanel classifierPanel;
	private JPanel results;
	private JPanel classDetailsPanel;
	private JTextField algorithmField;
	private JTextField correctField;
	private JTextField wrongField;
	private JTextField percentField;

	private JComboBox<String> algorithms;
	private JFileChooser trainingChooser;
	private JFileChooser dataChooser;

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
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(e -> {
			getTestData();
			getData();
			if (trainingData == null || data == null){
				JOptionPane.showMessageDialog(this.getParent(),
						"Please attach the files...",
						"Error",
						JOptionPane.ERROR_MESSAGE);

				System.out.println("Cannot start classification without those files, choose different file(s) and try again... ");
			} else {
				classifier.train(trainingData);
				int correct = classifier.classify(data);
				Map<Object, PerformanceMeasure> classPerformanceMap = EvaluateDataset.testDataset(classifier.getClassifier(), data);
				makeResultFields(correct, classPerformanceMap);
				showResults();
			}
		});

		algorithms = new JComboBox<>(controller.getAlgorithmsList());
		Font newAlgFont = algorithms.getFont().deriveFont(16f);
		algorithms.setFont(newAlgFont);
		algorithms.setMaximumSize(new Dimension(400, 100));
//		algorithms.setAlignmentX(0.5f);
		algorithms.addItemListener(e -> {
			final int index = algorithms.getSelectedIndex();
			switch (index){
				case ZERO_R:
					classifier = new ZeroR();
					break;

				case NAIVE_BAYES:
					classifier = new NaiveBayes();
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
		dataChooser = new JFileChooser(System.getProperty("user.dir"));

		classifierPanel = new JPanel();
		classifierPanel.setLayout(new GridBagLayout());

		JTextField trainingDataLabel = new JTextField("Training data:");
		JTextField dataLabel = new JTextField("Data: ");

		trainingDataLabel.setFont(FILE_LABEL_FONT);
		trainingDataLabel.setOpaque(false);
		trainingDataLabel.setEditable(false);
		trainingDataLabel.setBorder(BorderFactory.createEmptyBorder());

		dataLabel.setFont(FILE_LABEL_FONT);
		dataLabel.setOpaque(false);
		dataLabel.setEditable(false);
		dataLabel.setBorder(BorderFactory.createEmptyBorder());

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 5;
		c.ipady = 5;
		c.insets = new Insets(3, 6, 3, 6);
		c.weightx = 0.1f;
		c.weighty = 0.1f;

		classifierPanel.add(trainingDataLabel, c);

		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		classifierPanel.add(new FileChooser(trainingChooser), c);

		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		classifierPanel.add(dataLabel, c);

		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		classifierPanel.add((new FileChooser(dataChooser)), c);

		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weighty = 0.2f;
		classifierPanel.add(algorithms, c);

		c.gridy = 5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 0.1f;
		classifierPanel.add(submitButton, c);

		this.add(classifierPanel);

		makeResultsPanel();
	}

	private void makeResultsPanel() {
		results = new JPanel();
		results.setLayout(new GridBagLayout());

		classDetailsPanel = new JPanel();
		classDetailsPanel.setLayout(new BoxLayout(classDetailsPanel, BoxLayout.Y_AXIS));

		JButton closeResultsButtonTop = new JButton("Close");
		closeResultsButtonTop.addActionListener(e -> closeResults());
		JButton closeResultsButtonBottom = new JButton("Close");
		closeResultsButtonBottom.addActionListener(e -> closeResults());

		JTextField algorithmLabel = new JTextFieldWrapper("Algorithm used: ");
		algorithmField = new JTextFieldWrapper();
		JTextField correctLabel = new JTextFieldWrapper("Correct:");
		JTextField wrongLabel = new JTextFieldWrapper("Wrong:");
		JTextField percentLabel = new JTextFieldWrapper("Percentage: ");
		correctField = new JTextFieldWrapper();
		wrongField = new JTextFieldWrapper();
		percentField = new JTextFieldWrapper();

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 5;
		c.ipady = 5;
		c.insets = new Insets(5,2, 10, 2);
		c.weightx = 0.1f;
		c.weighty = 0.2f;
		results.add(algorithmLabel, c);

		c.gridx++;
		results.add(algorithmField, c);

		c.gridx++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		results.add(closeResultsButtonTop, c);

		c.gridx = 0;
		c.gridy++;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weighty = 0.05f;
		results.add(correctLabel, c);

		c.gridx++;
		results.add(wrongLabel, c);

		c.gridx++;
		results.add(percentLabel, c);

		c.gridx = 0;
		c.gridy++;
		c.anchor = GridBagConstraints.PAGE_START;
		results.add(correctField, c);

		c.gridx++;
		results.add(wrongField, c);

		c.gridx++;
		results.add(percentField, c);

		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(30, 1, 1, 1);
		c.weighty = 0.6f;
		results.add(new MyCustomScrollPane(classDetailsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), c);

		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(1, 1, 10, 1);
		c.weighty = 0.1f;
		results.add(closeResultsButtonBottom, c);
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
		data = getData(dataChooser);
	}

	private Dataset getData(JFileChooser chooser){
		Dataset dataset = null;
		File file = chooser.getSelectedFile();
		if (file == null) {
			return null;
		}
		try {
			dataset = MyFileHandler.loadDataset(file, 8, ","); //For abalone testing data from 'UCI-small'
			System.out.println("Loading: " + file);
			lastDataSize = MyFileHandler.lastDataSize;
		} catch (IOException e) {
			System.out.println("Couldn't load the file");
		}

		return dataset;
	}

	private void makeResultFields(int correct, Map<Object, PerformanceMeasure> map){
		classDetailsPanel.removeAll();
		algorithmField.setText(classifier.getClass().getSimpleName());
		correctField.setText(Integer.toString(correct));
		wrongField.setText(Integer.toString(lastDataSize-correct));
		double tmp = ((double)correct / (double)lastDataSize) * 100;
		double percentage = BigDecimal.valueOf(tmp).setScale(4, RoundingMode.HALF_UP).doubleValue();
		percentField.setText(percentage + "%");

		TreeMap<Object, PerformanceMeasure> sortedMap = new TreeMap<>((Object o1, Object o2) -> {
			String s1 = "", s2 = "";
			if (o1 instanceof String && o2 instanceof String){
				s1 = (String) o1;
				s2 = (String) o2;
			}
			if (s1.length() > s2.length()) return 1;
			else if (s2.length() > s1.length()) return -1;
			else return s1.compareTo(s2);
		});
		sortedMap.putAll(map);

		JPanel header = new JPanel();
		header.setLayout(new GridLayout(1, 6));
		header.add(new JTextFieldWrapper("Class label"));

		JTextField acc = new JTextFieldWrapper("Accuracy");
		acc.setToolTipText("(TP + TN) / total");
		header.add(acc);

		header.add(new JTextFieldWrapper("Error"));

		JTextField stats = new JTextFieldWrapper("TP/FP/TN/FN");
		stats.setToolTipText("TP - True positive, FP - False positive, TN - True negative, FN - False negative");
		header.add(stats);

		header.add(new JTextFieldWrapper("Correlation"));

		JTextField corrCoef = new JTextFieldWrapper("Corr Coef");
		corrCoef.setToolTipText("Correlation Coefficient");
		header.add(corrCoef);

		classDetailsPanel.add(header);
		for (Object classLabel : sortedMap.keySet()){
			classDetailsPanel.add(new ClassLabel(classLabel, sortedMap.get(classLabel)));
			classDetailsPanel.add(Box.createRigidArea(new Dimension(10, 5)));

			System.out.println(classLabel + ": " + sortedMap.get(classLabel).getAccuracy());
		}
	}

	private void showResults(){
		this.removeAll();
		this.add(results);
		this.getTopLevelAncestor().setSize(new Dimension(825, 700));
		refresh();
	}

	private void closeResults(){
		this.removeAll();
		this.add(classifierPanel);
		this.getTopLevelAncestor().setSize(new Dimension(400, 500));
		refresh();
	}

	private void refresh(){
		this.revalidate();
		this.repaint();
	}

	private class FileChooser extends JPanel{
		private File selectedFile;

		public FileChooser(JFileChooser chooser){
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			JTextField fileName = new JTextField("No files attached...");
			fileName.setFont(FILE_ATTACHED_FONT);
			fileName.setOpaque(false);
			fileName.setEditable(false);
			fileName.setBorder(BorderFactory.createEmptyBorder());
			JButton attachButton = new JButton("Choose a file...");

			attachButton.addActionListener(e -> {
				chooser.showOpenDialog(this.getParent());
				String fileNameString = chooser.getSelectedFile().getName();
				if (!fileNameString.equals("")) {
					fileName.setText(fileNameString);
					selectedFile = chooser.getSelectedFile();
				}
				else {
					chooser.setSelectedFile(null);
					selectedFile = null;
				}
			});

			this.add(attachButton);
			this.add(Box.createRigidArea(new Dimension(10, 10)));
			this.add(fileName);
		}

		public File getSelectedFile() {
			return selectedFile;
		}
	}

	private class ClassLabel extends JPanel{

		public ClassLabel(Object o, PerformanceMeasure pm){
			this.setLayout(new GridLayout(1, 6));
			this.setPreferredSize(new Dimension(classDetailsPanel.getWidth(), CLASS_LABEL_HEIGHT));

			GridBagConstraints c = new GridBagConstraints(0,
					0,
					1,
					1,
					0.1f,
					0.1f,
					GridBagConstraints.LINE_START,
					GridBagConstraints.NONE,
					new Insets(0, 2, 0, 2),
					2,
					2);

			//Class label
			this.add(new JTextFieldWrapper(o.toString()), c);

			//Accuracy
			double acc = BigDecimal.valueOf(pm.getAccuracy()*100)
					.setScale(4, RoundingMode.HALF_UP)
					.doubleValue();
			this.add(new JTextFieldWrapper(acc + "%"));

			//Error
			double error = BigDecimal.valueOf(pm.getErrorRate()*100)
					.setScale(4, RoundingMode.HALF_UP)
					.doubleValue();
			JTextField errorField = new JTextFieldWrapper(error + "%");
//			errorField.setToolTipText("(FP + FN) / total");
			this.add(errorField);

			//Stats (tp / fp / tn / fn)
			JTextField stats = new JTextFieldWrapper((int)pm.tp + "/" + (int)pm.fp + "/" + (int)pm.tn + "/" + (int)pm.fn);
			this.add(stats);

			//Correlation
			double corr;
			if (Double.isNaN(pm.getCorrelation()))
				corr = 0.0;
			else
			 	corr = BigDecimal.valueOf(pm.getCorrelation())
					.setScale(4, RoundingMode.HALF_UP)
					.doubleValue();
			this.add(new JTextFieldWrapper(corr));

			//Correlation Coefficient
			double corrCoef;
			if (Double.isNaN(pm.getCorrelationCoefficient()))
				corrCoef = 0.0;
			else
				corrCoef = BigDecimal.valueOf(pm.getCorrelationCoefficient())
					.setScale(4, RoundingMode.HALF_UP)
					.doubleValue();
			this.add(new JTextFieldWrapper(corrCoef));
		}
	}

	private class JTextFieldWrapper extends JTextField{

		public JTextFieldWrapper(String s){
			super(s);
			this.setEditable(false);
			this.setBorder(BorderFactory.createEmptyBorder());
			this.setOpaque(false);
			this.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setFont(RESULT_FIELD_FONT);
		}

		public JTextFieldWrapper(){
			this("");
		}

		public JTextFieldWrapper(Font f){
			this();
			this.setFont(f);
		}

		public JTextFieldWrapper(String s, Font f){
			this(s);
			this.setFont(f);
		}

		public JTextFieldWrapper(double d){
			this(Double.toString(d));
		}

		public JTextFieldWrapper(int i){
			this(Integer.toString(i));
		}
	}
}
