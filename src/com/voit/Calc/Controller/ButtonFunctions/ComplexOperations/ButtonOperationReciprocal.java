package com.voit.Calc.Controller.ButtonFunctions.ComplexOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationReciprocal  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationReciprocal(ModelInterface model){
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
