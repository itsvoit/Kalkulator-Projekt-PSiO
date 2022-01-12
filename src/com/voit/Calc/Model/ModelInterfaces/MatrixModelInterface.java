package com.voit.Calc.Model.ModelInterfaces;

import com.voit.Calc.Model.MatrixModel.Matrix;

import java.util.ArrayList;

public interface MatrixModelInterface extends ModelInterface {

	Matrix getMatrix1();
	Matrix getMatrix2();
	ArrayList<Matrix> getMatrices();

	void serializeMatrices();
	void deserializeMatrices(int o);
	String[] getMatricesNames();
	Matrix getMatrix(int x);
	void addMatrix(Matrix m);
}
