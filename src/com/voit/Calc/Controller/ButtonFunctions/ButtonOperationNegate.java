package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationNegate implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationNegate(ModelInterface model){
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
