package com.voit.Calc.Model.ModelObservers;

public interface ModelObservable {
	void registerObserver(ModelObserver o);
	void removeObserver(ModelObserver o);
	void notifyObservers();
}
