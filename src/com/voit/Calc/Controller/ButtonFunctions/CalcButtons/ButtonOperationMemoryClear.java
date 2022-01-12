package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

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
