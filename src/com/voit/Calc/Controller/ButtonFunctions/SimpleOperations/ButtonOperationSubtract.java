package com.voit.Calc.Controller.ButtonFunctions.SimpleOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationSubtract implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationSubtract(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.();
	}

	@Override
	public String getString() {
		return "-";
	}
}
