package com.voit.Calc.Model;

import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;

import java.util.ArrayList;

public class Model implements ModelInterface, ModelObservable {
	private ArrayList<ModelObserver> observers;

	ArrayList<String> history;

	//Numbers
	private Number memory;
	private Number x;
	private Number y;

	// Operations:
	// 0 - add
	// 1 - subtract
	// 2 - multiply
	// 3 - divide
	// 4 - power
	private final int N_OPERATIONS = 5;
	private final int NO_OP = -1;
	private final int ADD = 0;
	private final int SUBTRACT = 1;
	private final int MULTIPLY = 2;
	private final int DIVIDE = 3;
	private final int POWER = 4;
	boolean[] operations;

	public Model(){
		observers = new ArrayList<>();
		history = new ArrayList<>();
		operations = new boolean[N_OPERATIONS];
	}

	//Getters

	public Number getX() {
		return x.clone();
	}

	public Number getY() {
		return y.clone();
	}

	public Number getMemory() {
		return memory.clone();
	}

	public int getOperation() {
		int operation = NO_OP;
		for (int i=0; i<operations.length; i++)
			if (operations[i]) {
				operation = i;
				break;
			}

		return operation;
	}

	//Calculator operations

	//todo check input-related functions
	//Input operations
	public void appendNumber(int value){
		if (!x.fractional && x.intLen > 17) return;
		if (x.fractional && x.fractionLen > 17) return;

		if (!x.fractional) {
			x.intLen++;
			x.intVal *= 10;
			x.intVal += value;
		}
		else {
			x.fractionLen++;
			x.fractionVal *= 10;
			x.fractionVal += value;
		}
		notifyObservers();
	}

	public void deductNumber() {
		if (!x.fractional && x.intLen == 0) return;

		if (x.fractional && x.fractionLen == 0) {
			x.fractional = false;
		}
		else if (x.fractional && x.fractionLen > 0){
			x.fractionLen--;
			x.fractionVal /= 10;
		} else if (!x.fractional && x.intLen > 0) {
			x.intLen--;
			x.intVal /= 10;
		}
		notifyObservers();
	}

	public void comma() {
		if (x.fractional) return;

		x.fractional = true;
		notifyObservers();
	}

	public void clear() {
		x = new Number();

		notifyObservers();
	}

	public void clearAll() {
		y = new Number();

		clearOperations();

		clear(); //notify is in clear
	}

	//todo check memory-related functions
	//Memory operations
	public void memoryClear() {
		memory = new Number();
		notifyObservers();
	}

	public void memoryRead() {
		x = memory.clone();
		notifyObservers();
	}

	public void memoryAdd() {
		if (!memory.fractional && memory.intLen > 17) return;
		if (memory.fractional && memory.fractionLen > 17) return;

		double value = x.getValue() + memory.getValue();
		memory.setFields(value);
		notifyObservers();
	}

	public void memorySubtract() {
		if (!memory.fractional && memory.intLen > 17) return;
		if (memory.fractional && memory.fractionLen > 17) return;

		double value = memory.getValue() - x.getValue();
		memory.setFields(value);
		notifyObservers();
	}

	public void memoryWrite() {
		memory = x.clone();
		notifyObservers();
	}

	//todo simple operation-related functions
	//Simple operations
	public void add() {
		operations[ADD] = true;
		y = x.clone();
		x = new Number();
		notifyObservers();
	}

	public void subtract() {
		operations[SUBTRACT] = true;
		y = x.clone();
		x = new Number();
		notifyObservers();
	}

	public void multiply() {
		operations[MULTIPLY] = true;
		y = x.clone();
		x = new Number();
		notifyObservers();
	}

	public void divide() {
		operations[DIVIDE] = true;
		y = x.clone();
		x = new Number();
		notifyObservers();
	}

	public void negate() {
		x.negative = !x.negative;
		notifyObservers();
	}

	//todo equals function
	//Equals method
	public void equals() {
		int operation = -1;
		operation += 1 + getOperation();
		if (operation == -1) return;

		double value = 0;

		switch (operation){
			case ADD:
				value = x.getValue();
				value += y.getValue();
				x.setFields(value);
				break;

			case SUBTRACT:
				value = x.getValue();
				value -= y.getValue();
				x.setFields(value);
				break;

			case MULTIPLY:
				value = x.getValue();
				value *= y.getValue();
				x.setFields(value);
				break;

			case DIVIDE:
				value = x.getValue();
				if (y.getValue() == 0) return;

				value /= y.getValue();
				x.setFields(value);
				break;

			case POWER:
				value = x.getValue();
				value = Math.pow(value, y.getValue());
				x.setFields(value);
				break;
		}
		y = new Number();

		notifyObservers();
	}

	//todo check complex operation-related functions
	//Complex operations
	public void percent() {
		double value = x.getValue() / 100;
		x.setFields(value);
		notifyObservers();
	}

	public void reciprocal() {
		if (x.getValue() == 0) return;

		double value = Math.pow(x.getValue(), -1);
		x.setFields(value);
		notifyObservers();
	}

	public void power() {
		operations[POWER] = true;
		y = x.clone();
		x = new Number();
		notifyObservers();
	}

	public void sqrt() {
		if (x.getValue() < 0) return;

		double value = Math.sqrt(x.getValue());
		x.setFields(value);
		notifyObservers();
	}

	public void log() {
		if (x.getValue() <= 0) return;

		double value = Math.log10(x.getValue());
		x.setFields(value);
		notifyObservers();
	}

	public void registerObserver(ModelObserver o) {
		if (observers.contains(o)) return;

		observers.add(o);
	}

	public void removeObserver(ModelObserver o) {
		if (!observers.contains(o)) return;

		observers.remove(o);
	}

	public void notifyObservers() {
		//todo notify observers method
		System.out.println("Notify observers");
		for (int i=0; i<observers.size(); i++){
			observers.get(i).update();
		}
	}

	//Helper methods

	private void clearOperations(){
		for (int i=0; i<operations.length; i++) operations[i] = false;
	}
}
