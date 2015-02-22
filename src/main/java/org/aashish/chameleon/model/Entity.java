package org.aashish.chameleon.model;

import java.util.ArrayList;
import java.util.List;

public class Entity extends BasicEntity {
	
	//private List<Operation> operations = new ArrayList<Operation>();
	private String packageName;
	private List<Property> properties = new ArrayList<Property>();
	private String pkProperty;

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getPkProperty() {
		return pkProperty;
	}

	public void setPkProperty(String pkProperty) {
		this.pkProperty = pkProperty;
	}

	@Override
	public String toString() {
		return "Entity [packageName=" + packageName + ", properties="
				+ properties + ", " + super.toString() + "]";
	}

}
