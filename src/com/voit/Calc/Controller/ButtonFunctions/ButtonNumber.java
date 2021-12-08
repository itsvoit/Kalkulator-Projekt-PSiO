package com.voit.Calc.Controller.ButtonFunctions;

import com.voit.Calc.Model.ModelInterface;

public class ButtonNumber implements ButtonFunctionInterface{
	private String string;
	private int value;
	private ModelInterface model;

	public ButtonNumber(int value, ModelInterface model){
		this.value = value;
		this.model = model;
		this.string = Integer.toString(value);
	}

	public String getString(){
		return string;
	}

	@Override
	public void onClick() {
		model.addNumber(value);
	}
}
