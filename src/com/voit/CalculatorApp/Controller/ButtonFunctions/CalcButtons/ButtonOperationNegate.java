package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationNegate implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationNegate(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.negate();
	}

	@Override
	public String getString() {
		return "+/-";
	}
}
