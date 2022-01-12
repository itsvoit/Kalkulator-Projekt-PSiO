package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationMemoryRead implements ButtonFunctionInterface {
	private CalcModelInterface model;

	public ButtonOperationMemoryRead(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryRead();
	}

	@Override
	public String getString() {
		return "MR";
	}
}
