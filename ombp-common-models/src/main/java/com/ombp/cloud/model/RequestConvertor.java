package com.ombp.cloud.model;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ombp.cloud.utils.RandomStringCryptoUtil;

public class RequestConvertor<T> {

	private T object;
	
	public T getObject() {
		return object;
	}

	public RequestConvertor() 
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public RequestConvertor(T obj) throws NumberFormatException, InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

		if (obj instanceof BaseEntity) {
			if(((BaseEntity) obj).getPkId()!=null && ((BaseEntity) obj).getPkId().trim().length()!=0) {
			((BaseEntity) obj)
					.setId(Integer.valueOf(RandomStringCryptoUtil.getOriginalString(((BaseEntity) obj).getPkId())));
			}
		}
		
		if (obj instanceof String) {
			obj = (T) RandomStringCryptoUtil.getOriginalString((String) obj);
		}

		if (obj instanceof Collection<?>) {
			if (obj != null && ((Collection<?>) obj).size() != 0) {
				
				Iterator<?> iterator = ((Collection<?>) obj).iterator();

				while (iterator.hasNext()) {
					Object innerObj = iterator.next();
					if (innerObj instanceof BaseEntity) {
						if(((BaseEntity) innerObj).getPkId()!=null && ((BaseEntity) innerObj).getPkId().trim().length()!=0) {
						((BaseEntity) innerObj)
						.setId(Integer.valueOf(RandomStringCryptoUtil.getOriginalString(((BaseEntity) innerObj).getPkId())));
					}}
				}
			}
		}
		
		this.object = obj;
	}

	public List<String> getConvertedList(List<String> inputList) throws InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		if(inputList!=null && !inputList.isEmpty()) 
		{
			for(int i = 0; i < inputList.size(); i++) 
			{
				String inputObj = inputList.get(i);
				try {
					Integer.valueOf(inputObj);
					inputList.set(i, inputObj);
				} catch (Exception e) {
					inputList.set(i, RandomStringCryptoUtil.getOriginalString(inputObj));
				}
			}
		}
		
		return inputList;
	}
	
	public List<Integer> getConvertedIntegerList(List<String> inputList) throws InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		if(inputList!=null && !inputList.isEmpty()) 
		{
			for(int i = 0; i < inputList.size(); i++) 
			{
				String inputObj = inputList.get(i);
				try {
					Integer.valueOf(inputObj);
					inputList.set(i, inputObj);
				} catch (Exception e) {
					inputList.set(i, RandomStringCryptoUtil.getOriginalString(inputObj));
				}
			}
		}
		
		return inputList.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
	}
	
	public Set<String> getConvertedSet(Set<String> inputSet) throws InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		Set<String> outputSet = new HashSet<>();
		
		if(inputSet!=null && !inputSet.isEmpty()) 
		{
			for(String s : inputSet) 
			{
				try {
					Integer.valueOf(s);
					outputSet.add(s);
				} catch (Exception e) {
					outputSet.add(RandomStringCryptoUtil.getOriginalString(s));
				}
			}
		}
		
		return outputSet;
	}
	
	public Set<Integer> getConvertedIntegerSet(Set<String> inputSet) throws InvalidKeyException,
	InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException,
	NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
	Set<Integer> outputSet = new HashSet<>();
	
		if(inputSet!=null && !inputSet.isEmpty()) 
		{
				for (String s : inputSet) {
					Integer sInt = null;
					try {
						sInt = Integer.valueOf(s);
	
					} catch (Exception e) {
						sInt = Integer.valueOf(RandomStringCryptoUtil.getOriginalString(s));
					}
					outputSet.add(sInt);
				}
		}
	return outputSet;
	}
	
}
