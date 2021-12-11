package com.voit.Calc.Model;

public class NumberDouble implements NumberInterface{
    double value;

    public NumberDouble(){
        value = 0.0;
    }

    public NumberDouble(double v){
        value = v;
    }

    @Override
    public String getString() {
        return Double.toString(value);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double v) {
        value = v;
    }
}
