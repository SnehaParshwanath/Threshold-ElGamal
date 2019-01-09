package com.threshold.elgamal;
import java.math.BigInteger;



public class Decryption {
	
    private BigInteger decryptionShare;
    
    
	
	public void decryptionShareCompute(BigInteger mess,BigInteger cipher1,BigInteger share,BigInteger index,BigInteger[] thresholdXValues,BigInteger prime){
		
		BigInteger partialShare;
		BigInteger LagrangeInterpolation;
		System.out.println("share "+share);
		partialShare = cipher1.modPow(share,prime);
		System.out.println("partial share "+partialShare);
		Lagrange.setInterpolation(index, thresholdXValues, prime);
		LagrangeInterpolation = Lagrange.getInterpolation();
		System.out.println("Lagrange Interpolation "+LagrangeInterpolation);
		decryptionShare = partialShare.pow(LagrangeInterpolation.intValue());
		System.out.println("Decryption share is "+decryptionShare);
		System.out.println("mess is "+mess);
		decryptionShare = ((decryptionShare.modInverse(prime)).multiply(mess)).mod(prime);
        System.out.println("Decryption share is "+decryptionShare);
		
		
	}
	
   public BigInteger getDecryptionShare(){
	   return decryptionShare;
   }
	
	

}
