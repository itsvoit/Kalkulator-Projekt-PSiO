package com.voit.Calc.View;

import com.voit.Calc.Controller.ButtonFunctions.ButtonNumber;
import com.voit.Calc.Controller.ControllerInterface;
import com.voit.Calc.Model.ModelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
	//Constants
	private final String APP_NAME = "Calculator";
	private final int FRAME_X = 400;
	private final int FRAME_Y = 400;

	private ControllerInterface controller;

	JFrame mainFrame;
	JPanel mainPanel;

	JMenuBar mainMenuBar;
	JMenu menuFile;
	JMenuItem menuExit;
	JMenuItem menuAbout;

	JButtonFunction button1;



	//get model to register observers
	public View(ControllerInterface controller, ModelInterface model){
		this.controller = controller;

		button1 = new JButtonFunction(new ButtonNumber(1, model));

		//register observers here \/ \/ \/ \/ //todo register observers
	}

	public void makeGUI(){
		mainFrame = new JFrame(APP_NAME);
		mainPanel = new JPanel();
		mainFrame.getContentPane().add(mainPanel);

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(FRAME_X, FRAME_Y));
		mainFrame.setMinimumSize(new Dimension(FRAME_X, FRAME_Y));
		mainFrame.setResizable(true);

		mainPanel.add(button1);

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
