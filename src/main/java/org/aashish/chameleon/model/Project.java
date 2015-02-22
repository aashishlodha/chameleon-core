package org.aashish.chameleon.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String name = "Sample";
	private String basePackage = "";
	private List<Entity> entities = new ArrayList<Entity>();
	
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Project [name=" + name + ", basePackage=" + basePackage + ", entities=" + entities
				+ "]";
	}
	
}
