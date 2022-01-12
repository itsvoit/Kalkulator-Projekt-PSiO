package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

import javax.swing.*;
import java.awt.*;

public class SimpleCalcJPanel extends JPanel {

	private final int H_GAP = 5;
	private final int V_GAP = 5;
	private final int MAX_MEM_H = 50;

	SimpleCalcJPanel(CalcModelInterface model){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		makeMemoryButtons(model);
		makeFunctionButtons(model);
	}

	private void makeMemoryButtons(CalcModelInterface model){
		JPanel memoryPanel = new JPanel(new GridLayout(1, 5, H_GAP, V_GAP));

		memoryPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, MAX_MEM_H));

		//start with the lowest memory related number and loop through all memory related operations
		for (int i = FunctionJButton.MEMORY_CLEAR; i <= FunctionJButton.MEMORY_WRITE; i++) {
			memoryPanel.add(new FunctionJButton(i, model));
		}

		this.add(memoryPanel);
		this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
	}

	private void makeFunctionButtons(CalcModelInterface model){
		JPanel buttonsPanel = new JPanel(new GridLayout(5, 5, H_GAP, V_GAP));

		buttonsPanel.add(new FunctionJButton(FunctionJButton.RECIPROCAL, model)); //Reciprocal
		buttonsPanel.add(new FunctionJButton(FunctionJButton.PERCENT, model)); //Percent
		buttonsPanel.add(new FunctionJButton(FunctionJButton.CLEAR_ALL, model)); //Clear all
		buttonsPanel.add(new FunctionJButton(FunctionJButton.CLEAR, model)); //Clear
		buttonsPanel.add(new FunctionJButton(FunctionJButton.DIVIDE, model)); //Divide
		buttonsPanel.add(new FunctionJButton(FunctionJButton.POWER2, model)); //2nd Power

		for (int i=1; i<=3; i++) buttonsPanel.add(new FunctionJButton(FunctionJButton.NUMBER, i, model)); //number 1 - 3

		buttonsPanel.add(new FunctionJButton(FunctionJButton.MULTIPLY, model)); // Multiply
		buttonsPanel.add(new FunctionJButton(FunctionJButton.SQRT, model)); // Square root

		for (int i=4; i<=6; i++) buttonsPanel.add(new FunctionJButton(FunctionJButton.NUMBER, i, model)); //numbers 3 - 6

		buttonsPanel.add(new FunctionJButton(FunctionJButton.SUBTRACT, model)); //Subtract
		buttonsPanel.add(new FunctionJButton(FunctionJButton.POWER, model)); //Power

		for (int i=7; i<=9; i++) buttonsPanel.add(new FunctionJButton(FunctionJButton.NUMBER, i, model)); //numbers 7 - 9

		buttonsPanel.add(new FunctionJButton(FunctionJButton.ADD, model)); //Add
		buttonsPanel.add(new FunctionJButton(FunctionJButton.LOG, model)); //Log base 10
		buttonsPanel.add(new FunctionJButton(FunctionJButton.NEGATE, model)); //Negate

		buttonsPanel.add(new FunctionJButton(FunctionJButton.NUMBER, 0, model)); //number 0

		buttonsPanel.add(new FunctionJButton(FunctionJButton.COMMA, model)); //Comma - start fraction
		buttonsPanel.add(new FunctionJButton(FunctionJButton.EQUALS, model)); //Equals

		this.add(buttonsPanel);
	}
}
