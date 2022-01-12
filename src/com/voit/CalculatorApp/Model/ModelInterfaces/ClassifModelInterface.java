package com.voit.CalculatorApp.Model.ModelInterfaces;

public interface ClassifModelInterface extends ModelInterface {
	String[] algorithms = {"Logistic Regression", "Naive Bayes", "Stochastic Gradient Descent", "K-Nearest Neighbours", "Support Vector Machine"};
	String[] getAlgorithmsList();
}
