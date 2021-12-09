package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonNumber;
import com.voit.Calc.Model.ModelInterface;

public class NumberJButton extends FunctionJButton{

	public NumberJButton(int value, ModelInterface model){
		super(new ButtonNumber(value, model));
	}
}
