package org.aashish.chameleon.generator;

import org.aashish.chameleon.model.Entity;
import org.aashish.chameleon.model.Property;

public class JaxpAnnotationDecorator extends AnnotationDecorator {

	public JaxpAnnotationDecorator(Generator gen) {
		super(gen);
	}

	@Override
	public void generatePackageName(StringBuilder str, String packageName,
			int indent) {
		// Delegate call to PojoGenerator
		generator.generatePackageName(str, packageName, indent);
	}

	@Override
	public void generateImportStatement(StringBuilder str, int indent) {
		addIndent(indent, str);
		str.append("import javax.xml.bind.annotation.XmlRootElement;");
		str.append("import javax.xml.bind.annotation.XmlElement;");
		
		// Delegate call to PojoGenerator
		generator.generateImportStatement(str, indent);
	}

	@Override
	public void generateClassName(StringBuilder str, Entity entity,
			int indent) {
		addIndent(indent, str);
		str.append("@XmlRootElement(name = \"" + entity.getXmlName() + "\")");
		generator.generateClassName(str, entity, indent);
	}

	@Override
	public void generateField(StringBuilder str, Property property, Entity entity, int indent) {
		// TODO Auto-generated method stub
		generator.generateField(str, property, entity, indent);
	}

	@Override
	public void generateGetterMethod(StringBuilder str, Property property, int indent) {
		addIndent(indent, str);
		str.append("@XmlElement(name = \""+ property.getXmlName() + "\")");
		generator.generateGetterMethod(str, property, indent);
	}

	@Override
	public void generateSetterMethod(StringBuilder str, Property property, int indent) {
		// TODO Auto-generated method stub
		generator.generateSetterMethod(str, property, indent);
	}

}
