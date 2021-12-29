package com.voit.Calc.View;

import com.voit.Calc.Controller.ControllerInterface;
import com.voit.Calc.Model.Model;
import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelOnDouble;

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
	private final int FRAME_X = 500;
	private final int FRAME_Y = 600;
	private String aboutMessage;

	private final int DEFAULT_CALC = 0;
	private final int DEFAULT_MATRIX = 1;
	private final int DEFAULT_GRAPH = 2;

	//Fields
	private ControllerInterface controller;

	private JFrame mainFrame;

	private JPanel calcPanel;
	private JPanel calcViewPanel;
	private JPanel buttonsPanel;

	private JPanel matrixPanel;

	private JPanel graphPanel;

	private JPanel aboutPanel;

	private JPanel defaultView;
	private String defaultTitle;

	private JMenuBar mainMenuBar;
	private JMenu menuFile;
	private JMenuItem fileCalc;
	private JMenuItem fileGraph;
	private JMenuItem fileExit;
	private JMenuItem menuAbout;

	private WindowListener restoreViewWindowListener;
	private WindowListener closeApp;

	//use model to register observers
	public View(ControllerInterface controller, ModelInterface model){
		this.controller = controller;

		makeCalcPanel(model);
		makeMatrixPanel(model);
		makeGraphPanel(model);
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

		setDefaultFrameValues();

		makeMenuBar();
	}

	public void showFrame(){
		mainFrame.setVisible(true);
	}

	public void showCalc(){
		if (mainFrame == null || calcPanel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(calcPanel);
		mainFrame.setTitle("Calculator");
		setDefaultView(calcPanel, "Calculator");
		setDefaultFrameValues();
		refresh();
	}

	public void showGraph(){
		if (mainFrame == null || graphPanel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(graphPanel);
		mainFrame.setTitle("Graph");
		setDefaultView(graphPanel, "Graph");
		setDefaultFrameValues();
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
		setDefaultFrameValues();
	}

	private void showPanel(JPanel panel, String title){
		if (mainFrame == null || panel == null) return;

		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(panel);
		mainFrame.setTitle(title);
		refresh();
	}

	private void makeCalcPanel(ModelInterface model){
		calcPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		calcPanel.setLayout(new GridBagLayout());

		if (model instanceof Model)
			calcViewPanel = new ViewJPanel(model);
		else if (model instanceof ModelOnDouble)
			calcViewPanel = new ViewJPanelOnDouble(model);
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

	private void makeMatrixPanel(ModelInterface model){
		matrixPanel = new MatrixJPanel(model);
	}

	private void makeGraphPanel(ModelInterface model){
		//todo create graph panel
		graphPanel = new JPanel();
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
		fileGraph = new JMenuItem("Graph");
		fileExit = new JMenuItem("Exit");

		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuAbout);

		menuFile.add(fileCalc);
		menuFile.add(fileGraph);
		menuFile.add(fileExit);

		menuAbout.addActionListener(e -> showAbout());

		fileCalc.addActionListener(e -> showCalc());
		fileGraph.addActionListener(e -> showGraph());
		fileExit.addActionListener(e -> System.exit(0));
	}

	private String getAboutMessage(){
		String message = "";

		return message;
	}

	private void setDefaultFrameValues(){
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setSize(new Dimension(FRAME_X, FRAME_Y));
		mainFrame.setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
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
			case DEFAULT_GRAPH:
				setDefaultView(graphPanel, "Graphs");
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
