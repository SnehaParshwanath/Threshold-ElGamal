package com.threshold.elgamal;
import java.math.BigInteger;



public class Lagrange {
	
	private static BigInteger value = BigInteger.valueOf(1);
	private   BigInteger one = BigInteger.valueOf(1);
	private BigInteger two  = BigInteger.valueOf(2);
	
	
	public static void setInterpolation(BigInteger shareNum,BigInteger[] thresholdXValues,BigInteger p){
		
		
		BigInteger numerator = BigInteger.valueOf(1);
		BigInteger  denominator =  BigInteger.valueOf(1);
		for(int i=0;i < thresholdXValues.length;i++){
			if (thresholdXValues[i] != shareNum) {
                numerator = numerator.multiply(thresholdXValues[i].negate()).mod(p.subtract(BigInteger.valueOf(1)));
                denominator = denominator.multiply(shareNum.subtract(thresholdXValues[i]));
            }
			//int k = denominator;
		
			
		}
		System.out.println("numerator is "+ numerator);
		System.out.println("denominator is "+ denominator);
		BigInteger smaller;
		if(numerator.compareTo(denominator) == 1){
		      smaller = denominator;
		      System.out.println(smaller);
		}else{
			  smaller = numerator;
			  System.out.println(smaller);
		}
		
		
		final BigInteger gcd = numerator.gcd(denominator);
        if (BigInteger.ONE.compareTo(gcd) < 0) {
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }
        System.out.println("numerator is "+ numerator);
		System.out.println("denominator is "+ denominator);
		
		final BigInteger tmp = (numerator.multiply(denominator.modInverse(p.subtract(BigInteger.valueOf(1))))).mod(p.subtract(BigInteger.valueOf(1)));
		value = tmp;
		//System.out.println("Value is "+ value);
	}
	

	
	public static BigInteger getInterpolation(){
		return value;
	}
	
	/*public static void main(String args[]){
		
		BigInteger[] test = {BigInteger.valueOf(2),BigInteger.valueOf(4),BigInteger.valueOf(5)};
		
		setInterpolation(BigInteger.valueOf(2),test,BigInteger.valueOf(23));
		//new Lagrange().combine(BigInteger.valueOf(2),test,BigInteger.valueOf(22));
		
	}*/
	
	

}
