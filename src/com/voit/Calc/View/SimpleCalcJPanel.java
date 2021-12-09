package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.View.Buttons.MemoryJButton;
import com.voit.Calc.View.Buttons.NumberJButton;

import javax.swing.*;
import java.awt.*;

public class SimpleCalcJPanel extends JPanel {

	private final int H_GAP = 5;
	private final int V_GAP = 5;

	SimpleCalcJPanel(ModelInterface model){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		makeMemoryButtons(model);
		makeFunctionButtons(model);
	}

	private void makeMemoryButtons(ModelInterface model){
		JPanel memoryPanel = new JPanel(new GridLayout(1, 5, H_GAP, V_GAP));

		for (int i = MemoryJButton.CLEAR; i <= MemoryJButton.WRITE; i++) {
			memoryPanel.add(new MemoryJButton(model, i));
		}

		this.add(memoryPanel);
	}

	private void makeFunctionButtons(ModelInterface model){
		JPanel buttonsPanel = new JPanel(new GridLayout(5, 5, H_GAP, V_GAP));

		//todo swap tmp objects for correct buttons

		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		for (int i=1; i<=3; i++) buttonsPanel.add(new NumberJButton(i, model)); //number 1 - 3
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		for (int i=4; i<=6; i++) buttonsPanel.add(new NumberJButton(i, model)); //numbers 3 - 6
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		for (int i=7; i<=9; i++) buttonsPanel.add(new NumberJButton(i, model)); //numbers 7 - 9
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(new NumberJButton(0, model));
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 5))); //tmp object

		this.add(buttonsPanel);
	}
}
