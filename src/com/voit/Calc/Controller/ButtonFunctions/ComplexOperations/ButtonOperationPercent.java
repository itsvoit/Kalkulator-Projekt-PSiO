package com.voit.Calc.Controller.ButtonFunctions.ComplexOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationPercent  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationPercent(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.percent();
	}

	@Override
	public String getString() {
		return "%";
	}
}
