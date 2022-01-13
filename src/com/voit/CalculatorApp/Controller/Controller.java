package com.voit.CalculatorApp.Controller;

import com.voit.CalculatorApp.Controller.ControllerInterfaces.CalcControllerInterface;
import com.voit.CalculatorApp.Controller.ControllerInterfaces.ClassifControllerInterface;
import com.voit.CalculatorApp.Controller.ControllerInterfaces.MatrixControllerInterface;
import com.voit.CalculatorApp.Model.MatrixModel.Matrix;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.ClassifModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.MatrixModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.ModelInterface;
import com.voit.CalculatorApp.View.View;

public class Controller implements CalcControllerInterface, MatrixControllerInterface, ClassifControllerInterface {
	private MatrixModelInterface matrixModel;
	private CalcModelInterface calcModel;
	private ClassifModelInterface classifModel;
	private View view;

	public Controller(ModelInterface model){
		this.matrixModel = (MatrixModelInterface) model;
		this.calcModel = (CalcModelInterface) model;
		this.classifModel = (ClassifModelInterface) model;

		this.view = new View(this, model);

		view.makeGUI();
		view.showFrame();
	}

	//Matrix Controller
	public void serializeMatrices() {
		matrixModel.serializeMatrices();
	}

	public void deserializeMatrices(int option) {
		matrixModel.deserializeMatrices(option);
	}

	public String[] getMatricesNames() {
		return matrixModel.getMatricesNames();
	}

	public Matrix getMatrix(int x){
		return matrixModel.getMatrix(x);
	}

	public void saveMatrix(Matrix m) {
		matrixModel.addMatrix(m);
	}

	//Classification Controller

	public String[] getAlgorithmsList() {
		return classifModel.getAlgorithmsList();
	}
}
