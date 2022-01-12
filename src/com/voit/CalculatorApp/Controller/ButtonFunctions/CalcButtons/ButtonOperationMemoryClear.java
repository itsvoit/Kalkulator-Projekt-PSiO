package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationMemoryClear implements ButtonFunctionInterface {
	private CalcModelInterface model;

	public ButtonOperationMemoryClear(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryClear();
	}

	@Override
	public String getString() {
		return "MC";
	}
}
