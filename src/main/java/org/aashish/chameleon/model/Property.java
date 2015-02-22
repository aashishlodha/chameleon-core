package org.aashish.chameleon.model;

public class Property extends BasicEntity {
	
	private String javaDataType;
	private String xmlDataType;
	private String dbDataType;
	
	private boolean isPK;
	private boolean isFK;
	
	public String getJavaDataType() {
		return javaDataType;
	}
	public void setJavaDataType(String javaDataType) {
		this.javaDataType = javaDataType;
	}
	public String getXmlDataType() {
		return xmlDataType;
	}
	public void setXmlDataType(String xmlDataType) {
		this.xmlDataType = xmlDataType;
	}
	public String getDbDataType() {
		return dbDataType;
	}
	public void setDbDataType(String dbDataType) {
		this.dbDataType = dbDataType;
	}
	
	public boolean isPK() {
		return isPK;
	}
	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}
	public boolean isFK() {
		return isFK;
	}
	public void setFK(boolean isFK) {
		this.isFK = isFK;
	}
	
	@Override
	public String toString() {
		return "Property [javaDataType=" + javaDataType + ", xmlDataType="
				+ xmlDataType + ", dbDataType=" + dbDataType + ", "
				+ super.toString() + "]";
	}
	
}
