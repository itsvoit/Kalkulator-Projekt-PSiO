package com.voit.Calc.Controller.ButtonFunctions.SpecialOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationClear implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationClear(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.clear();
	}

	@Override
	public String getString() {
		return "C";
	}
}
