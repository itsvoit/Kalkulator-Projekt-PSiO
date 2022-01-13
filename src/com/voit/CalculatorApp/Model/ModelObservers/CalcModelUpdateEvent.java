package com.voit.CalculatorApp.Model.ModelObservers;

import com.voit.CalculatorApp.Model.CalcModel.NumberWrapperInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;

public class CalcModelUpdateEvent implements ModelUpdateEvent{
	private NumberWrapperInterface x;
	private NumberWrapperInterface y;
	private NumberWrapperInterface memory;
	private int operation;

	public CalcModelUpdateEvent(CalcModelInterface model){
		x = model.getX();
		y = model.getY();
		memory = model.getMemory();
		operation = model.getOperation();
	}

	public NumberWrapperInterface getX() {
		return x;
	}

	public NumberWrapperInterface getY() {
		return y;
	}

	public NumberWrapperInterface getMemory() {
		return memory;
	}

	public int getOperation() {
		return operation;
	}

}
