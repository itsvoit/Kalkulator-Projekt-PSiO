package com.voit.Calc;

import com.voit.Calc.Controller.Controller;
import com.voit.Calc.Model.Model;

public class Launcher {
	public static void main(String[] args){
		Launcher app = new Launcher();
		app.go();
	}

	private void go(){
		Controller controller2 = new Controller(new Model());
	}
}
