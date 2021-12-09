package com.voit.Calc.View.Buttons;

import com.voit.Calc.Controller.ButtonFunctions.Memory.*;
import com.voit.Calc.Model.ModelInterface;

public class MemoryJButton extends FunctionJButton {
	public static final int CLEAR = 0;
	public static final int READ = 1;
	public static final int ADD = 2;
	public static final int SUBTRACT = 3;
	public static final int WRITE = 4;

	public MemoryJButton(ModelInterface model, int option){
		super();
		switch (option){
			case CLEAR:
				setButtonFunction(new ButtonMemoryClear(model));
				break;

			case READ:
				setButtonFunction(new ButtonMemoryRead(model));
				break;

			case ADD:
				setButtonFunction(new ButtonMemoryAdd(model));
				break;

			case SUBTRACT:
				setButtonFunction(new ButtonMemorySubtract(model));
				break;

			case WRITE:
				setButtonFunction(new ButtonMemoryWrite(model));
				break;

		}
	}
}
