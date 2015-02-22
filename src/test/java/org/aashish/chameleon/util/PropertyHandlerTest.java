package org.aashish.chameleon.util;

import org.junit.Before;
import org.junit.Test;

public class PropertyHandlerTest {

	@Before
	public void setUp() throws Exception {
		getClass().getClassLoader().loadClass("org.aashish.chameleon.util.PropertyHandler");
	}

	@Test
	public void test() {
		String property = PropertyHandler.getProperty("javax.persistence.path");
		System.out.println("javax.persistence.path >> " + property);
	}

}
