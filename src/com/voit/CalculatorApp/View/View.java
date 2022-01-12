package com.voit.CalculatorApp.View;

import com.voit.CalculatorApp.Controller.Controller;
import com.voit.CalculatorApp.Controller.ControllerInterfaces.MatrixControllerInterface;
import com.voit.CalculatorApp.Model.Model;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.ClassifModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.MatrixModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.ModelInterface;
import com.voit.CalculatorApp.View.Customs.MyCustomScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class View {
	//Constants
	private final String INI_FILE = "view_ini.txt";
	private final int INI_LINES = 1;
	private final String APP_NAME = "Calculator";
	private final int FRAME_X_CALC = 500;
	private final int FRAME_Y_CALC = 600;
	private final int FRAME_X_MATRIX = 600;
	private final int FRAME_Y_MATRIX = 450;
	private final int FRAME_X_CLASSIF = 800;
	private final int FRAME_Y_CLASSIF = 1000;
	private String aboutMessage;

	private final int DEFAULT_CALC = 0;
	private final int DEFAULT_MATRIX = 1;
	private final int DEFAULT_CLASSIF = 2;

	//Fields
	private Controller controller;

	private JFrame mainFrame;
	private int defaultFrameSizeX;
	private int defaultFrameSizeY;
	private String defaultTitle;
	private JPanel defaultView;

	//Calculator
	private JPanel calcPanel;
	private JPanel calcViewPanel;
	private JPanel buttonsPanel;

	//Matrix
	private JPanel matrixPanel;

	//Data Classification
	private JPanel classiffPanel;

	//About
	private JPanel aboutPanel;

	//Menu
	private JMenuBar mainMenuBar;
	private JMenu menuFile;
	private JMenuItem fileCalc;
	private JMenuItem fileClassif;
	private JMenuItem fileMatrix;
	private JMenuItem fileExit;
	private JMenuItem menuAbout;

	//Listeners for about
	private WindowListener restoreViewWindowListener;
	private WindowListener closeApp;

	//use model to register observers
	public View(Controller controller, ModelInterface model){
		this.controller = controller;

		makeCalcPanel((CalcModelInterface) model);
		makeMatrixPanel((MatrixModelInterface)model);
		makeClassifPanel((ClassifModelInterface)model);
		makeAboutPanel();

		closeApp = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		restoreViewWindowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				showDefaultView();
			}
		};
	}

	public void makeGUI(){
		mainFrame = new JFrame(APP_NAME);
		showCalc(); //on default show calc; todo make app remember last used view and show it instead
		defaultView = calcPanel;
		defaultFrameSizeX = FRAME_X_CALC;
		defaultFrameSizeY = FRAME_Y_CALC;
		defaultTitle = "Calculator";

		setDefaultFrameValues(defaultFrameSizeX, defaultFrameSizeY);

		makeMenuBar();
	}

	public void showFrame(){
		mainFrame.setVisible(true);
	}

	public void showCalc(){
		if (mainFrame == null || calcPanel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(calcPanel);
		setDefaultView(calcPanel, "Calculator");
		setDefaultFrameValues(FRAME_X_CALC, FRAME_Y_CALC);
		refresh();
	}

	public void showClassification(){
		if (mainFrame == null || classiffPanel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(new MyCustomScrollPane(classiffPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		mainFrame.setTitle("Classification");
		setDefaultView(classiffPanel, "Classification");
		setDefaultFrameValues(FRAME_X_CLASSIF, FRAME_Y_CLASSIF);
		refresh();
	}

	public void showMatrix(){
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(matrixPanel);
		mainFrame.setTitle("Matrix Calculator");
		setDefaultView(matrixPanel, "Matrix Calculator");
		setDefaultFrameValues(FRAME_X_MATRIX, FRAME_Y_MATRIX);
		refresh();
	}

	public void showAbout(){
		if (mainFrame == null || aboutPanel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(aboutPanel);
		mainFrame.setTitle("About");
		mainFrame.removeWindowListener(closeApp);
		mainFrame.addWindowListener(restoreViewWindowListener);
		refresh();
	}

	public void showDefaultView(){
		showPanel(defaultView, defaultTitle);
		setDefaultFrameValues(defaultFrameSizeX, defaultFrameSizeY);
	}

	private void showPanel(JPanel panel, String title){
		if (mainFrame == null || panel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(panel);
		mainFrame.setTitle(title);
		refresh();
	}

	private void makeCalcPanel(CalcModelInterface model){
		calcPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		calcPanel.setLayout(new GridBagLayout());

		if (model instanceof Model)
			calcViewPanel = new CalcViewJPanelOnDouble(model);
		else
			calcViewPanel = new JPanel(); //if none exist, create empty panel so the app doesn't crash

		buttonsPanel = new SimpleCalcJPanel(model);

		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.1;
		calcPanel.add(calcViewPanel, c);

		c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.3;
		calcPanel.add(buttonsPanel, c);
	}

	private void makeMatrixPanel(MatrixModelInterface model){
		matrixPanel = new MatricesJPanel(model, (MatrixControllerInterface) controller);

	}

	private void makeClassifPanel(ClassifModelInterface model){
		//todo create classification panel
		classiffPanel = new ClassificationJPanel(controller);

	}

	private void makeAboutPanel(){
		//todo create new window with about info
		aboutPanel = new JPanel();
		JTextArea textArea = new JTextArea(getAboutMessage());

	}

	private void makeMenuBar(){
		mainMenuBar = new JMenuBar();
		mainFrame.setJMenuBar(mainMenuBar);

		menuFile = new JMenu("File");
		menuAbout = new JMenuItem("About");
		fileCalc = new JMenuItem("Calculator");
		fileClassif = new JMenuItem("Data classification");
		fileMatrix = new JMenuItem("Matrix");
		fileExit = new JMenuItem("Exit");

		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuAbout);

		menuFile.add(fileCalc);
		menuFile.add(fileClassif);
		menuFile.add(fileMatrix);
		menuFile.add(fileExit);

		menuAbout.addActionListener(e -> showAbout());

		fileCalc.addActionListener(e -> showCalc());
		fileClassif.addActionListener(e -> showClassification());
		fileMatrix.addActionListener(e -> showMatrix());
		fileExit.addActionListener(e -> System.exit(0));
	}

	private String getAboutMessage(){
		String message = "";

		return message;
	}

	private void setDefaultFrameValues(int x, int y){
		defaultFrameSizeX = x;
		defaultFrameSizeY = y;
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(x, y));
		mainFrame.setPreferredSize(new Dimension(x, y));
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.removeWindowListener(restoreViewWindowListener);
		mainFrame.addWindowListener(closeApp);
	}

	private void setDefaultView(JPanel panel, String title){
		if (panel == null || title == null) return;
		defaultView = panel;
		defaultTitle = title;
	}

	private void setDefaultView(int option){
		switch (option){
			case DEFAULT_CALC:
				setDefaultView(calcPanel, "Calculator");
				break;
			case DEFAULT_MATRIX:
				setDefaultView(matrixPanel, "Matrix calculator");
				break;
			case DEFAULT_CLASSIF:
				setDefaultView(classiffPanel, "Data Classification");
				break;
			default:
				System.out.println("Not a valid option: " + option + "\nUsing default instead...");
				setDefaultView(0);
		}
	}

	private void loadIniValues(){
		//todo loading default view from file
		try (BufferedReader reader = new BufferedReader(new FileReader(INI_FILE))){
			for (int i = 0; i < INI_LINES; i++) {
				String line = reader.readLine();
				switch (i) {
					case 0 :
						line = line.trim();
						int x = Integer.parseInt(line);
						setDefaultView(x);
						break;

					case 1:
						//todo expand ini file
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
			System.out.println("couldn't use ini, using default values instead...");
			setDefaultView(0);
		}
	}

	private void updateIniValues(){
		//todo update ini file
	}

	private void refresh(){
		if (mainFrame == null) return;

		mainFrame.revalidate();
		mainFrame.repaint();
	}
}
