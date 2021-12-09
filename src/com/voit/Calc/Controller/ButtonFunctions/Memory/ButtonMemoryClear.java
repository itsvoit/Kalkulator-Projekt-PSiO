package com.voit.Calc.Controller.ButtonFunctions.Memory;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonMemoryClear implements ButtonFunctionInterface {
	private ModelInterface model;

	public ButtonMemoryClear(ModelInterface model){
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
