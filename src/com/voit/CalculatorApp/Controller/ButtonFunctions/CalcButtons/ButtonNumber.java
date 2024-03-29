package com.voit.CalculatorApp.Controller.ButtonFunctions.CalcButtons;

import com.voit.CalculatorApp.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class ButtonNumber implements ButtonFunctionInterface {
	private int value;
	private CalcModelInterface model;

	public ButtonNumber(int value, CalcModelInterface model){
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
