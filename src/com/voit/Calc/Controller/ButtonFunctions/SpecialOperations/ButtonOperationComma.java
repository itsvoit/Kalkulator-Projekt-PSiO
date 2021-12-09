package com.voit.Calc.Controller.ButtonFunctions.SpecialOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationComma implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationComma(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.comma();
	}

	@Override
	public String getString() {
		return ",";
	}
}