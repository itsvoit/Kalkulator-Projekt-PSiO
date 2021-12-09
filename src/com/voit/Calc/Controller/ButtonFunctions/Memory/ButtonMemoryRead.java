package com.voit.Calc.Controller.ButtonFunctions.Memory;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonMemoryRead implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonMemoryRead(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryRead();
	}

	@Override
	public String getString() {
		return "MR";
	}
}
