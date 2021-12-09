package com.voit.Calc.Controller.ButtonFunctions.Memory;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonMemoryWrite implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonMemoryWrite(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryWrite();
	}

	@Override
	public String getString() {
		return "MS";
	}
}
