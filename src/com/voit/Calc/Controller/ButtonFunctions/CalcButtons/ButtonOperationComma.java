package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationComma implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationComma(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.comma();
	}

	@Override
	public String getString() {
		return ",";
	}
}
