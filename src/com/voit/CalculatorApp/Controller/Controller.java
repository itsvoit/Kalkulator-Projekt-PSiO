package com.voit.CalculatorApp.Controller;

import com.voit.CalculatorApp.Controller.ControllerInterfaces.CalcControllerInterface;
import com.voit.CalculatorApp.Controller.ControllerInterfaces.ClassifControllerInterface;
import com.voit.CalculatorApp.Controller.ControllerInterfaces.MatrixControllerInterface;
import com.voit.CalculatorApp.Model.CalcModel.NumberWrapperInterface;
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

	//Calculator Controller
	public NumberWrapperInterface getX() {
		return calcModel.getX();
	}

	public NumberWrapperInterface getY() {
		return calcModel.getY();
	}

	public NumberWrapperInterface getMemory() {
		return calcModel.getMemory();
	}

	public int getOperation() {
		return calcModel.getOperation();
	}

	public void appendNumber(int value) {
		calcModel.appendNumber(value);
	}

	public void deductNumber() {
		calcModel.deductNumber();
	}

	public void comma() {
		calcModel.comma();
	}

	public void clear() {
		calcModel.clear();
	}

	public void clearAll() {
		calcModel.clearAll();
	}

	public void memoryClear() {
		calcModel.memoryClear();
	}

	public void memoryRead() {
		calcModel.memoryRead();
	}

	public void memoryAdd() {
		calcModel.memoryAdd();
	}

	public void memorySubtract() {
		calcModel.memorySubtract();
	}

	public void memoryWrite() {
		calcModel.memoryWrite();
	}

	public void add() {
		calcModel.add();
	}

	public void subtract() {
		calcModel.subtract();
	}

	public void multiply() {
		calcModel.multiply();
	}

	public void divide() {
		calcModel.divide();
	}

	public void negate() {
		calcModel.negate();
	}

	public void equals() {
		calcModel.equals();
	}

	public void percent() {
		calcModel.percent();
	}

	public void reciprocal() {
		calcModel.reciprocal();
	}

	public void power() {
		calcModel.power();
	}

	public void power(int x) {
		calcModel.power(x);
	}

	public void sqrt() {
		calcModel.sqrt();
	}

	public void log() {
		calcModel.log();
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
