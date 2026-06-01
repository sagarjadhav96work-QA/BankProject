package com.automation.bankms.qa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManagerUtil {
	
	private LogManagerUtil()
	{
		
	}

	public static Logger getLogger(Class<?> Clazz)
	{
		
		return LoggerFactory.getLogger(Clazz);
	}
	
	
}
