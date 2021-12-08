package com.voit.Calc.Controller;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.View.View;

public class Controller implements ControllerInterface{
	private ModelInterface model;
	private View view;

	public Controller(ModelInterface model){
		this.model = model;
		this.view = new View(this, model);

		view.makeGUI();
		view.showGUI();
	}
}
