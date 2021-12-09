package com.voit.Calc.Controller.ButtonFunctions.Memory;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonMemorySubtract implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonMemorySubtract(ModelInterface model){
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
