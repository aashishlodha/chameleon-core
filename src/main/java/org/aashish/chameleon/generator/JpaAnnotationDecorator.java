package org.aashish.chameleon.generator;

import org.aashish.chameleon.model.Entity;
import org.aashish.chameleon.model.Property;
import org.aashish.chameleon.util.DataConverter;

public class JpaAnnotationDecorator extends AnnotationDecorator {

	public JpaAnnotationDecorator(Generator gen) {
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
		
		// Add the JPA imports
		addIndent(indent, str);
		str.append("import javax.persistence.Entity;");
		addIndent(indent, str);
		str.append("import javax.persistence.Id;");
		addIndent(indent, str);
		str.append("import javax.persistence.Column;");
		addIndent(indent, str);
		str.append("import javax.persistence.FetchType;");
		addIndent(indent, str);
		str.append("import javax.persistence.GeneratedValue;");
		addIndent(indent, str);
		str.append("import javax.persistence.GenerationType;");
		addIndent(indent, str);
		str.append("import javax.persistence.JoinColumn;");
		addIndent(indent, str);
		str.append("import javax.persistence.ManyToOne;");
		addIndent(indent, str);
		str.append("import javax.persistence.Table;");
		
		// Delegate call to PojoGenerator
		generator.generateImportStatement(str, indent);
		
	}

	@Override
	public void generateClassName(StringBuilder str, Entity entity,int indent) {
		//Add the JPA annotations above Class name
		addIndent(indent, str);
		str.append("@Entity");
		addIndent(indent, str);
		str.append("@Table(name=\""+ entity.getDbName() + "\")");
		generator.generateClassName(str, entity, indent);
	}

	@Override
	public void generateField(StringBuilder str, Property property, Entity entity, int indent) {
		//Add the JPA annotations above field name
		//addIndent(indent, str);
		getJPAPropAnnotation(str, property, entity.getPkProperty(), indent);
		generator.generateField(str, property, entity, indent);
	}

	@Override
	public void generateGetterMethod(StringBuilder str, Property property, int indent) {
		generator.generateGetterMethod(str, property, indent);
		
	}

	@Override
	public void generateSetterMethod(StringBuilder str, Property property, int indent) {
		generator.generateSetterMethod(str, property, indent);
	}
	
	public void getJPAPropAnnotation(StringBuilder str, Property property, Object pk, int indent) {
		
		//Single column key
		if(pk instanceof String){
			if(pk.equals(property.getJavaName())){
				addIndent(indent, str);
				str.append("@Id");
				addIndent(indent, str);
				str.append("@GeneratedValue(strategy = GenerationType.AUTO)");
			}
		}
		//Multicolumn key
		else if(pk instanceof String[]){
			//TODO Combined PK handler
		}
		// FK resolution
		if(DataConverter.isFK(property.getJavaDataType())){
			addIndent(indent, str);
			// TODO decide relationship cardinality
			str.append("@ManyToOne");
			addIndent(indent, str);
			str.append("@JoinColumn(name = \""+ getCamelCase(property.getJavaDataType()) +"Id\")");
		}
		else{
			addIndent(indent, str);
			str.append("@Column(name = \""+ property.getDbName() + "\")");
		}
	}

}
