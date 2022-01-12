package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationDivide  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationDivide(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.divide();
	}

	@Override
	public String getString() {
		return "/";
	}
}
