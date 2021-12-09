package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;
import com.voit.Calc.Model.Number;

import javax.swing.*;
import java.awt.*;

public class ViewJPanel extends JPanel implements ModelObserver {

	private final int H_GAP = 5;
	private final int V_GAP = 5;
	private final int PANEL_H = 800;
	private final Font FONT = new Font("TimesRoman", Font.BOLD, 18);

	private JLabel operationImg;
	private JTextField memoryField;
	private JTextField xField;
	private JTextField yField;

	private ModelInterface model;

	//use model to register observers
	public ViewJPanel(ModelInterface model){
		super();
		this.model = model;
		ModelObservable modelObservable = (ModelObservable) model;
		modelObservable.registerObserver(this);

		this.setLayout(new GridBagLayout());
		this.setMaximumSize(new Dimension(Short.MAX_VALUE, PANEL_H));

		makeMemoryField();
		makeOperationImg();
		makeNumbersField();
	}

	private void makeMemoryField(){
		JPanel memory = new JPanel();

		memoryField = new JTextField();
		fixJTextField(memoryField);

		memory.add(memoryField);

		memoryField.setText("MEMORY");

		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 3;
		c.gridy = 0;
		c.gridx = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.2;

		this.add(memory, c);
	}

	private void makeOperationImg(){
		JPanel operation = new JPanel();

		operationImg = new JLabel("image");
		operation.add(operationImg);

		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		c.weighty = 0.8;

		this.add(operation, c);
	}

	private void makeNumbersField(){
		JPanel numbers = new JPanel();
		numbers.setLayout(new BoxLayout(numbers, BoxLayout.X_AXIS));

		xField = new JTextField();
		yField = new JTextField();

		fixJTextField(xField);
		fixJTextField(yField);

		xField.setText("X LICZBA");
		yField.setText("Y LICZBA");

		numbers.add(yField);
		numbers.add(xField);

		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 6;
		c.gridy = 1;
		c.gridx = 1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.7;
		c.weighty = 0.8;

		this.add(numbers, c);
	}

	@Override
	public void update() {
		System.out.println("Update");
		Number x = model.getX();
		Number y = model.getY();
		Number memory = model.getMemory();
		int operation = model.getOperation();

		xField.setText(x.getString());

		if (y.getValue() == 0) yField.setText("");
		else yField.setText(y.getString());

		if (memory.getValue() == 0) memoryField.setText("");
		else memoryField.setText(memory.getString());

		setImage(operation);

		refresh();
	}

	//Helper
	private void setImage(int option){ //todo set image based on current operation

	}

	private void refresh(){
		this.revalidate();
		this.repaint();
	}

	private void fixJTextField(JTextField field){
		field.setEditable(false);
		field.setBorder(BorderFactory.createEmptyBorder());
		field.setOpaque(false);
		field.setFont(FONT);
	}
}
