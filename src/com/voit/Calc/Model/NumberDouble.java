package com.voit.Calc.Model;

public class NumberDouble implements NumberInterface{
    private double value;
    private boolean fractional;

    public NumberDouble(){
        value = 0.0;
    }

    public NumberDouble(double v){
        setValue(v);
    }

    public NumberDouble(double value, boolean fractional){
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
