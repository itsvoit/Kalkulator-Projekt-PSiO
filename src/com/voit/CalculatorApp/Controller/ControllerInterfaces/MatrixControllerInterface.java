package com.voit.CalculatorApp.Controller.ControllerInterfaces;

import com.voit.CalculatorApp.Model.MatrixModel.Matrix;

public interface MatrixControllerInterface {

	void serializeMatrices();
	void deserializeMatrices(int o);
	String[] getMatricesNames();
	Matrix getMatrix(int x);
	void saveMatrix(Matrix m);
}
