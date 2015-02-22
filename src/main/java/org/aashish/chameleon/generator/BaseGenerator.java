package org.aashish.chameleon.generator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.aashish.chameleon.io.BaseFileWriter;
import org.aashish.chameleon.model.Entity;
import org.aashish.chameleon.model.Project;
import org.aashish.chameleon.model.Property;
import org.aashish.chameleon.util.DataConverter;
import org.apache.log4j.Logger;

public class BaseGenerator{
	
	static Logger logger = Logger.getLogger(BaseGenerator.class);
	
	public static String generateSourceCode(Map<String, Object> projectData) {
		Project project = DataConverter.HMToProject(projectData);
		
		//TODO: Project validation goes here
		
		List<Entity> entities = project.getEntities();
		for (Entity entity : entities) {
			String sourceFileText = generateBaseClass(entity);
			String sourceFilePath = BaseFileWriter.write(entity.getJavaName(), entity.getPackageName() + ".model", project.getName(), sourceFileText, ".java");
			
			logger.info("Compiling a file..");
//			String tagetPath = sourceFilePath.replaceFirst(File.separator + "src" + File.separator, File.separator + "target" + File.separator);
			//String tagetPath = sourceFilePath.replaceFirst("src", "target");
			try {
				//runProcess("javac \"" + sourceFilePath+"\" -d \"" + tagetPath + "\"");
				runProcess("javac -cp \"C:\\Users\\Aashish\\.m2\\repository\\org\\hibernate\\javax\\persistence\\hibernate-jpa-2.1-api\\1.0.0.Final\\hibernate-jpa-2.1-api-1.0.0.Final.jar\" \"" + sourceFilePath+"\"");
			} catch (Exception ex) {
				ex.printStackTrace();
				// Logger.getLogger(JavaFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		return null;
	}
	
	private static String generateBaseClass(Entity entity) {
		logger.info("Inside BaseGenerator.generateBaseClass2(Entity)");
		logger.debug("entity : " + entity);
		StringBuilder strBuilder = new StringBuilder();
		
		Generator generator = new JaxpAnnotationDecorator(new JpaAnnotationDecorator(new PojoGenerator()));
		int indent = 0;

		logger.info("generating package name...");
		logger.debug("packageName : " + entity.getPackageName());
		if(entity.getPackageName() != null){
			generator.generatePackageName(strBuilder, entity.getPackageName(), indent);
			strBuilder.append("\n");
		}
		
		/*if(! isValidMetadata(classData)){
			return "Invalid Data!";
		}*/
		//TODO: Generate Import Statements here needed for FK
		generator.generateImportStatement(strBuilder, indent);
		strBuilder.append("\n");
		
		logger.info("generating class name line...");
		logger.debug("className: " + entity.getJavaName());
		if(entity.getJavaName() != null || ! entity.getJavaName().equals("") ){
			
			generator.generateClassName(strBuilder, entity, indent);
			
			indent++;

			logger.info("generating instance fields..");
			logger.debug("instanceFields: " + entity.getProperties());
			List<Property> instanceFields = (List<Property>) entity.getProperties();
			for (Property property : instanceFields) {
				generator.generateField(strBuilder, property, entity, indent);
			}

			strBuilder.append("\n");
			logger.info("generating getters and setters..");
			for (Property property : instanceFields) {
				//Getter method
				generator.generateGetterMethod(strBuilder, property, indent);

				//Setter Method
				generator.generateSetterMethod(strBuilder, property, indent);
			}
//			indent--;
			strBuilder.append("\n}");

			logger.debug("Generated java text : \n" + strBuilder.toString());

			/*logger.info("Writing file on disk...");
			if (write(entity.getJavaName(), strBuilder.toString())) {
				logger.debug("file written successfully!");
			}else{
				logger.debug("Error occured writing file to disk!");
			}*/

		}
		else{
			System.err.println("No Class name was found!!");
		}
		return strBuilder.toString();
	}

	public static String getCamelCase(String fieldName) {
		char firstChar = fieldName.charAt(0);
		char firstCharUpper = Character.toUpperCase(firstChar);
		String camelName = fieldName;
		return camelName.replaceFirst("" + firstChar, "" + firstCharUpper);
	}
	
	public static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
		logger.debug(command + " exitValue() " + pro.exitValue());
	}
	
	public static void generateMavenProject(Project project) throws Exception{
		runProcess("mvn ");
	}
	
	public static void printLines(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(
				new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			logger.debug(name + " " + line);
		}
	}
	
	/*
	public static String getJPAPropAnnotation(Property property, Object pk, String indent) {
		
		String resultStr = "";
		//Single column key
		if(pk instanceof String){
			if(pk.equals(property.getJavaName())){
				resultStr += indent + "@Id" +
						indent + "@GeneratedValue(strategy = GenerationType.AUTO)";
			}
		}
		//Multicolumn key
		else if(pk instanceof String[]){
			//TODO Combined PK handler
		}
		return resultStr + indent + "@Column(name = \""+ property.getDbName() + "\")";
	}
	
	public static void generateGetterMethod(StringBuilder stringBuilder, String fieldName, String fieldType){

		stringBuilder.append("\n\tpublic "+ fieldType + " get" + getCamelCase(fieldName) + "(){");
		stringBuilder.append("\n\t\treturn this." + fieldName + ";");
		stringBuilder.append("\n\t}");
	}

	public static void generateSetterMethod(StringBuilder stringBuilder, String fieldName, String fieldType){
		stringBuilder.append("\n\tpublic void set" + getCamelCase(fieldName) + "(" + fieldType + " " + fieldName + "){");
		stringBuilder.append("\n\t\tthis." + fieldName + " = " + fieldName + ";");
		stringBuilder.append("\n\t}");
	}
	
	public static String generateSourceCode(Map<String, Object> projectData) {
		Project project = DataConverter.HMToProject(projectData);
		
		//TODO: Project validation goes here
		
		List<Entity> entities = project.getEntities();
		for (Entity entity : entities) {
			String sourceFileText = BaseGenerator.generateBaseClass(entity);
			String sourceFilePath = BaseFileWriter.write(entity.getJavaName(), entity.getPackageName() + ".model", project.getName(), sourceFileText, ".java");
			
			logger.info("Compiling a file..");
//			String tagetPath = sourceFilePath.replaceFirst(File.separator + "src" + File.separator, File.separator + "target" + File.separator);
			//String tagetPath = sourceFilePath.replaceFirst("src", "target");
			try {
				//runProcess("javac \"" + sourceFilePath+"\" -d \"" + tagetPath + "\"");
				runProcess("javac -cp \"C:\\Users\\Aashish\\.m2\\repository\\org\\hibernate\\javax\\persistence\\hibernate-jpa-2.1-api\\1.0.0.Final\\hibernate-jpa-2.1-api-1.0.0.Final.jar\" \"" + sourceFilePath+"\"");
			} catch (Exception ex) {
				ex.printStackTrace();
				// Logger.getLogger(JavaFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		return null;
	}

	private static String generateBaseClass(Entity entity) {
		logger.info("Inside BaseGenerator.generateBaseClass(Entity)");
		logger.info("generating package name...");
		logger.debug("entity : " + entity);
		StringBuilder strBuilder = new StringBuilder("");

		logger.debug("packageName : " + entity.getPackageName());
		if(entity.getPackageName() != null){
			strBuilder.append("package " + entity.getPackageName() + ".model;");
		}

//		if(! isValidMetadata(classData)){
//			return "Invalid Data!";
//		}
		//TODO: Generate Import Statements here needed for FK
		strBuilder.append("\nimport javax.persistence.Entity;");
		strBuilder.append("\nimport javax.persistence.Id;");
		strBuilder.append("\nimport javax.persistence.Column;");
		strBuilder.append("\nimport javax.persistence.FetchType;");
		strBuilder.append("\nimport javax.persistence.GeneratedValue;");
		strBuilder.append("\nimport javax.persistence.GenerationType;");
		strBuilder.append("\nimport javax.persistence.JoinColumn;");
		strBuilder.append("\nimport javax.persistence.ManyToOne;");
		strBuilder.append("\nimport javax.persistence.Table;");
		
		logger.info("generating class name line...");
		logger.debug("className: " + entity.getJavaName());
		if(entity.getJavaName() != null || ! entity.getJavaName().equals("") ){
			
			strBuilder.append("\n@Entity");
			strBuilder.append("\n@Table(name=\""+ entity.getDbName()+ "\")");
			strBuilder.append("\npublic class " + entity.getJavaName() + "{");

			logger.info("generating instance fields..");
			logger.debug("instanceFields: " + entity.getProperties());
			List<Property> instanceFields = (List<Property>) entity.getProperties();
			for (Property property : instanceFields) {
				String fieldName = property.getJavaName();
				String fieldType = property.getJavaDataType();

				strBuilder.append(getJPAPropAnnotation(property, entity.getPkProperty(), "\n\t"));
				strBuilder.append("\n\tprivate " + fieldType + " " + fieldName + ";");

			}

			logger.info("generating getters and setters..");
			for (Property property : instanceFields) {
				String fieldName = property.getJavaName();
				String fieldType = property.getJavaDataType();

				//Getter method
				generateGetterMethod(strBuilder, fieldName, fieldType);

				//Setter Method
				generateSetterMethod(strBuilder, fieldName, fieldType);
			}
			strBuilder.append("\n}");

			logger.debug("Generated java text : \n" + strBuilder.toString());

		}
		else{
			System.err.println("No Class name was found!!");
		}
		return strBuilder.toString();
	}*/
}
