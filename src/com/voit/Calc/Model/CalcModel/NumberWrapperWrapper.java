package com.voit.Calc.Model.CalcModel;

public class NumberWrapperWrapper implements NumberWrapperInterface {
    private double value;
    private boolean fractional;

    public NumberWrapperWrapper(){
        value = 0.0;
    }

    public NumberWrapperWrapper(double v){
        setValue(v);
    }

    public NumberWrapperWrapper(double value, boolean fractional){
        this.value = value;
        this.fractional = fractional;
    }

    @Override
    public String getString() {
        int x = (int)value;
        if (x == value) {
            String out = Integer.toString(x);
            if (fractional) out += ".";
            return out;
        }

        return Double.toString(value);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double v) {
        value = v;

        int x = (int) v;
        fractional = x != v;
    }

    @Override
    public boolean isFractional(){
        return fractional;
    }
}
