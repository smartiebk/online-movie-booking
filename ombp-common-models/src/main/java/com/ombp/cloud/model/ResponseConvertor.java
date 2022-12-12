package com.ombp.cloud.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.ombp.cloud.model.paging.Page;
import com.ombp.cloud.utils.RandomStringCryptoUtil;

public class ResponseConvertor<T> {

	private T object;

	public T getObject() {
		return object;
	}

	public ResponseConvertor(T obj) {

		if(obj!=null) 
		{
		
			if (obj instanceof BaseEntity) {
				if(((BaseEntity) obj).getId() !=null)
				{
					((BaseEntity) obj).setPkId(RandomStringCryptoUtil
							.getRandomStringWithSecretEmbeded(String.valueOf(((BaseEntity) obj).getId())));
					((BaseEntity) obj).setId(null);
				}
			}
			
			if(obj instanceof Page) 
			{
				List li = ((Page) obj).getData();
				
				if (li != null && li.size() != 0) {
					Iterator<?> iterator = li.iterator();
	
					while (iterator.hasNext()) {
						Object innerObj = iterator.next();
	
						if (innerObj instanceof BaseEntity) {
							if(((BaseEntity) innerObj).getId() !=null)
							{
								((BaseEntity) innerObj).setPkId(RandomStringCryptoUtil
										.getRandomStringWithSecretEmbeded(String.valueOf(((BaseEntity) innerObj).getId())));
								((BaseEntity) innerObj).setId(null);
							}
						}
					}
				}
			
			}
			
			if (obj instanceof Collection<?>) {
				if (obj != null && ((Collection<?>) obj).size() != 0) {
					Iterator<?> iterator = ((Collection<?>) obj).iterator();
	
					while (iterator.hasNext()) {
						Object innerObj = iterator.next();
	
						if (innerObj instanceof BaseEntity) {
							if(((BaseEntity) innerObj).getId() !=null)
							{
								((BaseEntity) innerObj).setPkId(RandomStringCryptoUtil
										.getRandomStringWithSecretEmbeded(String.valueOf(((BaseEntity) innerObj).getId())));
								((BaseEntity) innerObj).setId(null);
							}
						}
					}
				}
			}
		}
		
		this.object = obj;
	}

}
