package com.voit.Calc.Controller.ButtonFunctions.SpecialOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationClearAll implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationClearAll(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.clearAll();
	}

	@Override
	public String getString() {
		return "CE";
	}
}
