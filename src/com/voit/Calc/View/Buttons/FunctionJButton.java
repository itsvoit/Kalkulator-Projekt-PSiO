package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @implNote Extend this class to make a functional button with loose ties to
 * model extending ModelInterface.
 *
 * @implSpec Include correct strategy implementing ButtonFunctionInterface
 *
 * @see com.voit.Calc.Model.ModelInterface
 */
public abstract class FunctionJButton extends JButton implements ActionListener {
	private ButtonFunctionInterface buttonFunction;

	public FunctionJButton(){
		this.addActionListener(this);
		this.setFont(new Font("TimesRoman", Font.BOLD, 18));
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
		if (buttonFunction == null) return;

		buttonFunction.onClick();
	}
}
