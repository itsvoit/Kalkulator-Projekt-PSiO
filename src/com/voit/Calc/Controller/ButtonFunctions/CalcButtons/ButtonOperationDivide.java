package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationDivide  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationDivide(ModelInterface model){
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