package com.voit.CalculatorApp;

import com.voit.CalculatorApp.Controller.Controller;
import com.voit.CalculatorApp.Model.Model;

public class Launcher {
	public static void main(String[] args){
		Launcher app = new Launcher();
		app.go();
	}

	private void go(){
		Controller controller2 = new Controller(new Model());
	}
}
