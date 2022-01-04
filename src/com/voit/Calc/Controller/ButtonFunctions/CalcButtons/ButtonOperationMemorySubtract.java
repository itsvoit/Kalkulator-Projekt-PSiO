package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
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
