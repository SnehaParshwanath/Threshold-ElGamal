package com.threshold.elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


public class Shamir {
	
	private  int total;
	private  int threshold;
	private  BigInteger secret;
	private  HashMap<Integer, BigInteger> partyShares;
	private  BigInteger prime;
	
	public void setShamirParmaters(int total,int threshold,BigInteger secret,BigInteger prime){
		
		this.total = total;
		this.threshold = threshold;
		this.secret = secret;
		this.prime = prime;
		
	}
	
	public void setPartyShares(){
		
		
		final BigInteger[] coefficentValues = new BigInteger[threshold-1];
		
		
		for (int i = 0; i < threshold - 1; i++) {
			    coefficentValues[i] = randomZp(prime);
	            System.out.println("a" + (i + 1) + ": " + coefficentValues[i]);
	    }
		
		partyShares = new HashMap<Integer, BigInteger>();
		
		 for (int i = 1; i <= total; i++) {
	            BigInteger accum = secret;

	            for (int j = 1; j < threshold; j++) {
	                final BigInteger t1 = BigInteger.valueOf(i).modPow(BigInteger.valueOf(j), prime);
	                final BigInteger t2 = coefficentValues[j - 1].multiply(t1).mod(prime);

	                accum = accum.add(t2).mod(prime);
	            }
	            partyShares.put(i,accum);
	            
	        }
		
	}
	
	public HashMap<Integer, BigInteger>  getPartyShares(){
		
		return partyShares;
	}
	
	public void printPartyShares(){
		
		for (Map.Entry<Integer, BigInteger> entry : partyShares.entrySet()) {
		    System.out.println("Share number "+entry.getKey()+" : Value = "+entry.getValue());
		}
	}
	
	
	
	private BigInteger randomZp(final BigInteger p) {
        while (true) {
        	SecureRandom sr = new SecureRandom();
            final BigInteger temp = new BigInteger(p.bitLength(), sr);
            if (temp.compareTo(BigInteger.ZERO) > 0 && temp.compareTo(p) < 0) {
                return temp;
            }
        }
    }
	
	
	

}
