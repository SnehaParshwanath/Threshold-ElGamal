package com.threshold.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;

public class generatePublicParameters {
	
	
	public static ArrayList<Integer> getGenerators(int p){
	 int  g;
	 ArrayList<Integer> generators = new ArrayList<Integer>(100);

     for (g = 2; g < p; g += 1) {
         if (checkRoot(g, p)) {
             generators.add(g);
         }
     }

     return generators;

    }

    public static boolean checkRoot(int a,int b){
    	BigInteger i = BigInteger.valueOf(1);
        BigInteger t = BigInteger.valueOf(a).modPow(i,BigInteger.valueOf(b));
        		
            while (t.compareTo(BigInteger.valueOf(1)) == 1) {
            	
                i = i.add(BigInteger.valueOf(1));
                t = t.multiply(BigInteger.valueOf(a));
                t = t.mod(BigInteger.valueOf(b));
            }
            if (i.equals(BigInteger.valueOf(b).subtract(BigInteger.valueOf(1)))) {
                return true;
            }else{
            	return false;
            }
    	
    	
    	
    }
}
