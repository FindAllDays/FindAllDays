package com.ift.imywork.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Password {

    // The higher the number of iterations the more 
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int iterations = 20*1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database. 
        Empty passwords are not supported. */
    public static String getSaltedHashRev(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds 
        to a stored salted hash of the password. */
    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        return Base64.encodeBase64String(key.getEncoded());
    }
    private static final String Instance = "MD5";
	  private static final String key = "AquaticApp"; // 128 bit key
	  private static String charArr="xBzypornks";
	  private static String numS="0123456789";
	
	public static String getSaltedHash(String input) throws Exception {
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
}
