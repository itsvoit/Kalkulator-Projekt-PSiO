package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationAdd implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationAdd(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.add();
	}

	@Override
	public String getString() {
		return "+";
	}
}
