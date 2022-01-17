package com.voit.CalculatorApp.Model;

import com.voit.CalculatorApp.Model.CalcModel.NumberWrapperInterface;
import com.voit.CalculatorApp.Model.CalcModel.NumberWrapperWrapper;
import com.voit.CalculatorApp.Model.MatrixModel.Matrix;
import com.voit.CalculatorApp.Model.ModelInterfaces.CalcModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.ClassifModelInterface;
import com.voit.CalculatorApp.Model.ModelInterfaces.MatrixModelInterface;
import com.voit.CalculatorApp.Model.ModelObservers.*;

import java.io.*;
import java.util.ArrayList;

public class Model implements CalcModelInterface, MatrixModelInterface, ClassifModelInterface, ModelObservable {
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

    public Model(){
        userInput = true;
        observers = new ArrayList<>();
        xString = "";
        operation = NO_OP;

        matricesList = new ArrayList<>();
        matricesNamesList = new ArrayList<>();
        deserializeMatrices(APPEND);
    }

    //Getters
    public NumberWrapperInterface getX() {
        return new NumberWrapperWrapper(xVal, comma);
    }

    public NumberWrapperInterface getY() {
        return new NumberWrapperWrapper(yVal);
    }

    public NumberWrapperInterface getMemory() {
        return new NumberWrapperWrapper(memoryVal);
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
        if (!userInput) return; //value in x is not user-generated
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

    public void equals() {
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

        if (xVal != 0 && yVal != 0) equals();
        operation = newOp;
        userInput = true;

        if (xVal == 0) return;

        yVal = xVal;
        xVal = 0;
        comma = false;
        xString = "";
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
        System.out.println("Notifying observers");
        CalcModelUpdateEvent eventC = new CalcModelUpdateEvent(this);
        MatrixModelUpdateEvent eventM = new MatrixModelUpdateEvent(this);
        for (int i = 0; i < observers.size(); i++) {
            ModelObserver obs = observers.get(i);
            if (obs instanceof CalcModelObserver)
                obs.update(eventC);
            else if (obs instanceof MatrixModelObserver)
                obs.update(eventM);
        }
    }

    //-------------------------------------------------------
    //-------------------- Matrix calc ----------------------

    private Matrix matrix1;
    private Matrix matrix2;
    private ArrayList<Matrix> matricesList;
    private ArrayList<String> matricesNamesList;

    public Matrix getMatrix1() {
        return matrix1;
    }

    public Matrix getMatrix2() {
        return matrix2;
    }

    public ArrayList<Matrix> getMatrices(){
        return matricesList;
    }

    public String[] getMatricesNames(){
        String[] list = matricesNamesList.toArray(new String[0]);
        for (String item : list) {
            System.out.println(item);
        }
        return matricesNamesList.toArray(new String[0]);
    }

    public Matrix getMatrix(int x){
        System.out.println("Getting matrix: " + matricesList.get(x));
        return matricesList.get(x).clone();
    }

    public void addMatrix(Matrix m){
        if (m.getName() == null){
            int i=1;
            while (true){
                if (matricesNamesList.contains(Integer.toString(i))) {
                    i++;
                    continue;
                }

                m.setName(Integer.toString(i));
                break;
            }
        }
        if (matricesList.contains(m)) return;

        System.out.println("Adding matrix: " + m);
        matricesList.add(m);
        matricesNamesList.add(m.getName());

        notifyObservers();
        serializeMatrices();
    }

    public void serializeMatrices(){
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(MATRICES_FILE));
            outStream.writeObject(matricesList);
        } catch (IOException e) {
            System.out.println("couldn't serialize matrices");
        }
    }

    public void deserializeMatrices(int option){
        ArrayList<Matrix> newMatrices = null;
        try {
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(MATRICES_FILE));
            //Deserialize Matrices
            Object arr = inStream.readObject();
            if (arr instanceof ArrayList){
                ArrayList tmpArr = (ArrayList) arr;
                if (tmpArr.size() > 0 && tmpArr.get(0) instanceof Matrix)
                    newMatrices = (ArrayList<Matrix>) tmpArr;
            }

        } catch (IOException e){
            System.out.println("File not found");
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (newMatrices == null) return;

        if (option == OVERWRITE) {
            matricesList = newMatrices;
        }
        else {
            for (Matrix item : newMatrices) {
                if (matricesList.contains(item)) continue;
                matricesList.add(item);
            }
        }

        for (int i = 0; i < matricesList.size(); i++) {
            matricesNamesList.add(matricesList.get(i).getName());
        }
    }

    //-------------------------------------------------------
    //---------------- Data classification ------------------

    public String[] getAlgorithmsList() {
        return algorithms;
    }
}
