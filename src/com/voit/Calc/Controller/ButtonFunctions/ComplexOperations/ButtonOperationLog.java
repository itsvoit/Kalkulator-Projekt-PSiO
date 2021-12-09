package com.voit.Calc.Controller.ButtonFunctions.ComplexOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationLog  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationLog(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.log();
	}

	@Override
	public String getString() {
		return "log10(x)";
	}
}
