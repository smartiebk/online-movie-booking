package com.ombp.cloud.utils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class RandomStringCryptoUtil {

	private static String salt = "$@1T";
	
	private static Map<String, String> NUMBER_CHAR_MAP = new HashMap<>();
	
	public static String getRandomStringWithSecretEmbeded(String input) {
		String output = input;
		try {
			String secret = String.valueOf(System.nanoTime());

			String encryptedOne = doCrypto(Cipher.ENCRYPT_MODE, input, secret, salt);

			output = embedSecretAndSaltIntoInput(encryptedOne, secret, salt);
			output = stringToHex(output);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static String getOriginalString(String input) throws InvalidKeyException, InvalidAlgorithmParameterException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, IOException {
		
		input = hexToString(input);

		Integer lengthOfSecret = Integer.valueOf(input.substring(1, 3));

		String secret = new StringBuilder(input.substring(4, 4 + lengthOfSecret)).reverse().toString();

		String encrypted = input.substring(0, 1) + input.substring(3, 4)
				+ input.substring(4 + lengthOfSecret, input.length());

		String encryptedOne = encrypted.substring(0, encrypted.length() - 5);

		String output = doCrypto(Cipher.DECRYPT_MODE, encryptedOne, secret, salt);

		return output;
	}

	private static String embedSecretAndSaltIntoInput(String encryptedOne, String secret, String salt) {

		int secretLen = secret.length();

		String preoutput = encryptedOne.substring(0, 1) + secretLen + encryptedOne.substring(1, 2)
				+ new StringBuilder(secret).reverse() + encryptedOne.substring(2, encryptedOne.length());

		String output = preoutput + getRandomAlphaNumericString(5);

		return output;
	}

	private static String doCrypto(int cipherMode, String inputString, String givenSecretKey, String salt)
			throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		IvParameterSpec ivspec = new IvParameterSpec(iv);

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(givenSecretKey.toCharArray(), salt.getBytes(), 65536, 128);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(cipherMode, secretKey, ivspec);

		String outputString = Cipher.ENCRYPT_MODE == cipherMode
				? Base64.getEncoder().encodeToString(cipher.doFinal(inputString.getBytes("UTF-8")))
				: new String(cipher.doFinal(Base64.getDecoder().decode(inputString)));

		return outputString;
	}

	public static String getRandomAlphaNumericString(int n) {

		// chose a Character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (alphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public static String stringToHex(String input) 
	{
		StringBuffer sb = new StringBuffer();
	      //Converting string to character array
	      char ch[] = input.toCharArray();
	      for(int i = 0; i < ch.length; i++) {
	         String hexString = Integer.toHexString(ch[i]);
	         sb.append(hexString);
	      }
	      return sb.toString();
	}

	public static String hexToString(String input) 
	{
		String result = new String();
	    char[] charArray = input.toCharArray();
	    for(int i = 0; i < charArray.length; i=i+2) {
	       String st = ""+charArray[i]+""+charArray[i+1];
	       char ch = (char)Integer.parseInt(st, 16);
	       result = result + ch;
	    }
	    return result;
	}
	
	private static Map<String, String> getCharNumberMap()
	{
		if(NUMBER_CHAR_MAP.isEmpty()) 
		{
			NUMBER_CHAR_MAP.put("0", "q,e,h,b");
			NUMBER_CHAR_MAP.put("1", "u,g,y");
			NUMBER_CHAR_MAP.put("2", "m,n");
			NUMBER_CHAR_MAP.put("3", "i,x");
			NUMBER_CHAR_MAP.put("4", "l,a");
			NUMBER_CHAR_MAP.put("5", "c,s,f");
			NUMBER_CHAR_MAP.put("6", "t,d");
			NUMBER_CHAR_MAP.put("7", "k,p,j");
			NUMBER_CHAR_MAP.put("8", "w,o");
			NUMBER_CHAR_MAP.put("9", "v,z,r");
			
			NUMBER_CHAR_MAP.put("a", "4");
			NUMBER_CHAR_MAP.put("b", "0");
			NUMBER_CHAR_MAP.put("c", "5");
			NUMBER_CHAR_MAP.put("d", "6");
			NUMBER_CHAR_MAP.put("e", "0");
			NUMBER_CHAR_MAP.put("f", "5");
			NUMBER_CHAR_MAP.put("g", "1");
			NUMBER_CHAR_MAP.put("h", "0");
			NUMBER_CHAR_MAP.put("i", "3");
			NUMBER_CHAR_MAP.put("j", "7");
			NUMBER_CHAR_MAP.put("k", "7");
			NUMBER_CHAR_MAP.put("l", "4");
			NUMBER_CHAR_MAP.put("m", "2");
			NUMBER_CHAR_MAP.put("n", "2");
			NUMBER_CHAR_MAP.put("o", "8");
			NUMBER_CHAR_MAP.put("p", "7");
			NUMBER_CHAR_MAP.put("q", "0");
			NUMBER_CHAR_MAP.put("r", "9");
			NUMBER_CHAR_MAP.put("s", "5");
			NUMBER_CHAR_MAP.put("t", "6");
			NUMBER_CHAR_MAP.put("u", "1");
			NUMBER_CHAR_MAP.put("v", "9");
			NUMBER_CHAR_MAP.put("w", "8");
			NUMBER_CHAR_MAP.put("x", "3");
			NUMBER_CHAR_MAP.put("y", "1");
			NUMBER_CHAR_MAP.put("z", "9");
		}
		
		return NUMBER_CHAR_MAP;
	}
	
	public static String getCharIdFromInteger(Integer id) 
	{
		getCharNumberMap();
		
		StringBuilder charId = new StringBuilder();
		
		charId.append(getRandomAlphaNumericString(2));
		
		String incomingId = String.valueOf(id);
		
		char[] incomingIdArr = incomingId.toCharArray();
		
		for(int i=0; i< incomingIdArr.length; i++) 
		{
			String[] values = NUMBER_CHAR_MAP.get(String.valueOf(incomingIdArr[i])).split(",");
			
			int index = 0 + (int)(Math.random() * ((3 - 0) + 1));
			
			if(values.length > index) 
			{
				charId.append(values[index]);
			}
			else 
			{
				int newIndex = index - values.length;
				charId.append(values[newIndex]);
			}
			
		}
		
		return charId.toString();
	}
	
	public static Integer getIntegerIDFromCharId(String id) 
	{
		getCharNumberMap();
		
		Integer intId = -11;
		
		if(id!=null && id.length()>2) 
		{
			StringBuilder charId = new StringBuilder();
			
			String incomingId = id.substring(2, id.length());
			
			char[] incomingIdArr = incomingId.toCharArray();
			
			for(int i=0; i< incomingIdArr.length; i++) 
			{
				charId.append(NUMBER_CHAR_MAP.get(String.valueOf(incomingIdArr[i])));
			}
			try 
			{
				intId = Integer.parseInt(charId.toString());
			}
			catch(NumberFormatException e) 
			{
				//Ignore
			}
		}
		
		return intId;
	}
	
	public static String genrateOTP(final int lengthOfOTP) {

		StringBuilder generatedOTP = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();
		String otp = String.valueOf(System.nanoTime()).substring(0, 6);
		try {
		
			secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());
			
			for (int i = 0; i < lengthOfOTP; i++) {
				generatedOTP.append(secureRandom.nextInt(9));
			}
		} catch (NoSuchAlgorithmException e) {
			//
		}
		
		otp = generatedOTP.toString();
		
		return otp;
	}
	
}
