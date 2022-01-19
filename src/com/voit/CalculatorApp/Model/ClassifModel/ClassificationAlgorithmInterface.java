package com.voit.CalculatorApp.Model.ClassifModel;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Dataset;

public interface ClassificationAlgorithmInterface {
	int classify(Dataset d);
	void train(Dataset d);
	Classifier getClassifier();
}
