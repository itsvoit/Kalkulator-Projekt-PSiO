package com.voit.Calc.View;

import com.voit.Calc.Controller.ButtonFunctions.*;
import com.voit.Calc.Model.ModelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @implNote Extend this class to make a functional button with loose ties to
 * model extending ModelInterface.
 *
 * @implSpec Include correct strategy implementing ButtonFunctionInterface
 *
 * @see com.voit.Calc.Model.ModelInterface
 */
public class FunctionJButton extends JButton implements ActionListener {
	public static final int NONE = -1;
	public static final int NUMBER = 0;
	public static final int ADD = 1;
	public static final int SUBTRACT = 2;
	public static final int MULTIPLY = 3;
	public static final int DIVIDE = 4;
	public static final int NEGATE = 5;
	public static final int EQUALS = 6;
	public static final int CLEAR = 7;
	public static final int CLEAR_ALL = 8;
	public static final int COMMA = 9;
	public static final int DEDUCT = 10;
	public static final int MEMORY_CLEAR = 11;
	public static final int MEMORY_READ = 12;
	public static final int MEMORY_ADD = 13;
	public static final int MEMORY_SUBTRACT = 14;
	public static final int MEMORY_WRITE = 15;
	public static final int PERCENT = 16;
	public static final int RECIPROCAL = 17;
	public static final int POWER = 18;
	public static final int SQRT = 19;
	public static final int LOG = 20;
	public static final int POWER2 = 21;

	private final Font FONT = new Font("TimesRoman", Font.BOLD, 12);

	ButtonFunctionInterface buttonFunction;

	public FunctionJButton(int option, ModelInterface model){
		this.addActionListener(this);
		this.setFont(new Font("TimesRoman", Font.BOLD, 18));
		this.setOperation(option, model);
	}

	public FunctionJButton(int option, int value, ModelInterface model){
		this.addActionListener(this);
		this.setFont(new Font("TimesRoman", Font.BOLD, 18));
		if (option == NUMBER)
			this.setButtonFunction(new ButtonNumber(value, model));
		else
			this.setOperation(option, model);
	}

	public void setOperation(int option, ModelInterface model){
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

			case MEMORY_CLEAR:
				setButtonFunction(new ButtonOperationMemoryClear(model));
				break;

			case MEMORY_READ:
				setButtonFunction(new ButtonOperationMemoryRead(model));
				break;

			case MEMORY_ADD:
				setButtonFunction(new ButtonOperationMemoryAdd(model));
				break;

			case MEMORY_SUBTRACT:
				setButtonFunction(new ButtonOperationMemorySubtract(model));
				break;

			case MEMORY_WRITE:
				setButtonFunction(new ButtonOperationMemoryWrite(model));
				break;

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

	public void setButtonFunction(ButtonFunctionInterface buttonFunction) {
		this.buttonFunction = buttonFunction;
		super.setText(buttonFunction.getString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (buttonFunction == null) return;

		buttonFunction.onClick();
	}


}
