package com.voit.CalculatorApp.Model.ClassifModel;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

public class KNearestNeighbours implements ClassificationAlgorithmInterface{
	Classifier knn;
	@Override
	public int classify(Dataset data) {
		if (knn == null) {
			System.out.println("Classifier is not trained, exiting...");
			return -1;
		}
		System.out.println("Classifing: " + this.getClass().getSimpleName());
		int correct = 0, wrong = 0;
		/* Classify all instances and check with the correct class values */
		for (Instance inst : data) {
			Object predictedClassValue = knn.classify(inst);
			Object realClassValue = inst.classValue();
			if ((predictedClassValue != null) && predictedClassValue.equals(realClassValue))
				correct++;
			else
				wrong++;
		}
		System.out.println("Correct: " + correct);
		System.out.println("Wrong: " + wrong);
		return correct;
	}

	@Override
	public void train(Dataset data) {
		System.out.println("Training: " + this.getClass().getSimpleName());
		/* Contruct a KNN classifier that uses 15 neighbors to make a
		 *decision. */
		knn = new KNearestNeighbors(15);
		knn.buildClassifier(data);
	}
}
