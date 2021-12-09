package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.SpecialOperations.*;
import com.voit.Calc.Model.ModelInterface;

public class SpecialOperationJButton extends FunctionJButton{
	public static final int EQUALS = 0;
	public static final int CLEAR = 1;
	public static final int CLEAR_ALL = 2;
	public static final int COMMA = 3;
	public static final int DEDUCT = 4;

	public SpecialOperationJButton(int option, ModelInterface model){
		super();
		switch (option){
			case EQUALS:
				setButtonFunction(new ButtonOperationEquals(model));
				break;

			case CLEAR:
				setButtonFunction(new ButtonOperationClear(model));
				break;

			case CLEAR_ALL:
				setButtonFunction(new ButtonOperationClearAll(model));
				break;

			case COMMA:
				setButtonFunction(new ButtonOperationComma(model));
				break;

			case DEDUCT:
				setButtonFunction(new ButtonOperationDeductNumber(model));
				break;

		}
	}
}
