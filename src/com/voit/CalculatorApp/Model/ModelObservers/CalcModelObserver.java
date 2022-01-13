package com.voit.CalculatorApp.Model.ModelObservers;

public interface CalcModelObserver extends ModelObserver {
	void update(ModelUpdateEvent e);
}
