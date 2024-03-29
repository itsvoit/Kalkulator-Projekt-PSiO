package com.voit.CalculatorApp.Controller.ControllerInterfaces;

import com.voit.CalculatorApp.Model.CalcModel.NumberWrapperInterface;

public interface CalcControllerInterface {
	NumberWrapperInterface getX();
	NumberWrapperInterface getY();
	NumberWrapperInterface getMemory();
	int getOperation();

	void appendNumber(int value);
	void deductNumber();
	void comma();
	void clear();
	void clearAll();

	void memoryClear();
	void memoryRead();
	void memoryAdd();
	void memorySubtract();
	void memoryWrite();

	void add();
	void subtract();
	void multiply();
	void divide();
	void negate();
	void equals();
	void percent();
	void reciprocal();
	void power();
	void power(int x);
	void sqrt();
	void log();
}
