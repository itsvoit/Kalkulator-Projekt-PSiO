package com.voit.Calc.View;

import com.voit.Calc.Model.CalcModel.NumberWrapperInterface;
import com.voit.Calc.Model.ModelInterfaces.CalcModelInterface;
import com.voit.Calc.Model.ModelObservers.CalcModelUpdateEvent;

public class CalcViewJPanelOnDouble extends CalcViewJPanel {
    public CalcViewJPanelOnDouble(CalcModelInterface model) {
        super(model);
    }

    public void update(CalcModelUpdateEvent event){
        NumberWrapperInterface x = event.getX();
        NumberWrapperInterface y = event.getY();
        NumberWrapperInterface memory = event.getMemory();

        String xString = getNumberString(x);
        if (xString.equals("")){
            xString += "0";
        }

        setXFieldText(xString);
        setYFieldText(getNumberString(y));
        setMemoryFieldText(getNumberString(memory));

        setOperation(event.getOperation());
    }

    private String getNumberString(NumberWrapperInterface num){
        String string = num.getString();

        if (string.equals("0")){
            string = "";
        }

        return string;
    }
}
