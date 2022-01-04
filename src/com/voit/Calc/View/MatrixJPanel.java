package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelObservers.ModelObserver;
import com.voit.Calc.Model.ModelObservers.ModelUpdateEvent;

import javax.swing.*;

public class MatrixJPanel extends JPanel implements ModelObserver {

	public MatrixJPanel(ModelInterface model){

	}

	private void loadMatrices(){
		//todo load matrices

	}

	@Override
	public void update(ModelUpdateEvent e) {

	}
}
