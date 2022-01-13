package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationMemorySubtract implements ButtonFunctionInterface {
	private CalcModelInterface model;

	public ButtonOperationMemorySubtract(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memorySubtract();
	}

	@Override
	public String getString() {
		return "M-";
	}
}
