package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationClear implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationClear(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.clear();
	}

	@Override
	public String getString() {
		return "C";
	}
}
