package com.voit.Calc.Controller.ButtonFunctions.ComplexOperations;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterface;

public class ButtonOperationPower  implements ButtonFunctionInterface {
	ModelInterface model;
	int power;

	public ButtonOperationPower(ModelInterface model){
		this.model = model;
	}

	public ButtonOperationPower(ModelInterface model, int power){
		this(model);
		this.power = power;
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
		return "x^y";
	}
}
