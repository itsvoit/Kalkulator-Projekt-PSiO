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
        setMemoryFieldText(getNumberString(memory));

        setOperation(model.getOperation());
    }

    private String getNumberString(NumberInterface num){
        String string = num.getString();

        if (string.equals("0")){
            string = "";
        }

        return string;
    }
}
