package org.aashish.chameleon.io;

import java.net.URLClassLoader;

import org.junit.Before;
import org.junit.Test;

public class BaseLoaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		URLClassLoader ucl = null;
		ucl = BaseLoader.loadClassesFromDir(ucl, "SAMPLE", "DD", "DD");
		System.out.println("Loaded classes from directory..");
		Class<?> c = BaseLoader.getClassByName(ucl, "org.sample.test.model.Sample");
		System.out.println("loaded class >> " + c.getCanonicalName());
	}

}
