package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationMemoryWrite implements ButtonFunctionInterface {
	private CalcModelInterface model;

	public ButtonOperationMemoryWrite(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryWrite();
	}

	@Override
	public String getString() {
		return "MS";
	}
}
