package com.voit.CalculatorApp.Model.CalcModel;

public class NumberWrapper implements NumberWrapperInterface {
    private double value;
    private boolean fractional;

    public NumberWrapper(){
        value = 0.0;
    }

    public NumberWrapper(double v){
        setValue(v);
    }

    public NumberWrapper(double value, boolean fractional){
        this.value = value;
        this.fractional = fractional;
    }

    @Override
    public String getString() {
//        System.out.println("Evaluating string for " + getValue()); //todo debug
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

    public String toString(){
        return Double.toString(getValue());
    }
}
