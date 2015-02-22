package org.aashish.chameleon.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyHandler {
	
	static Logger logger = Logger.getLogger(PropertyHandler.class);
	static Properties properties = new Properties();
	
	public PropertyHandler(){
		String propFileName = "/generator.properties";
		
		try{
			InputStream inputStream = PropertyHandler.class.getClassLoader().getResourceAsStream(propFileName);
			logger.info("istream >> " + inputStream);
			properties.load(inputStream);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		logger.info("Properties loaded >> " + properties);
	}
	
	public static String getProperty(String key){
		new PropertyHandler();
		return properties.getProperty(key);
	}

}
