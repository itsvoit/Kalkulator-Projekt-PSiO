package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionJButton extends JButton implements ActionListener {
	ButtonFunctionInterface buttonFunction;

	public FunctionJButton(ButtonFunctionInterface buttonFunction){
		super(buttonFunction.getString());
		this.buttonFunction = buttonFunction;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonFunction.onClick();
	}
}
