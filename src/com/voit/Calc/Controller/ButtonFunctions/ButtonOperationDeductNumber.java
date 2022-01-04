package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationDeductNumber implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationDeductNumber(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.deductNumber();
	}

	@Override
	public String getString() {
		return "<---";
	}
}
