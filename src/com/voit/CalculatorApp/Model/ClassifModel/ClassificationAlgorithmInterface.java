package com.voit.CalculatorApp.Model.ClassifModel;

import net.sf.javaml.core.Dataset;

public interface ClassificationAlgorithmInterface {
	void classify(Dataset d);
	void train(Dataset d);
}
