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
        operation = NO_OP;
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
        if (!userInput){
            clear();
        }
        xString += value;
        convertInput();
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
        if (xString.equals("")) xString += "0"; //if "" we want to show "0." instead of "."
        xString += ".";
        notifyObservers();
    }

    public void clear() {
        if (xVal == 0) {
            yVal = 0;
            memoryVal = 0;
        }
        xVal = 0;
        xString = "";
        comma = false;
        userInput = true;
        setOperation(NO_OP);

        notifyObservers();
    }

    public void clearAll() {
        xVal = 0;
        xString = "";
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
        convertInput();
        setOperation(ADD);
        notifyObservers();
    }

    public void subtract() {
        convertInput();
        setOperation(SUBTRACT);
        notifyObservers();
    }

    public void multiply() {
        convertInput();
        setOperation(MULTIPLY);
        notifyObservers();
    }

    public void divide() {
        convertInput();
        setOperation(DIVIDE);
        notifyObservers();
    }

    public void negate() {
        if (!userInput) return;
        convertInput();
        xVal *= -1;
        xString = Double.toString(xVal);
        int x = (int) xVal;
        if (x == xVal) {
            xString = xString.substring(0, xString.length() - 1); //delete the trailing 0
            if (!comma) xString = xString.substring(0, xString.length() - 1); //delete comma sign
        }

        notifyObservers();
    }

    public void equals() { //todo
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
                xVal = Math.pow(yVal, xVal);
                break;

            case NO_OP:
                return;
        }
        setOperation(NO_OP);
        userInput = false;
        yVal = 0;
        xString = "";
        notifyObservers();
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
        checkForComma();
        notifyObservers();
    }

    public void power() {
        convertInput();
        setOperation(POWER);
        notifyObservers();
    }

    public void power(int x) {
        userInput = false;
        convertInput();
        xVal = Math.pow(xVal, x);
        checkForComma();
        notifyObservers();
    }

    public void sqrt() {
        convertInput();
        if (xVal < 0) return;
        userInput = false;
        xVal = Math.sqrt(xVal);
        checkForComma();
        notifyObservers();
    }

    public void log() {
        convertInput();
        if (xVal == 0) return;
        userInput = false;
        xVal = Math.log10(xVal);
        checkForComma();
        notifyObservers();
    }

    private void convertInput(){
        if (userInput && !xString.equals("")) {
            String returnString = xString;
            if (xString.charAt(xString.length()-1) == '.'){
                returnString = returnString.substring(0, returnString.length()-1);
            }
            if (!comma) xVal = Integer.parseInt(returnString);
            else xVal = Double.parseDouble(returnString);
//            System.out.println(xVal);
        }
    }

    private void setOperation(int newOp){
        if (newOp == NO_OP){
            operation = NO_OP;
            userInput = true;
        }
        if (newOp < ADD || newOp > POWER) return;
        equals();
        yVal = xVal;
        xVal = 0;
        comma = false;
        xString = "";
        operation = newOp;
        userInput = true;
    }

    private void checkForComma(){
        int x = (int) xVal;
        comma = x != xVal;
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
