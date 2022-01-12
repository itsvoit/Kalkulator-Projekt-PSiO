package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationReciprocal  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationReciprocal(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.reciprocal();
	}

	@Override
	public String getString() {
		return "1/x";
	}
}
