package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationSubtract implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationSubtract(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.subtract();
	}

	@Override
	public String getString() {
		return "-";
	}
}
