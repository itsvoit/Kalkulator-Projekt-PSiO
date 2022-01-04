package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationEquals implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationEquals(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.equals();
	}

	@Override
	public String getString() {
		return "=";
	}
}
