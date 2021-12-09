package com.voit.Calc.Model;

import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;

import java.util.ArrayList;

public class Model implements ModelInterface, ModelObservable {
	private ArrayList<ModelObserver> observers;

	private long intValue;

	public Model(){
		observers = new ArrayList<>();
	}

	public void appendNumber(int value){
		intValue *= 10;
		intValue += value;
		notifyObservers();
	}

	public void deductNumber() {

	}

	//todo memory-related functions
	public void memoryClear() {

	}

	public void memoryRead() {

	}

	public void memoryAdd() {

	}

	public void memorySubtract() {

	}

	public void memoryWrite() {

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
	}
}
