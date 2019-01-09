package com.threshold.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Pick a prime number");
		
	    int p = sc.nextInt();
	    
	    boolean checkPrime = PrimeStorage.checkPrime(p);
	    if(checkPrime){
	    	
	    	System.out.println("Generating value for g");
	    	ArrayList<Integer> gen = generatePublicParameters.getGenerators(p);
	    	
	    	/*for(int i=0;i<gen.size();i++){
	    		System.out.println("The generators are: "+gen.get(i));
	    	}*/
	    	int randomNum = ThreadLocalRandom.current().nextInt(0,gen.size()/2);
	    	
	    	
	    	
	    	System.out.println("Dealer setting private key ");
	    	BigInteger privateKey = BigInteger.valueOf(ThreadLocalRandom.current().nextInt(1,p-2));
	    	
	    	
	    	
	    	System.out.println("---------Public parameters are---------");
	    	BigInteger g = BigInteger.valueOf(gen.get(randomNum));
	    	System.out.println("g is "+ g);
	    	System.out.println("p is "+ p);
	    	System.out.println("G is cyclic group of order  "+ p);
	    	BigInteger h = g.modPow(privateKey,BigInteger.valueOf(p));
	    	System.out.println("Public key is "+h);
	    	
	    	
	    	System.out.println("--------Private key only known to the trusted third party is--------");
	    	System.out.println(privateKey);
	    	
	    	
	    	System.out.println("-------Distributing shares of private key to parties----------------");
	    	Shamir sh = new Shamir();
	    	sh.setShamirParmaters(5,3,privateKey,BigInteger.valueOf(p-1));
	    	sh.setPartyShares();
	    	HashMap <Integer,BigInteger> shares = sh.getPartyShares();
	    	sh.printPartyShares();
	    	
	    	
	    	System.out.println("-------Encryption part-------------");
	    	BigInteger randomKey = BigInteger.valueOf(ThreadLocalRandom.current().nextInt(1,p-1));
	    	System.out.println("Random key is "+randomKey);
	    	BigInteger message = BigInteger.valueOf(4);
	    	System.out.println("Message to be enc is "+message);
	    	BigInteger cipher1 = g.modPow(randomKey, BigInteger.valueOf(p));
	    	BigInteger cipher2 = h.modPow(randomKey, BigInteger.valueOf(p));
	    	cipher2 = (cipher2.multiply(message)).mod(BigInteger.valueOf(p));
	    	System.out.println("Cipher 1 is  "+cipher1);
	    	System.out.println("Chipher2 is  "+cipher2);
	    	
	    	
	    	System.out.println("--------------Distributed decyrption---------------------------");
	    	BigInteger[] indexes = {BigInteger.valueOf(2),BigInteger.valueOf(4),BigInteger.valueOf(5)};
	    	Decryption d = new Decryption();
	    	Decryption d1 = new Decryption();
	    	Decryption d3 = new Decryption();
	    	//d.decryptionShareCompute(cipher2, cipher1,shares.get(2),BigInteger.valueOf(2),indexes,BigInteger.valueOf(19));
	    	d.decryptionShareCompute(cipher2, cipher1,shares.get(2),BigInteger.valueOf(2),indexes,BigInteger.valueOf(p));
	    	BigInteger share1 = d.getDecryptionShare();
	    	System.out.println(share1);
	    	d1.decryptionShareCompute(share1, cipher1,shares.get(4), BigInteger.valueOf(4),indexes,BigInteger.valueOf(p));
	    	BigInteger share2 = d1.getDecryptionShare();
	    	System.out.println(share2);
	    	d3.decryptionShareCompute(share2, cipher1,shares.get(5),BigInteger.valueOf(5),indexes,BigInteger.valueOf(p));
	    	BigInteger share3 = d3.getDecryptionShare();
	    	System.out.println(share3);
	    	
	    	
	    }else{
	    	System.out.println("This is not a prime.Start again!");
	    }

	}

}
