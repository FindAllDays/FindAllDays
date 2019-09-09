package com.ift.imywork.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
	  private static final String Instance = "MD5";
	  private static final String key = "PKS123"; // 128 bit key
	  private static String charArr="xBzypornks";
	  private static String numS="0123456789";
	public static String generatePassword( int len) {
		char[] validchars = {'a','b','c','d','e','f','g','h','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z'
							,'A','B','C','D','E','F','G','H','J','K','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'
							,'1','2','3','4','5','6','7','8','9'};
		    char[] password = new char[len];
		    Random rand = new Random(System.nanoTime());
		    for (int i = 0; i < len; i++) {
		        password[i] = validchars[rand.nextInt(validchars.length)];
		    }
		    return new String(password);
		}
	public static String encrypt(String input) throws Exception {
		 String md5 = null;    	
		try {
			    input+=key;
		        //Create MessageDigest object for MD5
		        MessageDigest digest = MessageDigest.getInstance(Instance);
		         
		        //Update input string in message digest
		        digest.update(input.getBytes(), 0, input.length());
		 
		        //Converts message digest value in base 16 (hex) 
		        md5 = new BigInteger(1, digest.digest()).toString(16);
		 
		        } catch (NoSuchAlgorithmException e) {
		 
		            e.printStackTrace();
		        }
		        return md5;
    }
	
		 
	 public static String encryptPks(String data) throws Exception {
	      	String encryptedValue ="";
	      		for (int i = 0; i < data.length(); i++) {
					if(numS.indexOf((data.charAt(i)+""))!=-1){
						encryptedValue+=charArr.charAt(Integer.parseInt(data.charAt(i)+""));
					}
					else
						encryptedValue+=data.charAt(i)+"";
				}
	      	String key=Encrypt.encrypt("pks");
	        return key.substring(key.length()/2)+encryptedValue;
	    }

	    public static String decryptPKs(String encryptedData) throws Exception {
	        String decryptedValue ="";
	  		for (int i = 0; i < encryptedData.length(); i++) {
				if(charArr.indexOf(encryptedData.charAt(i)+"")!=-1){
					
					decryptedValue+=charArr.indexOf(encryptedData.charAt(i)+"")+"";
				}
				else
					decryptedValue+=encryptedData.charAt(i)+"";
			}
	  		String key=Encrypt.encrypt("pks");
	  				key= key.substring(key.length()/2);
	        return decryptedValue.replace(key, "");
	    }
      public static void main(String[] args) throws Exception {
    	  
         System.out.println(Encrypt.encryptPks("HN1506110010002"));
         System.out.println(Encrypt.decryptPKs("d69536ede66f3e75HNBoxrBBxxBxxxz"));
         
      }
}
