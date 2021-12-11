package com.voit.Calc.Model;

import com.voit.Calc.Model.ModelObservers.ModelObservable;
import com.voit.Calc.Model.ModelObservers.ModelObserver;

import java.util.ArrayList;

public class ModelOnDouble implements ModelInterface, ModelObservable {
    private ArrayList<ModelObserver> observers;

    private double xVal; //current number
    private double yVal; //previous number
    private double memoryVal;

    private String xString; //for initial user input

    private int intLen; //for bounding integer size
    private int fractionLen; //for bounding fraction precision

    private int operation;

    //determines whether the x number is input by user or by operation
    private boolean userInput;
    private boolean comma;

    public ModelOnDouble(){
        userInput = true;
        observers = new ArrayList<>();
        xString = "";
    }

    //Getters
    public NumberInterface getX() {
        convertInput();
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

    //Operations
    public void appendNumber(int value) {
        xString += value;
        notifyObservers();
    }

    public void deductNumber() {
        if (xString.length() == 0) return; //nothing to deduct

        //if deducted comma
        if (xString.charAt(xString.length()-1) == ','){
            comma = false;
        }
        xString = xString.substring(0, xString.length()-1);
        notifyObservers();
    }

    public void comma() {
        if (comma) return; //comma exists
        comma = true;
        xString += ",";
        notifyObservers();
    }

    public void clear() {
        if (xVal == 0) {
            yVal = 0;
            memoryVal = 0;
        }
        xVal = 0;
        comma = false;
        userInput = true;

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
        convertInput();
        memoryVal += xVal;
        notifyObservers();
    }

    public void memorySubtract() {
        convertInput();
        memoryVal -= xVal;
        notifyObservers();
    }

    public void memoryWrite() {
        convertInput();
        memoryVal = xVal;
        notifyObservers();
    }

    public void add() {
        operation = ADD;
        convertInput();
        notifyObservers();
    }

    public void subtract() {
        operation = SUBTRACT;
        convertInput();
        notifyObservers();
    }

    public void multiply() {
        operation = MULTIPLY;
        convertInput();
        notifyObservers();
    }

    public void divide() {
        operation = DIVIDE;
        convertInput();
        notifyObservers();
    }

    public void negate() {
        convertInput();
        xVal *= -1;
        notifyObservers();
    }

    public void equals() { //todo
        userInput = false;
        convertInput();

        switch (operation){
            case ADD:
                xVal += yVal;
                break;

            case SUBTRACT:
                xVal = yVal - xVal;
                break;

            case MULTIPLY:
                xVal *= yVal;
                break;

            case DIVIDE:
                if (xVal == 0) return;
                xVal = yVal / xVal;
                break;

            case POWER:
                xVal = Math.pow(xVal, yVal);
                break;

            case NO_OP:
                return;
        }
        yVal = 0;
    }

    public void percent() {
        userInput = false;
        convertInput();
        xVal /= 100;
        notifyObservers();
    }

    public void reciprocal() {
        userInput = false;
        convertInput();
        xVal = Math.pow(xVal, -1);
        notifyObservers();
    }

    public void power() {
        operation = POWER;
        convertInput();
        notifyObservers();
    }

    public void power(int x) {
        userInput = false;
        convertInput();
        xVal = Math.pow(xVal, x);
        notifyObservers();
    }

    public void sqrt() {
        convertInput();
        if (xVal < 0) return;
        userInput = false;
        xVal = Math.sqrt(xVal);
        notifyObservers();
    }

    public void log() {
        convertInput();
        if (xVal == 0) return;
        userInput = false;
        xVal = Math.log10(xVal);
        notifyObservers();
    }

    private void convertInput(){
        if (userInput) {
            if (comma) xVal = Integer.parseInt(xString);
            else xVal = Double.parseDouble(xString);
        }
    }

    //Observable methods
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
