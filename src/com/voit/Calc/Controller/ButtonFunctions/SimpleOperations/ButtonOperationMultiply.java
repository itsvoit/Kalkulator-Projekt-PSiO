package com.voit.Calc.Controller.ButtonFunctions.SimpleOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMultiply  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationMultiply(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.();
	}

	@Override
	public String getString() {
		return "X";
	}
}
