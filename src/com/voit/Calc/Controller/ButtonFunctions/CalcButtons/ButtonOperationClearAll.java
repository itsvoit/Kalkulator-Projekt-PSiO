package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationClearAll implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationClearAll(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.clearAll();
	}

	@Override
	public String getString() {
		return "CE";
	}
}
