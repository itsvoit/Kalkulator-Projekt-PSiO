package com.voit.Calc.Controller;

import com.voit.Calc.Model.MatrixModel.Matrix;

public interface ControllerInterface {
	void serializeMatrices();
	void deserializeMatrices(int o);
	String[] getMatricesNames();
	Matrix getMatrix(int x);
	void saveMatrix(Matrix m);
}
