/*
 * 创建日期 2005-4-19
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.common;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class ConstantVariables {
	
	// database related.
	// =================
	public static int RESULTSET_DEFAULT_MAX_RETRIEVE=10000;
	
	// datasource name.  
	// Attention: if in a cluster envi., the name should be replace as a cluster/...name..
	public static String DATABASE_DATASOURCE = "jdbc/CCBEAIHDS";
	
	// ================= 
	
	// database content.
	public static String DATABASE_EXTENDEDMESSAGE_REQUEST="I";
	public static String DATABASE_EXTENDEDMESSAGE_RESPONSE="O";
	
	// class name
	public static String CLASS_TRANSACTION_STRUCTURE = "com.ibm.ccb.wsdlengine.entity.TransactionStructure";
	
	// schema
	public static String SCHEMA_BINARYARRAY = "base64Binary";
	
	// binding use
	public static String BINDING_USE = "literal";
	
	public static String DUPLICATE_SINGLE = "s";
	public static String DUPLICATE_MULTIPLE = "m";
	
}
