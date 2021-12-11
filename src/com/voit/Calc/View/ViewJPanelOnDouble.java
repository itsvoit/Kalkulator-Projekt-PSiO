package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.NumberInterface;

public class ViewJPanelOnDouble extends ViewJPanel{
    public ViewJPanelOnDouble(ModelInterface model) {
        super(model);
    }

    public void update(){
        NumberInterface x = model.getX();
        NumberInterface y = model.getY();
        NumberInterface memory = model.getMemory();

        String xString = getNumberString(x);
        if (xString.equals("")){
            xString += "0";
        }

        setXFieldText(xString);
        setYFieldText(getNumberString(y));
        setXFieldText(getNumberString(memory));

        setOperation(model.getOperation());
    }

    private String getNumberString(NumberInterface num){
        String string;
        System.out.println(num.getString());
        if (num.getValue() == 0){
            string = "";
        }
        else if (num.getString().contains(".")){
            string = num.getString();
        }
        else {
            int xVal = (int) num.getValue();
            System.out.println("Int val: " + xVal);
            string = Integer.toString(xVal);
        }
        return string;
    }
}
