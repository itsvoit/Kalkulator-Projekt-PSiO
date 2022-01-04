package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationMemoryClear implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonOperationMemoryClear(ModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.memoryClear();
	}

	@Override
	public String getString() {
		return "MC";
	}
}
