package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationMultiply  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationMultiply(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.multiply();
	}

	@Override
	public String getString() {
		return "x";
	}
}
