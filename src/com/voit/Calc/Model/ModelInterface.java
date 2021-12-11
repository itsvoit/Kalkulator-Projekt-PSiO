package com.voit.Calc.Model;

public interface ModelInterface {
	// Operations:
	// 0 - add
	// 1 - subtract
	// 2 - multiply
	// 3 - divide
	// 4 - power
	//-1 - no operation set
	int N_OPERATIONS = 5;
	int NO_OP = -1;
	int ADD = 0;
	int SUBTRACT = 1;
	int MULTIPLY = 2;
	int DIVIDE = 3;
	int POWER = 4;

	NumberInterface getX();
	NumberInterface getY();
	NumberInterface getMemory();
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
