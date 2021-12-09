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
		clone.negative = negative;
		return clone;
	}

	public double getValue() {
		double value = 0;
		value += intVal;

		value += (Math.pow(Math.pow(10, fractionLen), -1) * fractionVal);

		value *= negative ? -1 : 1; //if negative, then make it negative, duh...

		return value;
	}

	public String getString(){
		String text = "";

		if (negative) text += "-";

		text += intVal;
		if (fractional) {
			text += ".";
			if (fractionVal != 0)
				text += fractionVal;
		}
		return text;
	}

	public void setValue(double value){
		intVal = (int) value;
		intLen = getLen(intVal);
		System.out.println("==========================="); //todo comment
		System.out.printf("SET VALUE\nval: %f\nintVal: %d\n", value, intVal);

		negative = intVal < 0;

		double fractionPart = value - intVal;
		if (negative) intVal *= -1;
		String fractionString = Double.toString(fractionPart);
//		System.out.println(fractionString);
		char[] fractionChars = new char[fractionString.length()];
		fractionString.getChars(0, fractionString.length(), fractionChars, 0);

		long fractionLong = 0;
		int len=0;
		for (; len<16 && len+2<fractionChars.length; len++){
			fractionLong *= 10;
			fractionLong += Character.getNumericValue(fractionChars[len+2]);
		}

		fractionVal = fractionLong;
		fractionLen = len;
		System.out.println("fraction len: " + len); //todo comment

		System.out.printf("fraction: %f\nf long: %d\ngetVal: %f\n", fractionPart, fractionLong, getValue());
		System.out.println("===========================");

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
