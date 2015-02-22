package org.aashish.chameleon.generator;

import org.aashish.chameleon.model.Entity;
import org.aashish.chameleon.model.Property;

public class PojoGenerator extends Generator {

	@Override
	public void generatePackageName(StringBuilder str, String packageName,
			int indent) {
		//addIndent(indent, str);
		str.append("package " + packageName + ".model;");
	}

	@Override
	public void generateImportStatement(StringBuilder str, int indent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateClassName(StringBuilder str, Entity entity,
			int indent) {
		addIndent(indent, str);
		str.append("public class " + entity.getJavaName() + "{");
	}

	@Override
	public void generateField(StringBuilder str, Property property, Entity entity, int indent) {
		addIndent(indent, str);
		str.append("private " + property.getJavaDataType() + " " + property.getJavaName() + ";");
	}

	@Override
	public void generateGetterMethod(StringBuilder str, Property property, int indent) {
		addIndent(indent, str);
		str.append("public "+ property.getJavaDataType() + " get" + getCamelCase(property.getJavaName()) + "(){");
		addIndent(++indent, str);
		str.append("return this." + property.getJavaName() + ";");
		addIndent(--indent, str);
		str.append("}");
	}

	@Override
	public void generateSetterMethod(StringBuilder str, Property property, int indent) {
		addIndent(indent, str);
		str.append("public void set" + getCamelCase(property.getJavaName()) + "(" + property.getJavaDataType() + " " + property.getJavaName() + "){");
		addIndent(++indent, str);
		str.append("this." + property.getJavaName() + " = " + property.getJavaName() + ";");
		addIndent(--indent, str);
		str.append("}");
	}
	
}
