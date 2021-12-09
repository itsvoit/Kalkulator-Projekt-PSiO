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


	private boolean[] operations;

	private boolean initVal;

	public Model(){
		observers = new ArrayList<>();
		history = new ArrayList<>();
		operations = new boolean[N_OPERATIONS];
		x = new Number();
		y = new Number();
		memory = new Number();
		initVal = true;
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
		checkInitValue();
		if (!x.fractional && x.intLen > 17) return;
		if (x.fractional && x.fractionLen > 17) return;

		if (!x.fractional && !x.negative) { //not fractional, not negative
			x.intLen++;
			x.intVal *= 10;
			x.intVal += value;
		}
		else if (!x.negative){ //fractional, not negative
			x.fractionLen++;
			x.fractionVal *= 10;
			x.fractionVal += value;
		}
		else if (!x.fractional){ //not fractional, negative
			x.intLen++;
			x.intVal *= 10;
			x.intVal += value;
		}
		else{ //fractional, negative
			x.fractionLen++;
			x.fractionVal *= 10;
			x.fractionVal += value;
		}
		notifyObservers();
	}

	public void deductNumber() {
		checkInitValue();
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
		checkInitValue();
		if (x.fractional) return;

		x.fractional = true;
		notifyObservers();
	}

	public void clear() {
		x = new Number();
		initVal = true;

		notifyObservers();
	}

	public void clearAll() {
		y = new Number();

		clearOperations();
		memory = new Number();

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
		initVal = false;
		notifyObservers();
	}

	public void memoryAdd() {
		if (!memory.fractional && memory.intLen > 17) return;
		if (memory.fractional && memory.fractionLen > 17) return;

		double value = x.getValue() + memory.getValue();
		memory.setValue(value);
		notifyObservers();
	}

	public void memorySubtract() {
		if (!memory.fractional && memory.intLen > 17) return;
		if (memory.fractional && memory.fractionLen > 17) return;

		double value = memory.getValue() - x.getValue();
		memory.setValue(value);
		notifyObservers();
	}

	public void memoryWrite() {
		memory = x.clone();
		notifyObservers();
	}

	//todo check simple operation-related functions
	//Simple operations
	public void add() {
		if (getOperation() != -1) equals();
		clearOperations();
		operations[ADD] = true;
		y = x.clone();
		x = new Number();
		initVal = false;
		notifyObservers();
	}

	public void subtract() {
		if (getOperation() != -1) equals();
		clearOperations();
		operations[SUBTRACT] = true;
		y = x.clone();
		x = new Number();
		initVal = false;
		notifyObservers();
	}

	public void multiply() {
		if (getOperation() != -1) equals();
		clearOperations();
		operations[MULTIPLY] = true;
		y = x.clone();
		x = new Number();
		initVal = false;
		notifyObservers();
	}

	public void divide() {
		if (getOperation() != -1) equals();
		clearOperations();
		operations[DIVIDE] = true;
		y = x.clone();
		x = new Number();
		initVal = false;
		notifyObservers();
	}

	public void negate() {
		x.negative = !x.negative;
//		initVal = false;
		notifyObservers();
	}

	//todo check equals function
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
//				System.out.println(x.getValue());
//				System.out.println(y.getValue());
//				System.out.println(value);
				x.setValue(value);
				break;

			case SUBTRACT:
				value = y.getValue();
				value -= x.getValue();
				x.setValue(value);
				break;

			case MULTIPLY:
				value = x.getValue();
				value *= y.getValue();
				x.setValue(value);
				break;

			case DIVIDE:
				value = y.getValue();
				if (x.getValue() == 0) return;

				value /= x.getValue();
				x.setValue(value);
				break;

			case POWER:
				value = y.getValue();
				value = Math.pow(value, x.getValue());
				x.setValue(value);
				break;
		}
		y = new Number();
		initVal = false;
		clearOperations();
		notifyObservers();
	}

	//todo check complex operation-related functions
	//Complex operations
	public void percent() {
		double value = x.getValue() / 100;
		x.setValue(value);
		initVal = false;
		notifyObservers();
	}

	public void reciprocal() {
		if (x.getValue() == 0) return;

		double value = Math.pow(x.getValue(), -1);
		x.setValue(value);
		initVal = false;
		notifyObservers();
	}

	public void power() {
		if (getOperation() != -1) equals();
		clearOperations();
		operations[POWER] = true;
		y = x.clone();
		x = new Number();
		initVal = false;
		notifyObservers();
	}

	public void power(int power) {
		double value = Math.pow(x.getValue(), power);
		x.setValue(value);
		initVal = false;
		notifyObservers();
	}

	public void sqrt() {
		if (x.getValue() < 0) return;

		double value = Math.sqrt(x.getValue());
		x.setValue(value);
		initVal = false;
		notifyObservers();
	}

	public void log() {
		if (x.getValue() <= 0) return;

		double value = Math.log10(x.getValue());
		x.setValue(value);
		initVal = false;
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
//		System.out.println("Notify observers");
		for (int i=0; i<observers.size(); i++){
			observers.get(i).update();
		}
		showDebug(); //todo comment
	}

	//Helper methods

	private void showDebug(){
		System.out.println("------------------");
		System.out.println("------------------");
		showInfo(x, "X:");
		System.out.println("------------------");
		showInfo(y, "Y:");
		System.out.println("------------------");
		showInfo(memory, "Memory:");
		System.out.println("------------------");
	}

	private void showInfo(Number num, String prompt){
		System.out.println(prompt);
		System.out.printf("value: %f\n", num.getValue());
		System.out.printf("x int len: %d\n", num.intLen);
		System.out.printf("x f len: %d\n", num.fractionLen);
		System.out.printf("fractional: %b\nnegative: %b\n", num.fractional, num.negative);

	}

	private void clearOperations(){
		for (int i=0; i<operations.length; i++) operations[i] = false;
	}

	private void checkInitValue(){
		if (!initVal){
			clear();
			initVal = true;
		}
	}
}
