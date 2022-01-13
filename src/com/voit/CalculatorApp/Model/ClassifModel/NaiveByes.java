package com.voit.CalculatorApp.Model.ClassifModel;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.bayes.NaiveBayesClassifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

public class NaiveByes implements ClassificationAlgorithmInterface{
	Classifier nb;
	@Override
	public int classify(Dataset data) {
		System.out.println("Classifing: " + this.getClass().getSimpleName());
		if (nb == null) {
			System.out.println("Classifier is not trained, exiting...");
			return -1;
		}
		System.out.println("Classifing: " + this.getClass().getSimpleName());
		int correct = 0, wrong = 0;
		/* Classify all instances and check with the correct class values */
		for (Instance inst : data) {
			Object predictedClassValue = nb.classify(inst);
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
		nb = new NaiveBayesClassifier(false, false, false);
		nb.buildClassifier(data);
	}
}
