package com.voit.CalculatorApp.Model.ClassifModel;

import be.abeel.io.LineIterator;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;

import java.io.*;
import java.util.Iterator;

public class MyFileHandler {
	public static int lastDataSize;

	public static Dataset loadDataset(File f, int classIndex, String separator) throws IOException {
		return load(new InputStreamReader(new FileInputStream(f)), classIndex, separator);
	}

	public static Dataset load(Reader in, int classIndex, String separator) {
		LineIterator it = new LineIterator(in);
		it.setSkipBlanks(true);
		it.setSkipComments(true);
		Dataset out = new DefaultDataset();
		Iterator i$ = it.iterator();
		lastDataSize = 0;

		while(i$.hasNext()) {
			lastDataSize++;
			String line = (String)i$.next();
			String[] arr = line.split(separator);
			double[] values;
			if (classIndex == -1) {
				values = new double[arr.length];
			} else {
				values = new double[arr.length - 1];
			}

			String classValue = null;

			for(int i = 0; i < arr.length; ++i) {
				if (i == classIndex) {
					classValue = arr[i];
				} else {
					double val;
					try {
						val = Double.parseDouble(arr[i]);
					} catch (NumberFormatException var14) {
						if (arr[i].matches("[A-Z]")) val = getDoubleValueFromLetters(arr[i]);
						else val = 0.0D / 0.0;
					}

					if (classIndex != -1 && i > classIndex) {
						values[i - 1] = val;
					} else {
						values[i] = val;
					}
				}
			}

			out.add(new DenseInstance(values, classValue));
		}

		return out;
	}

	private static double getDoubleValueFromLetters(String letters){
		double out = 0.0;

		for(int i=0; i< letters.length(); i++){
			out += getDoubleValueFromLetter(letters.charAt(i));
		}

		return out;
	}

	private static double getDoubleValueFromLetter(char c){
		double out = 0.0;

		out = Character.getNumericValue(c) - 10;
//		System.out.println("Numeric value of " + c + ": " + out);

		return out;
	}
}
