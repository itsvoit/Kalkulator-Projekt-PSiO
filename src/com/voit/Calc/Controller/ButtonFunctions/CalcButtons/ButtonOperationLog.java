package com.voit.Calc.Controller.ButtonFunctions.CalcButtons;

import com.voit.Calc.Controller.ButtonFunctions.ButtonFunctionInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;

public class ButtonOperationLog  implements ButtonFunctionInterface {
	CalcModelInterface model;

	public ButtonOperationLog(CalcModelInterface model){
		this.model = model;
	}

	@Override
	public void onClick() {
		model.log();
	}

	@Override
	public String getString() {
		return "log10(x)";
	}
}
