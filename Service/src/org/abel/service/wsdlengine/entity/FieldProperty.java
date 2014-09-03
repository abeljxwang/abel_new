/*
 * 创建日期 2005-4-19
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entity;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class FieldProperty {

	/**
	 * 
	 */
	public FieldProperty() {
		super();

	}
	
	// describe the name of the feild.
	private String messageName = null;
	
	// describe the data type of the feild.
	private String dataType = null;
	
	// describe wether it can be null.
	private boolean nillable = true;
	
	// describe default value.
	private String defaultValue = null;
	
	/**
	 * @return
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @return
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @return
	 */
	public String getMessageName() {
		return messageName;
	}

	/**
	 * @return
	 */
	public boolean isNillable() {
		return nillable;
	}

	/**
	 * @param string
	 */
	public void setDataType(String string) {
		dataType = string;
	}

	/**
	 * @param string
	 */
	public void setDefaultValue(String string) {
		defaultValue = string;
	}

	/**
	 * @param string
	 */
	public void setMessageName(String string) {
		messageName = string;
	}

	/**
	 * @param b
	 */
	public void setNillable(boolean b) {
		nillable = b;
	}

}
