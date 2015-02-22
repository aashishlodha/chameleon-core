package org.aashish.chameleon.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

public class BaseLoader {
	
	public static Logger logger = Logger.getLogger(BaseLoader.class);
	
	public static URLClassLoader loadClassesFromDir(URLClassLoader ucl, String projectName, String className, String basePackage){
		//TODO remove param ucl
		try {
			URL classUrl = new URL("file:///E:/chameleon/Sample Project/src/");
			URL[] classUrls = { classUrl };
			ucl = new URLClassLoader(classUrls);
		} catch (MalformedURLException e) {
			logger.error("Error occurred while loading classes from directory : " + basePackage, e);
		}
		
		return ucl;
	}
	
	public static Class<?> getClassByName(URLClassLoader ucl, String className){
		
		Class<?> c = null;
		try {
			c = ucl.loadClass(className);
		} catch (ClassNotFoundException e) {
			logger.error("Error occurred while loading classes from loader : " + className, e);
		} catch (NoClassDefFoundError e) {
			logger.error("Class not found in loader : " + className, e);
		}
		return c;
	}

}
