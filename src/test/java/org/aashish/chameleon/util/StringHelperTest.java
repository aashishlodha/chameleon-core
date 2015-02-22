package org.aashish.chameleon.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	//@Test
	public void camelTest() {
		
		String iStr = "company Profile";
		String oStr = NamingConventionHelper.toCamelCase(iStr);
		System.out.println("oStr >> " + oStr);
	}
	
	@Test
	public void pascalTest() {
		
		String iStr = "company Profile";
		String oStr = NamingConventionHelper.toPascalCase(iStr);
		System.out.println("oStr >> " + oStr);
	}

}
