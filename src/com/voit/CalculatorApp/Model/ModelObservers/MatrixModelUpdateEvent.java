package com.voit.CalculatorApp.Model.ModelObservers;

import com.voit.CalculatorApp.Model.MatrixModel.Matrix;
import com.voit.CalculatorApp.Model.ModelInterfaces.MatrixModelInterface;

import java.util.ArrayList;

public class MatrixModelUpdateEvent implements ModelUpdateEvent{

	private Matrix matrix1;
	private Matrix matrix2;
	private ArrayList<Matrix> matricesList;
	private String[] matricesNames;

	public MatrixModelUpdateEvent(MatrixModelInterface model){
		matrix1 = model.getMatrix1();
		matrix2 = model.getMatrix2();
		matricesList = model.getMatrices();
		matricesNames = new String[matricesList.size()];
		for (int i=0; i<matricesNames.length; i++) {
			matricesNames[i] = matricesList.get(i).getName();
		}
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

	public String[] getMatricesNames(){
		return matricesNames;
	}
}
