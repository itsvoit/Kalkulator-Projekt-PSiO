package com.voit.Calc.Controller.ButtonFunctions.Memory;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonMemoryAdd implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonMemoryAdd(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryAdd();
	}

	@Override
	public String getString() {
		return "M+";
	}
}
