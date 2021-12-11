package com.voit.Calc.View;

import com.voit.Calc.Controller.ControllerInterface;
import com.voit.Calc.Model.Model;
import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelOnDouble;

import javax.swing.*;
import java.awt.*;

public class View {
	//Constants
	private final String APP_NAME = "Calculator";
	private final int FRAME_X = 500;
	private final int FRAME_Y = 600;

	//Fields
	private ControllerInterface controller;

	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel viewPanel;
	private JPanel buttonsPanel;

	private JMenuBar mainMenuBar;
	private JMenu menuFile;
	private JMenuItem menuExit;
	private JMenuItem menuAbout;


	//use model to register observers
	public View(ControllerInterface controller, ModelInterface model){
		this.controller = controller;

		createMainPanel(model);
	}

	private void createMainPanel(ModelInterface model){
		mainPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.setLayout(new GridBagLayout());

		if (model instanceof Model)
			viewPanel = new ViewJPanel(model);
		else if (model instanceof ModelOnDouble)
			viewPanel = new ViewJPanelOnDouble(model);
		else
			viewPanel = new JPanel(); //if none exist, create empty panel so the app doesn't crash

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
		mainPanel.add(viewPanel, c);

		c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.3;
		mainPanel.add(buttonsPanel, c);
	}

	public void makeGUI(){
		mainFrame = new JFrame(APP_NAME);
		mainFrame.getContentPane().add(mainPanel);

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(FRAME_X, FRAME_Y));
		mainFrame.setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
		mainFrame.setResizable(true);

		makeMenu();
	}

	public void showGUI(){
		mainFrame.setVisible(true);
	}

	private void makeMenu(){
		mainMenuBar = new JMenuBar();
		mainFrame.setJMenuBar(mainMenuBar);

		menuFile = new JMenu("File");
		menuAbout = new JMenuItem("About");
		menuExit = new JMenuItem("Exit");

		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuAbout);

		menuFile.add(menuExit);

		menuAbout.addActionListener(e -> {
			showAbout();
		});

		menuExit.addActionListener(e -> {
			System.exit(0);
		});
	}

	private void showAbout(){
		//todo create new window with about info
		System.out.println("About");
	}

}
