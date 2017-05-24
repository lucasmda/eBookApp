package br.com.ebookapp.database.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandlerHelper {
	public static final boolean isBlankOrNull(String string)
	{
		return string == null || string.trim().length() == 0 || "null".equals(string);
	}

	public static final boolean isBlankOrNull(Object[] objects)
	{
		return objects == null || objects.length == 0;
	}

	public static final boolean isBlankOrNull(List<?> list)
	{
		return list == null || list.isEmpty();
	}

	public static final List<?> convertArrayToList(Object[] objects)
	{
		return (objects == null) ? new ArrayList<Object>() : new ArrayList<Object>(Arrays.asList(objects));
	}
	
	public static final String convertSystemDate(String date) 
	{
		return new StringBuilder()
				.append(date.substring(8, 10).toCharArray()).append("/")
				.append(date.substring(5, 7).toCharArray()).append("/")
				.append(date.substring(0, 4).toCharArray()).toString();
	}
}
