package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionJButton extends JButton implements ActionListener {
	private ButtonFunctionInterface buttonFunction;

	public FunctionJButton(){
		this.addActionListener(this);
	}

	public FunctionJButton(ButtonFunctionInterface buttonFunction){
		this();
		this.buttonFunction = buttonFunction;
		super.setText(buttonFunction.getString());
	}

	public void setButtonFunction(ButtonFunctionInterface buttonFunction) {
		this.buttonFunction = buttonFunction;
		super.setText(buttonFunction.getString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonFunction.onClick();
	}
}
