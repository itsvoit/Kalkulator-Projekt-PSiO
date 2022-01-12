package com.voit.CalculatorApp.Model.ModelObservers;

public interface ModelObservable {
	void registerObserver(ModelObserver o);
	void removeObserver(ModelObserver o);
	void notifyObservers();
}
