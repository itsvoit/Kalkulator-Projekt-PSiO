package com.voit.Calc.Controller.ControllerInterfaces;

import com.voit.Calc.Model.MatrixModel.Matrix;

public interface MatrixControllerInterface {

	void serializeMatrices();
	void deserializeMatrices(int o);
	String[] getMatricesNames();
	Matrix getMatrix(int x);
	void saveMatrix(Matrix m);
}
