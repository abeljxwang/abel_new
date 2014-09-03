/*
 * 创建日期 2005-4-29
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entity;
import java.util.ArrayList;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class GroupDataStructure {

	/**
	 * 
	 */
	public GroupDataStructure() {
		super();

	}
	
	String metaID;
	
	ArrayList MessageItems ; 
	/**
	 * @return
	 */
	public ArrayList getMessageItems() {
		return MessageItems;
	}

	/**
	 * @return
	 */
	public String getMetaID() {
		return metaID;
	}

	/**
	 * @param list
	 */
	public void setMessageItems(ArrayList list) {
		MessageItems = list;
	}

	/**
	 * @param string
	 */
	public void setMetaID(String string) {
		metaID = string;
	}

}
