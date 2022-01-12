package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMemoryWrite implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonOperationMemoryWrite(ModelInterface model){
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