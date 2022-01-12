package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationSqrt  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationSqrt(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.sqrt();
	}

	@Override
	public String getString() {
		return "sqrt(x)";
	}
}
