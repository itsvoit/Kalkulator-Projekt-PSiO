package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;
import com.voit.Calc.Model.Number;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ViewJPanel extends JPanel implements ModelObserver {

	private final int H_GAP = 5;
	private final int V_GAP = 5;
	private final int PANEL_H = 800;
	private final Font FONT = new Font("TimesRoman", Font.BOLD, 18);
	private final Font FONT_COMPONENTS = new Font("TimesRoman", Font.BOLD, 18);
	private final Font OPERATION_FONT = new Font("TimesRoman", Font.BOLD, 28);

	private JTextPane operationImg;
	private JTextPane memoryField;
	private JTextPane xField;
	private JTextPane yField;

	private ModelInterface model;

	//use model to register observers
	public ViewJPanel(ModelInterface model){
		super();
		this.model = model;
		ModelObservable modelObservable = (ModelObservable) model;
		modelObservable.registerObserver(this);

		this.setLayout(new GridLayout(3, 2, H_GAP, V_GAP));
		this.setMaximumSize(new Dimension(Short.MAX_VALUE, PANEL_H));

		makeMemoryField();
		makeOperationImg();
		makeNumbersField();

		this.add(Box.createRigidArea(new Dimension(5, 5)));
		this.add(memoryField);
		this.add(Box.createRigidArea(new Dimension(5, 5)));
		this.add(yField);
		this.add(operationImg);
		this.add(xField);
	}

	private void makeMemoryField(){
		JPanel memory = new JPanel();

		memoryField = new JTextPane();
		fixJTextField(memoryField);

		memory.add(memoryField);
	}

	private void makeOperationImg(){
		JPanel operation = new JPanel();

		operationImg = new JTextPane();
		fixJTextField(operationImg);
		operation.add(operationImg);

		operationImg.setFont(OPERATION_FONT);
	}

	private void makeNumbersField(){
		xField = new JTextPane();
		yField = new JTextPane();

		fixJTextField(xField);
		fixJTextField(yField);

		xField.setText("0");
	}

	@Override
	public void update() {
//		System.out.println("Update");
		Number x = model.getX();
		Number y = model.getY();
		Number memory = model.getMemory();
		int operation = model.getOperation();

		xField.setText(x.getString());
		setYVal(y);
		setMemVal(memory);

		setOperation(operation);

		refresh();
	}

	//Helper

	private void setYVal(Number y){
		if (y.getString().equals("0")) yField.setText("");
		else yField.setText(y.getString());
	}

	private void setMemVal(Number mem){
		if (mem.getString().equals("0")) memoryField.setText("");
		else {
			String text = "Mem: ";
			text += mem.getString();
			memoryField.setText(text);
		}
	}

	private void setOperation(int option){ //todo set image based on current operation
		switch (option){
			case ModelInterface.ADD:
				operationImg.setText("+");
				break;

			case ModelInterface.SUBTRACT:
				operationImg.setText("-");
				break;

			case ModelInterface.MULTIPLY:
				operationImg.setText("x");
				break;

			case ModelInterface.DIVIDE:
				operationImg.setText("/");
				break;

			case ModelInterface.POWER:
				operationImg.setText("^");
				break;

			case ModelInterface.NO_OP:
				operationImg.setText("");
				break;
		}
	}

	private void refresh(){
		this.revalidate();
		this.repaint();
	}

	private void fixJTextField(JTextPane textPane){
		textPane.setEditable(false);
		textPane.setBorder(BorderFactory.createEmptyBorder());
		textPane.setOpaque(false);
		textPane.setFont(FONT_COMPONENTS);

		StyledDocument documentStyle = textPane.getStyledDocument();
		SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
		SimpleAttributeSet wrapAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
	}
}
