package org.aashish.chameleon.generator;

import org.aashish.chameleon.model.Entity;
import org.aashish.chameleon.model.Property;

public abstract class Generator {

	public abstract void generatePackageName(StringBuilder str, String packageName, int indent);
	
	public abstract void generateImportStatement(StringBuilder str, int indent);
	
	public abstract void generateClassName(StringBuilder str, Entity entity, int indent);
	
	public abstract void generateField(StringBuilder str, Property property, Entity entity, int indent);
	
	public abstract void generateGetterMethod(StringBuilder str, Property property, int indent);
	
	public abstract void generateSetterMethod(StringBuilder str, Property property, int indent);
	
	public void addIndent(int indent, StringBuilder str){
		str.append("\n");
		for(int i=0;i<indent;i++){
			str.append("\t");
		}
	}
	
	public String getCamelCase(String fieldName) {
		char firstChar = fieldName.charAt(0);
		char firstCharUpper = Character.toUpperCase(firstChar);
		String camelName = fieldName;
		return camelName.replaceFirst("" + firstChar, "" + firstCharUpper);
	}
	
}
