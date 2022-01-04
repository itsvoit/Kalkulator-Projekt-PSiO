package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMemoryRead implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonOperationMemoryRead(ModelInterface model){
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
