package org.aashish.chameleon.output.handler;

import org.aashish.chameleon.io.BaseFileWriter;
import org.junit.Before;
import org.junit.Test;

public class JavaWriterTest {

	String fileName, packageName, projectName, extension;
	String inputStr;
	
	@Before
	public void setUp() throws Exception {
		fileName = "Test";
		packageName = "org.sample.test";
		projectName = "Test Project";
		inputStr = "Hello World!";
		extension = ".java";
	}

	@Test
	public void test() {
		String returnStr = BaseFileWriter.write(fileName, packageName, projectName, inputStr, extension);
		System.out.println("fileName >> " + returnStr);
	}

}
