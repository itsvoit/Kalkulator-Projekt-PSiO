package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationEquals implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationEquals(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.equals();
	}

	@Override
	public String getString() {
		return "=";
	}
}
