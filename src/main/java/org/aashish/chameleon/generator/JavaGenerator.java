package org.aashish.chameleon.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;

public class JavaGenerator {
	
	Logger logger = Logger.getLogger(this.getClass());

	public String generateSourceCode(Map<String, Object> classData) {
		
		logger.info("Inside JavaGenerator.generateCode(Map<String, Object>)");
		logger.info("generating package name...");
		logger.debug("classData : " + classData);
		StringBuilder strBuilder = new StringBuilder("");

		logger.debug("packageName : " + classData.get("packageName"));
		if(classData.get("packageName") != null){
			strBuilder.append("package " + classData.get("packageName").toString() + ";");
		}

		if(! isValidMetadata(classData)){
			return "Invalid Data!";
		}
		//TODO: Generate Import Statements here
		//strBuilder.append("import ");
		
		logger.info("generating class name line...");
		logger.debug("className: " + classData.get("className"));
		if(classData.get("className") != null || ! classData.get("className").equals("") ){
			strBuilder.append("\npublic class " + classData.get("className").toString() + "{");

			logger.info("generating instance fields..");
			logger.debug("instanceFields: " + classData.get("instanceFields"));
			@SuppressWarnings("unchecked")
			Map<String, String> instanceFields = (Map<String, String>) classData.get("instanceFields");
			for (Map.Entry<String, String> entry : instanceFields.entrySet()) {
				String fieldName = entry.getKey();
				String fieldType = entry.getValue();

				strBuilder.append("\n\tprivate " + fieldType + " " + fieldName + ";");

			}

			logger.info("generating getters and setters..");
			for (Map.Entry<String, String> entry : instanceFields.entrySet()) {
				String fieldName = entry.getKey();
				String fieldType = entry.getValue();

				//Getter method
				generateGetterMethod(strBuilder, fieldName, fieldType);

				//Setter Method
				generateSetterMethod(strBuilder, fieldName, fieldType);
			}
			strBuilder.append("\n}");

			logger.debug("Generated java text : " + strBuilder.toString());

			logger.info("Writing file on disk...");
			if (writeToFile(classData.get("className").toString(), strBuilder.toString())) {
				logger.debug("file written successfully!");
			}else{
				logger.debug("Error occured writing file to disk!");
			}

			// Compiling a file..
			try {
				runProcess("javac " + "user/data/" + classData.get("className").toString() + ".java");
			} catch (Exception ex) {
				ex.printStackTrace();
				//Logger.getLogger(JavaFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		else{
			System.err.println("No Class name was found!!");
		}
		return "";
	}

	public boolean isValidMetadata(Map<String, Object> classData) {
		if(classData.get("className") == null){
			return false;
		}
		return true;
	}

	private String getCamelCase(String fieldName) {
		char firstChar = fieldName.charAt(0);
		char firstCharUpper = Character.toUpperCase(firstChar);
		String camelName = fieldName;
		return camelName.replaceFirst("" + firstChar, "" + firstCharUpper);
	}

	private void generateGetterMethod(StringBuilder stringBuilder, String fieldName, String fieldType){

		stringBuilder.append("\n\tpublic "+ fieldType + " get" + getCamelCase(fieldName) + "(){");
		stringBuilder.append("\n\t\treturn this." + fieldName + ";");
		stringBuilder.append("\n\t}");
	}

	private void generateSetterMethod(StringBuilder stringBuilder, String fieldName, String fieldType){
		stringBuilder.append("\n\tpublic void set" + getCamelCase(fieldName) + "(" + fieldType + " " + fieldName + "){");
		stringBuilder.append("\n\t\tthis." + fieldName + " = " + fieldName + ";");
		stringBuilder.append("\n\t}");
	}

	@SuppressWarnings("unused")
	private String generateConstructor(){

		return null;
	}

	private boolean writeToFile(String fileName, String content){
		PrintWriter writer = null;
		try {
			File file = new File("user/data/" + fileName + ".java");
			file.getParentFile().mkdirs();
			//writer = new PrintWriter(, "UTF-8");
			writer = new PrintWriter(file,"UTF-8");
			writer.println(content);
			writer.close();
			return true;
		} catch (FileNotFoundException ex) {
			//Logger.getLogger(JavaGenerator.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedEncodingException ex) {
			//Logger.getLogger(JavaGenerator.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			writer.close();
		}
		return false;
	}

	private void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
		logger.debug(command + " exitValue() " + pro.exitValue());
	}
	
	//TODO toString
	public void generateToString(StringBuilder stringBuilder, Map<String, Object> instanceFields,String className) {
		
		stringBuilder.append("@Override\npublic String toString(){"
				+ "\n return getClass().getSimpleName() + \": {\" + }");
	}
	

	private void printLines(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(
				new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			logger.debug(name + " " + line);
		}
	}

}
