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

	public void addNumber(int value){
		intValue *= 10;
		intValue += value;
		notifyObservers();
	}





	@Override
	public void registerObserver(ModelObserver o) {
		if (observers.contains(o)) return;

		observers.add(o);
	}

	@Override
	public void removeObserver(ModelObserver o) {
		if (!observers.contains(o)) return;

		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		//todo notify observers method
		System.out.println("Notify observers");
	}
}
