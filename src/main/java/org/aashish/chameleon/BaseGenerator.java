package org.aashish.chameleon;

import java.util.HashMap;

import org.aashish.chameleon.generator.JavaGenerator;
import org.apache.log4j.Logger;

public class BaseGenerator {
	
	static Logger logger = Logger.getLogger(BaseGenerator.class);
	
	public static void main(String[] args) {
		
		JavaGenerator generator = new JavaGenerator();
		String msg = generator.generateSourceCode(new HashMap<String, Object>());
		logger.info("Message : " + msg);
	}

}
