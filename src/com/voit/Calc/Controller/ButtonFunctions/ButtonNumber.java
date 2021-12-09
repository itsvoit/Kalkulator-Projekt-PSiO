package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonNumber implements ButtonFunctionInterface{
	private int value;
	private ModelInterface model;

	public ButtonNumber(int value, ModelInterface model){
		this.value = value;
		this.model = model;
	}

	public String getString(){
		return Integer.toString(value);
	}

	@Override
	public void onClick() {
		model.appendNumber(value);
	}
}
