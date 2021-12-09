package com.voit.Calc.Controller.ButtonFunctions.SimpleOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationAdd implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationAdd(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.add();
	}

	@Override
	public String getString() {
		return "+";
	}
}
