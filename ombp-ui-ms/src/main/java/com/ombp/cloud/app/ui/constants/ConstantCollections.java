package com.ombp.cloud.app.ui.constants;

import java.util.ArrayList;
import java.util.List;

public class ConstantCollections {

	public static List<String> FRAGMENT_CLEANER_KEYWORDS = new ArrayList<>();
	
	static 
	{
		FRAGMENT_CLEANER_KEYWORDS.add("<!DOCTYPE HTML>");
		FRAGMENT_CLEANER_KEYWORDS.add("<html>");
		FRAGMENT_CLEANER_KEYWORDS.add("<body>");
		FRAGMENT_CLEANER_KEYWORDS.add("</body>");
		FRAGMENT_CLEANER_KEYWORDS.add("</html>");
	}
	
	
	
}
