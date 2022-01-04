package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMemoryAdd implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonOperationMemoryAdd(ModelInterface model){
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
