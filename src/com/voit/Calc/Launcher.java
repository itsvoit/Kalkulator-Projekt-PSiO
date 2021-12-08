package com.voit.Calc;

import com.voit.Calc.Controller.Controller;
import com.voit.Calc.Controller.ControllerInterface;
import com.voit.Calc.Model.Model;
import com.voit.Calc.Model.ModelInterface;

public class Launcher {
	public static void main(String[] args){
		Launcher app = new Launcher();
		app.go();
	}

	private void go(){
		ModelInterface model = new Model();
		ControllerInterface controller = new Controller(model);
	}
}
