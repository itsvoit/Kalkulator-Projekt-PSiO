package com.voit.Calc.Model;

import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;

import java.util.ArrayList;

public class Model implements ModelInterface, ModelObservable {
	private ArrayList<ModelObserver> observers;

	private long intValue;

	public Model(){
		observers = new ArrayList<>();
		history = new ArrayList<>();
		operations = new boolean[N_OPERATIONS];
	}

	//Getters

	public Number getX() {
		return x;
	}

	public Number getY() {
		return y;
	}

	public Number getMemory() {
		return memory;
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
		intValue *= 10;
		intValue += value;
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

	//todo memory-related functions
	public void memoryClear() {

	}

	public void memoryRead() {

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

	//todo complex operation-related functions
	//Complex operations
	public void percent() {

	}

	public void reciprocal() {

	}

	public void power() {
		operations[POWER] = true;
		y = x.clone();
		x = new Number();
		notifyObservers();
	}

	public void sqrt() {

	}

	public void log() {

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
