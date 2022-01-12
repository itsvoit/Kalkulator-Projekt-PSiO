package com.voit.Calc.View;

import com.voit.Calc.Model.CalcModel.NumberWrapperInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;
import com.voit.Calc.Model.ModelObservers.CalcModelUpdateEvent;
import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class CalcViewJPanel extends JPanel implements ModelObserver {

	private final int H_GAP = 5;
	private final int V_GAP = 5;
	private final int PANEL_H = 800;
	private Font FONT = new Font("TimesRoman", Font.BOLD, 18);
	private Font FONT_COMPONENTS = new Font("TimesRoman", Font.BOLD, 18);
	private Font OPERATION_FONT = new Font("TimesRoman", Font.BOLD, 28);

	private JTextPane operationImg;
	private JTextPane memoryField;
	private JTextPane xField;
	private JTextPane yField;

	//use model to register observers
	public CalcViewJPanel(CalcModelInterface model){
		super();
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

		setXFieldText("0");
	}

	protected void setXFieldText(String text){
		xField.setText(text);
	}

	protected void setYFieldText(String text){
		yField.setText(text);
	}

	protected void setMemoryFieldText(String text){
		memoryField.setText(text);
	}

	@Override
	public void update(CalcModelUpdateEvent event) {
//		System.out.println("Update");
		NumberWrapperInterface x = event.getX();
		NumberWrapperInterface y = event.getY();
		NumberWrapperInterface memory = event.getMemory();
		int operation = event.getOperation();

		setXFieldText(x.getString());
		setYVal(y);
		setMemVal(memory);

		setOperation(operation);

		refresh();
	}

	//Helper

	private void setYVal(NumberWrapperInterface y){
		if (y.getString().equals("0")) setXFieldText("");
		else setYFieldText(y.getString());
	}

	private void setMemVal(NumberWrapperInterface mem){
		if (mem.getString().equals("0")) setMemoryFieldText("");
		else {
			String text = "Mem: ";
			text += mem.getString();
			setMemoryFieldText(text);
		}
	}

	protected void setOperation(int option){
		switch (option){
			case CalcModelInterface.ADD:
				operationImg.setText("+");
				break;

			case CalcModelInterface.SUBTRACT:
				operationImg.setText("-");
				break;

			case CalcModelInterface.MULTIPLY:
				operationImg.setText("x");
				break;

			case CalcModelInterface.DIVIDE:
				operationImg.setText("/");
				break;

			case CalcModelInterface.POWER:
				operationImg.setText("^");
				break;

			case CalcModelInterface.NO_OP:
				operationImg.setText("");
				break;
		}
	}

	protected void refresh(){
		this.revalidate();
		this.repaint();
	}

	protected void fixJTextField(JTextPane textPane){
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
