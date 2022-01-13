package com.voit.CalculatorApp.Model.ModelInterfaces;

import com.voit.CalculatorApp.Model.MatrixModel.Matrix;

import java.util.ArrayList;

public interface MatrixModelInterface extends ModelInterface {
	String MATRICES_FILE = "matrices.ser";
	int OVERWRITE = 0;
	int APPEND = 1;

	Matrix getMatrix1();
	Matrix getMatrix2();
	ArrayList<Matrix> getMatrices();

	void serializeMatrices();
	void deserializeMatrices(int o);
	String[] getMatricesNames();
	Matrix getMatrix(int x);
	void addMatrix(Matrix m);
}
