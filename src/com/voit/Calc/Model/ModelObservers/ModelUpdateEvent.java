package com.voit.Calc.Model.ModelObservers;

import com.voit.Calc.Model.Matrix;
import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.NumberInterface;

import java.util.ArrayList;

public class ModelUpdateEvent {
	private NumberInterface x;
	private NumberInterface y;
	private NumberInterface memory;
	private int operation;
	private Matrix matrix1;
	private Matrix matrix2;
	private ArrayList<Matrix> matricesList;

	public ModelUpdateEvent(ModelInterface model){
		x = model.getX();
		y = model.getY();
		memory = model.getMemory();
		operation = model.getOperation();
		matrix1 = model.getMatrix1();
		matrix2 = model.getMatrix2();
		matricesList = model.getMatrices();
	}

	public NumberInterface getX() {
		return x;
	}

	public NumberInterface getY() {
		return y;
	}

	public NumberInterface getMemory() {
		return memory;
	}

	public int getOperation() {
		return operation;
	}

	public Matrix getMatrix1() {
		return matrix1;
	}

	public Matrix getMatrix2() {
		return matrix2;
	}

	public ArrayList<Matrix> getMatricesList() {
		return matricesList;
	}
}
