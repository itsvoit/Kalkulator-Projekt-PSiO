package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationPower implements ButtonFunctionInterface {
	CalcModelInterface model;
	int power;
	String string;

	public ButtonOperationPower(CalcModelInterface model){
		this.model = model;
		string = "x^y";
	}

	public ButtonOperationPower(CalcModelInterface model, int power){
		this(model);
		this.power = power;
		string = "x^" + power;
	}

	@Override
	public void onClick() {
		if (power != 0)
			model.power(power);
		else
			model.power();
	}

	@Override
	public String getString() {
		return string;
	}
}
