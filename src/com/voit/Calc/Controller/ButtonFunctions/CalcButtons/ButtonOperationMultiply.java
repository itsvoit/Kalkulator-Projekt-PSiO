package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMultiply  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationMultiply(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.multiply();
	}

	@Override
	public String getString() {
		return "x";
	}
}
