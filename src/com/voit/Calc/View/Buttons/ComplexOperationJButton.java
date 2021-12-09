package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.ComplexOperations.*;
import com.voit.Calc.Model.ModelInterface;

import java.awt.*;

public class ComplexOperationJButton extends FunctionJButton{
	public static final int PERCENT = 0;
	public static final int RECIPROCAL = 1;
	public static final int POWER = 2;
	public static final int SQRT = 3;
	public static final int LOG = 4;
	public static final int POWER2 = 5;
	private final Font FONT = new Font("TimesRoman", Font.BOLD, 12);

	public ComplexOperationJButton(int option, ModelInterface model){
		super();
		switch (option){
			case PERCENT:
				setButtonFunction(new ButtonOperationPercent(model));
				break;

			case RECIPROCAL:
				setButtonFunction(new ButtonOperationReciprocal(model));
				break;

			case POWER:
				setButtonFunction(new ButtonOperationPower(model));
				break;

			case SQRT:
				setButtonFunction(new ButtonOperationSqrt(model));
				this.setFont(FONT);
				break;

			case LOG:
				setButtonFunction(new ButtonOperationLog(model));
				this.setFont(FONT);
				break;

			case POWER2:
				setButtonFunction(new ButtonOperationPower(model, 2));
				break;

		}
	}
}
