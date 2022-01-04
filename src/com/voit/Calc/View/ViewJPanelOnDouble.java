package com.voit.Calc.View;

import com.voit.Calc.Model.ModelInterface;
import com.voit.Calc.Model.ModelObservers.ModelUpdateEvent;
import com.voit.Calc.Model.NumberInterface;

public class ViewJPanelOnDouble extends ViewJPanel{
    public ViewJPanelOnDouble(ModelInterface model) {
        super(model);
    }

    public void update(ModelUpdateEvent event){
        NumberInterface x = event.getX();
        NumberInterface y = event.getY();
        NumberInterface memory = event.getMemory();

        String xString = getNumberString(x);
        if (xString.equals("")){
            xString += "0";
        }

        setXFieldText(xString);
        setYFieldText(getNumberString(y));
        setMemoryFieldText(getNumberString(memory));

        setOperation(event.getOperation());
    }

    private String getNumberString(NumberInterface num){
        String string = num.getString();

        if (string.equals("0")){
            string = "";
        }

        return string;
    }
}
