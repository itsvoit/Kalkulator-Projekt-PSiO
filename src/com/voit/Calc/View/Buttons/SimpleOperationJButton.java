package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.SimpleOperations.*;
import com.voit.Calc.Model.ModelInterface;

public class SimpleOperationJButton extends FunctionJButton{
	public static final int ADD = 0;
	public static final int SUBTRACT = 1;
	public static final int MULTIPLY = 2;
	public static final int DIVIDE = 3;
	public static final int NEGATE = 4;

	public SimpleOperationJButton(int option, ModelInterface model){
		super();
		switch (option){
			case ADD:
				setButtonFunction(new ButtonOperationAdd(model));
				break;

			case SUBTRACT:
				setButtonFunction(new ButtonOperationSubtract(model));
				break;

			case MULTIPLY:
				setButtonFunction(new ButtonOperationMultiply(model));
				break;

			case DIVIDE:
				setButtonFunction(new ButtonOperationDivide(model));
				break;

			case NEGATE:
				setButtonFunction(new ButtonOperationNegate(model));
				break;

		}
	}
}
