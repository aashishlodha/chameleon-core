package org.aashish.chameleon.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aashish.chameleon.model.Entity;
import org.aashish.chameleon.model.Project;
import org.aashish.chameleon.model.Property;
import org.apache.log4j.Logger;

public class DataConverter {
	
	private static Logger logger = Logger.getLogger(DataConverter.class); 
	
	public static Project HMToProject(Map<String,Object> hm){
		Project project = new Project();
		String basePackage = hm.get("basePackage").toString();
		project.setBasePackage(basePackage);
		project.setName(hm.get("name").toString());
		

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> entitiesMeta = (List<Map<String, Object>>) hm.get("entities");
		List<Entity> entities = HMToEntity(entitiesMeta);
		project.setEntities(entities);
		return project;
	}
	
	public static List<Entity> HMToEntity(List<Map<String, Object>> entitiesMeta){
		
		List<Entity> entities = new ArrayList<>();
		
		for (Map<String,Object> classData : entitiesMeta) {
			logger.info("Creating an Entity : " + classData);

			Entity entity = new Entity();
			//logger.debug("packageName : " + classData.get("packageName"));
			if(classData.get("packageName") != null){
				entity.setPackageName(classData.get("packageName").toString());
			}
			
			if(classData.get("className") != null){
				entity.setJavaName(classData.get("className").toString());
				// TODO: use the name if given or use standard convention for name
				entity.setDbName(entity.getJavaName());
				entity.setXmlName(entity.getJavaName());
			}
			
			if(classData.get("pk") != null){
				entity.setPkProperty(classData.get("pk").toString());
			}
			
			List<Property> properties = null;
			@SuppressWarnings("unchecked")
			Map<String, String> propertyMeta = (Map<String, String>) classData.get("instanceFields");
			properties = HMToPropertyList(propertyMeta);
			entity.setProperties(properties);
			
			entities.add(entity);
		}
		return entities;
	}
	
	public static List<Property> HMToPropertyList(Map<String, String> propertyMeta){
		
		List<Property> properties = new ArrayList<>();
		for (Map.Entry<String, String> entry : propertyMeta.entrySet()) {
			String fieldName = entry.getKey();
			String fieldType = entry.getValue();

			//TODO set the appropriate names and data types
			Property property = new Property();
			property.setDbDataType(fieldType);
			property.setDbName(fieldName);
			property.setJavaDataType(fieldType);
			property.setJavaName(fieldName);
			property.setXmlDataType(fieldType);
			property.setXmlName(fieldName);
			
			properties.add(property);
		}
		return properties;
	}

	public static String ConvertDBTypes(String type){
		
		if(type.equals("integer"))
			return "int";
		else if(type.equals("decimal"))
			return "decimal(9,2)";
		else if(type.equals("date"))
			return "Date";
		else if(type.equals("datetime"))
			return "DateTime";
		else if(type.equals("time"))
			return "time";
		else if(type.equals("string"))
			return "varchar(50)";
		else if(type.equals("char"))
			return "char(50)";
		else if(type.equals("date"))
			return "Date";
		return "Unknown Type";
	}
	
	public static boolean isNumeric(String type){
		switch (type) {
		case "double":
			return true;
		case "int":
			return true;
		case "float":
			return true;
		case "long":
			return true;
		case "byte":
			return true;
		case "short":
			return true;
		default:
			break;
		}
		return false;
	}
	
	public static boolean isJavaDataType(String type){
		if(isNumeric(type))
			return true;
		
		switch (type) {
		case "boolean":
			return true;
		case "String":
			return true;
		case "Date":
			return true;
		case "char":
			return true;
		default:
			break;
		}
		return false;
	}
	
	public static boolean isFK(String type){
		if(isJavaDataType(type))
			return false;
		return true;
	}
	
}
