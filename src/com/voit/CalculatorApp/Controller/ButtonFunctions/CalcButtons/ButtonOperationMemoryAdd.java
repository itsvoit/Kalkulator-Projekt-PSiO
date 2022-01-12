package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationMemoryAdd implements ButtonFunctionInterface {
	private CalcModelInterface model;

	public ButtonOperationMemoryAdd(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryAdd();
	}

	@Override
	public String getString() {
		return "M+";
	}
}
