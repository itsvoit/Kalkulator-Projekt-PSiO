package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMemorySubtract implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonOperationMemorySubtract(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memorySubtract();
	}

	@Override
	public String getString() {
		return "M-";
	}
}
