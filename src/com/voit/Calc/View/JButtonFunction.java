package com.voit.Calc.View;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonFunction extends JButton implements ActionListener {
	ButtonFunctionInterface button;

	JButtonFunction(ButtonFunctionInterface button){
		super(button.getString());
		this.button = button;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		button.onClick();
	}
}
