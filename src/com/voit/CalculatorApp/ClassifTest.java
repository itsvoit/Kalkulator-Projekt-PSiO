package com.voit.CalculatorApp;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.InstanceTools;
import net.sf.javaml.tools.data.FileHandler;

import java.util.Scanner;

public class ClassifTest {
	private final int SIZE = 3;
	private final int SET_SIZE = 4;
	private double values[];
	private Dataset dataset;

	private ClassifTest(){
		values = new double[SIZE];
		dataset = new DefaultDataset();
	}

	public static void main(String[] args){
		ClassifTest app = new ClassifTest();
		app.go();
	}

	private void go(){
		fillValues();
		Instance instance = new DenseInstance(values);
		dataset.add(instance);
		for (int i=1; i<SET_SIZE; i++){
			dataset.add(InstanceTools.randomInstance(SIZE));
		}

		for (int i=0; i<SET_SIZE; i++){
			System.out.println(dataset.get(i));
		}

		FileHandler handler;
	}

	private void fillValues(){
		System.out.println("Input " + SIZE + " doubles into the first Instance:");
		for (int i=0; i<SIZE; i++){
			values[i] = getDoubleFromUser();
		}
	}

	private double getDoubleFromUser(){
		Scanner scanner = new Scanner(System.in);
		double value = 0;

		while (true){
			try{
				value = Double.parseDouble(scanner.nextLine());
				break;
			} catch (Exception e){
//				e.printStackTrace();
				System.out.println("Wrong value type/not a double, try again...");
			}
		}

		return value;
	}
}
