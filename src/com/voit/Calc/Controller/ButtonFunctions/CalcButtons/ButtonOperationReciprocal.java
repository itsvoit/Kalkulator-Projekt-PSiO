package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationReciprocal  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationReciprocal(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.reciprocal();
	}

	@Override
	public String getString() {
		return "1/x";
	}
}
