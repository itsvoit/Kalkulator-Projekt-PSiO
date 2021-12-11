package com.voit.Calc.Model;

import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;

import java.util.ArrayList;

public class ModelOnDouble implements ModelInterface, ModelObservable {
    private ArrayList<ModelObserver> observers;

    private double xVal;
    private double yVal;
    private double memoryVal;

    private String xString;

    private int intLen;
    private int fractionLen;

    private int operation;

    private boolean userInputting;
    private boolean comma;

    public ModelOnDouble(){
        userInputting = true;
        observers = new ArrayList<>();
    }

    public NumberInterface getX() {
        return new NumberDouble(xVal);
    }

    public NumberInterface getY() {
        return new NumberDouble(yVal);
    }

    public NumberInterface getMemory() {
        return new NumberDouble(memoryVal);
    }

    public int getOperation() {
        return operation;
    }

    public void appendNumber(int value) {
        xString += value;
        notifyObservers();
    }

    public void deductNumber() {
        xString = xString.substring(0, xString.length()-1);
        notifyObservers();
    }

    public void comma() {
        if (comma) return;
        comma = true;
        xString += ".";
        notifyObservers();
    }

    public void clear() {
        if (xVal == 0) {
            yVal = 0;
            memoryVal = 0;
        }
        xVal = 0;
        comma = false;
        userInputting = true;

        notifyObservers();
    }

    public void clearAll() {
        xVal = 0;
        clear();
    }

    public void memoryClear() {
        memoryVal = 0;
        notifyObservers();
    }

    public void memoryRead() {
        xVal = memoryVal;
        notifyObservers();
    }

    public void memoryAdd() {
        memoryVal += xVal;
        notifyObservers();
    }

    public void memorySubtract() {
        memoryVal -= xVal;
        notifyObservers();
    }

    public void memoryWrite() {
        memoryVal = xVal;
        notifyObservers();
    }

    public void add() {
        operation = ADD;
        xVal += yVal;
        yVal = 0;
        notifyObservers();
    }

    public void subtract() {
        operation = SUBTRACT;
        xVal = yVal - xVal;
        yVal = 0;
        notifyObservers();
    }

    public void multiply() {
        operation = MULTIPLY;
        xVal *= yVal;
        yVal = 0;
        notifyObservers();
    }

    public void divide() {
        if (xVal == 0) return;
        xVal = yVal / xVal;
        yVal = 0;
        notifyObservers();
    }

    public void negate() {
        xVal *= -1;
        notifyObservers();
    }

    public void equals() { //todo

    }

    public void percent() {
        xVal /= 100;
        notifyObservers();
    }

    public void reciprocal() {
        xVal = Math.pow(xVal, -1);
        notifyObservers();
    }

    public void power() {
        xVal = Math.pow(xVal, yVal);
        notifyObservers();
    }

    public void power(int x) {
        xVal = Math.pow(xVal, x);
        notifyObservers();
    }

    public void sqrt() {
        if (xVal < 0) return;
        xVal = Math.sqrt(xVal);
        notifyObservers();
    }

    public void log() {
        if (xVal == 0) return;
        xVal = Math.log10(xVal);
        notifyObservers();
    }

    @Override
    public void registerObserver(ModelObserver o) {
        if (o != null && !observers.contains(o)){
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(ModelObserver o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }
}
