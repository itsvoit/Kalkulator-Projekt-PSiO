package com.voit.Calc.Model;

public class Number implements Cloneable{
	public long intVal;
	public int intLen;
	public long fractionVal;
	public int fractionLen;

	public boolean fractional; //used to determine whether the number is to be considered as fraction
	public boolean negative;

	public Number(){}

	public Number clone(){
		Number clone = new Number();
		clone.intVal = intVal;
		clone.intLen = intLen;
		clone.fractionVal = fractionVal;
		clone.fractionLen = fractionLen;
		clone.fractional = fractional;
		return clone;
	}

	public double getValue() {
		double value = 0;
		value += intVal;

		value += (Math.pow(Math.pow(1, fractionLen), -1) * fractionVal);

		return value;
	}

	public String getString(){
		return Double.toString(getValue());
	}

	public void setValue(double value){
		intVal = (int) value;
		intLen = getLen(intVal);

		negative = intVal < 0;

		double fractionPart = value - intVal;
		String fractionString = Double.toString(fractionPart);
		char[] fractionChars = new char[fractionString.length()];
		fractionString.getChars(0, fractionString.length(), fractionChars, 0);

		long fractionLong = 0;
		int len=0;
		for (; len<18 && len<fractionChars.length; len++){
			fractionLong *= 10;
			fractionLong += Character.getNumericValue(fractionChars[len]);
		}

		fractionVal = fractionLong;
		fractionLen = len;

		fractional = fractionVal != 0; //fractionVal != 0 ==> fractional = true
	}

	private int getLen(long value){
		int len = 0;
		while (value > 0){
			len++;
			value/=10;
		}
		return len;
	}
}
