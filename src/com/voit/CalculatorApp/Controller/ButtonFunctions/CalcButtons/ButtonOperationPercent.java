package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationPercent  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationPercent(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.percent();
	}

	@Override
	public String getString() {
		return "%";
	}
}
