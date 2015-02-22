package org.aashish.chameleon.model;

public class BasicEntity {
	
	private String javaName;
	private String xmlName;
	private String dbName;
	public String getJavaName() {
		return javaName;
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getXmlName() {
		return xmlName;
	}
	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	// Useful in subclasses
	@Override
	public String toString() {
		return "javaName=" + javaName + ", xmlName=" + xmlName
				+ ", dbName=" + dbName;
	}
	
}
