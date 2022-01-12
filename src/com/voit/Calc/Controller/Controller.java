package com.voit.Calc.Controller;

import com.voit.Calc.Controller.ControllerInterfaces.CalcControllerInterface;
import com.voit.Calc.Controller.ControllerInterfaces.ClassifControllerInterface;
import com.voit.Calc.Controller.ControllerInterfaces.MatrixControllerInterface;
import com.voit.Calc.Model.MatrixModel.Matrix;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;
import com.voit.Calc.Model.ModelInterfaces.ClassifModelInterface;
import com.voit.Calc.Model.ModelInterfaces.MatrixModelInterface;
import com.voit.Calc.Model.ModelInterfaces.ModelInterface;
import com.voit.Calc.View.View;

public class Controller implements CalcControllerInterface, MatrixControllerInterface, ClassifControllerInterface {
	private MatrixModelInterface matrixModel;
	private CalcModelInterface calcModel;
	private ClassifModelInterface classifModel;
	private View view;

	public Controller(ModelInterface model){
		this.view = new View(this, model);

		this.matrixModel = (MatrixModelInterface) model;
		this.calcModel = (CalcModelInterface) model;
		this.classifModel = (ClassifModelInterface) model;

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
		return null;
	}
}
