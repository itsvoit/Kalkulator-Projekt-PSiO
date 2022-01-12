package com.voit.Calc.Model.ModelObservers;

import com.voit.Calc.Model.MatrixModel.Matrix;
import com.voit.Calc.Model.ModelInterfaces.MatrixModelInterface;

import java.util.ArrayList;

public class MatrixModelUpdateEvent {

	private Matrix matrix1;
	private Matrix matrix2;
	private ArrayList<Matrix> matricesList;

	public MatrixModelUpdateEvent(MatrixModelInterface model){
		matrix1 = model.getMatrix1();
		matrix2 = model.getMatrix2();
		matricesList = model.getMatrices();
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
