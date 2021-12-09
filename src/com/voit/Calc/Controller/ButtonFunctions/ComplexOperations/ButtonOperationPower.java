package com.voit.Calc.Controller.ButtonFunctions.ComplexOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationPower  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationPower(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.power();
	}

	@Override
	public String getString() {
		return "x^y";
	}
}
