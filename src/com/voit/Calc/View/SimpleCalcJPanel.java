package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.View.Buttons.*;

import javax.swing.*;
import java.awt.*;

public class SimpleCalcJPanel extends JPanel {

	private final int H_GAP = 5;
	private final int V_GAP = 5;
	private final int MAX_MEM_H = 50;

	SimpleCalcJPanel(ModelInterface model){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		makeMemoryButtons(model);
		makeFunctionButtons(model);
	}

	private void makeMemoryButtons(ModelInterface model){
		JPanel memoryPanel = new JPanel(new GridLayout(1, 5, H_GAP, V_GAP));

		memoryPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, MAX_MEM_H));

		for (int i = MemoryJButton.CLEAR; i <= MemoryJButton.WRITE; i++) {
			memoryPanel.add(new MemoryJButton(model, i));
		}

		this.add(memoryPanel);
		this.add(Box.createRigidArea(new Dimension(H_GAP, V_GAP)));
	}

	private void makeFunctionButtons(ModelInterface model){
		JPanel buttonsPanel = new JPanel(new GridLayout(5, 5, H_GAP, V_GAP));

		buttonsPanel.add(new ComplexOperationJButton(ComplexOperationJButton.RECIPROCAL, model)); //Reciprocal
		buttonsPanel.add(new ComplexOperationJButton(ComplexOperationJButton.PERCENT, model)); //Percent
		buttonsPanel.add(new SpecialOperationJButton(SpecialOperationJButton.CLEAR_ALL, model)); //Clear all
		buttonsPanel.add(new SpecialOperationJButton(SpecialOperationJButton.CLEAR, model)); //Clear
		buttonsPanel.add(new SimpleOperationJButton(SimpleOperationJButton.DIVIDE, model)); //Divide
		buttonsPanel.add(new ComplexOperationJButton(ComplexOperationJButton.POWER2, model)); //2nd Power

		for (int i=1; i<=3; i++) buttonsPanel.add(new NumberJButton(i, model)); //number 1 - 3

		buttonsPanel.add(new SimpleOperationJButton(SimpleOperationJButton.MULTIPLY, model)); // Multiply
		buttonsPanel.add(new ComplexOperationJButton(ComplexOperationJButton.SQRT, model)); // Square root

		for (int i=4; i<=6; i++) buttonsPanel.add(new NumberJButton(i, model)); //numbers 3 - 6

		buttonsPanel.add(new SimpleOperationJButton(SimpleOperationJButton.SUBTRACT, model)); //Subtract
		buttonsPanel.add(new ComplexOperationJButton(ComplexOperationJButton.POWER, model)); //Power

		for (int i=7; i<=9; i++) buttonsPanel.add(new NumberJButton(i, model)); //numbers 7 - 9

		buttonsPanel.add(new SimpleOperationJButton(SimpleOperationJButton.ADD, model)); //Add
		buttonsPanel.add(new ComplexOperationJButton(ComplexOperationJButton.LOG, model)); //Log base 10
		buttonsPanel.add(new SimpleOperationJButton(SimpleOperationJButton.NEGATE, model)); //Negate

		buttonsPanel.add(new NumberJButton(0, model));

		buttonsPanel.add(new SpecialOperationJButton(SpecialOperationJButton.COMMA, model)); //Comma - start fraction
		buttonsPanel.add(new SpecialOperationJButton(SpecialOperationJButton.EQUALS, model)); //Equals

		this.add(buttonsPanel);
	}
}
