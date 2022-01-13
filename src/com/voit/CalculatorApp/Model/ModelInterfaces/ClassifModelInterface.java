package com.voit.CalculatorApp.Model.ModelInterfaces;

public interface ClassifModelInterface extends ModelInterface {
	String[] algorithms = {"ZeroR", "Naive Bayes", "Mean Feature Voting", "K-Nearest Neighbours", "Nearest Mean Classifier"};
	String[] getAlgorithmsList();
}
