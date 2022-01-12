package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationEquals implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationEquals(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.equals();
	}

	@Override
	public String getString() {
		return "=";
	}
}
