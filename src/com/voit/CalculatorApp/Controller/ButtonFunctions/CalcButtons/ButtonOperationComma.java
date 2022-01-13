package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationComma implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationComma(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.comma();
	}

	@Override
	public String getString() {
		return ",";
	}
}
