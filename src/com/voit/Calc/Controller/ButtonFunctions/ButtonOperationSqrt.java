package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationSqrt  implements ButtonFunctionInterface {
	ModelInterface model;

	public ButtonOperationSqrt(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.sqrt();
	}

	@Override
	public String getString() {
		return "sqrt(x)";
	}
}
