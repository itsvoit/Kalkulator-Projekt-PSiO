package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationDeductNumber implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationDeductNumber(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.deductNumber();
	}

	@Override
	public String getString() {
		return "<---";
	}
}
