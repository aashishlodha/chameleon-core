package org.aashish.chameleon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aashish.chameleon.generator.BaseGenerator;
import org.aashish.chameleon.model.Project;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class DataConverterTest {

	Logger logger = Logger.getLogger(getClass());
	Map<String, Object> projectMeta;
	List<Map<String, Object>> entities;
	Map<String, Object> classMeta;
	Map<String, String> instanceFields;
	
	@Before
	public void setUp() throws Exception {
		instanceFields = new HashMap<>();
		
		/*Map<String, String> salaryHM = new HashMap();
		salaryHM.put("type", "float");
		//salaryHM.put("isFK", false);*/		
		instanceFields.put("sample2", "Sample2");
		instanceFields.put("salary", "float");
		instanceFields.put("name", "String");
		//instanceFields.put("sample", "Sample2");
		
		classMeta = new HashMap<>();
		classMeta.put("packageName", "org.sample.test");
		classMeta.put("className", "Sample");
		classMeta.put("instanceFields", instanceFields);
		classMeta.put("pk", "name");
		
		Map<String, Object> classMeta2 = new HashMap<>();
		classMeta2.put("packageName", "org.sample.test");
		classMeta2.put("className", "Sample2");
		classMeta2.put("instanceFields", instanceFields);
		classMeta2.put("pk", "salary");
		
		entities = new ArrayList<>();
		entities.add(classMeta);
		entities.add(classMeta2);
		
		projectMeta = new HashMap<>();
		projectMeta.put("name" ,"Sample Project");
		projectMeta.put("basePackage" ,"org.sample.test");
		projectMeta.put("entities", entities);
	}

	//@Test
	public void test1() {
		Project project = new Project();
		project = DataConverter.HMToProject(projectMeta);
		logger.info("Project Meta >> \n" + project);
	}
	
	@Test
	public void test2() {
		String generatedSourceCode = BaseGenerator.generateSourceCode(projectMeta);
		logger.info("Generated Source Code >> \n" + generatedSourceCode);
	}

}
