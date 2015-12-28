package com.yumendedao.commons.utils;

import java.math.BigDecimal;

public class NumberUtils {
	
	private NumberUtils(){}
	
	public static boolean isInBorder(int num, int border){
		return isInBorder(new BigDecimal(num), new BigDecimal(border), true);
	}
	
	public static boolean isInBorder(BigDecimal number, BigDecimal border, boolean isIncludeBorder){
		boolean isInBorrder = false;
		if(border.compareTo(BigDecimal.ZERO) < 0)
			border = border.negate();
		if(isIncludeBorder){
			if(border.compareTo(number.abs()) >= 0)
				isInBorrder = true;
		} else {
			if(border.compareTo(number.abs()) > 0)
				isInBorrder = true;
		}
		return isInBorrder;
	}
	
	public static BigDecimal getMaxByWidth(int width, boolean isSymbol, int decimalBit, boolean isSeparator){
		if(width <= 0 || decimalBit < 0 || (decimalBit >= 0 && decimalBit < width -2))
			return BigDecimal.ZERO;
		BigDecimal maxAmount = new BigDecimal(10);
		BigDecimal maxNumber = new BigDecimal(Integer.MAX_VALUE);
		int integerWidth = width;
		if(isSymbol)
			integerWidth -= 1;
		if(decimalBit > 0)
			integerWidth -= decimalBit + 1;
		if(isSeparator){
			integerWidth -= (integerWidth - 1) / 3;
		}
		
		if(integerWidth <= 0){
			integerWidth = 0;
			if(isSeparator)
				decimalBit -= 1;
		}
		
		maxAmount = maxAmount.pow(integerWidth);
		if(decimalBit > 0){
			maxAmount = maxAmount.subtract(new BigDecimal("0.1").pow(decimalBit));
		} else {
			maxAmount = maxAmount.subtract(BigDecimal.ONE);
		}
		return maxAmount.compareTo(maxNumber) > 0 ? maxNumber : maxAmount;
	}


}
