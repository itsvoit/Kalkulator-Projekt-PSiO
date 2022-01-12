package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationNegate implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationNegate(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.negate();
	}

	@Override
	public String getString() {
		return "+/-";
	}
}
