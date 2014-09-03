/*
 * �������� 2005-5-23
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.soapengine.core;

/**
 * @author abelwang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class SOAConstant {
	//Suffix of Request
	public static String SUFFIX_REQUEST = "Request";
	//Suffix of Response
	public static String SUFFIX_RESPONSE = "Response";
	//Target Namespace
	public static String CCB_EAI_TARGET_NS_PREFIX ="http://ibm.com/service/wsdl/";
	//XSD Namespace
	public static String CCB_EAI_XSD_NS = "http://www.w3.org/2001/XMLSchema";
	//Request ID
	public static String WSDL_REQUESTER_ID = "requester_id";
	//Transaction Serial Number
	public static String WSDL_TRANSACTION_SN = "transaction_sn";
	//Transaction Serial Number
	public static String WSDL_TRANSACTION_ID = "transaction_id";
	//Node Name for Request Extension
	public static String WSDL_REQ_EXTENSION = "RequestExtension";
	//Node Name for Response Extension
	public static String WSDL_RESP_EXTENSION = "ResponseExtension";
	//Node Name for Response Status
	public static String WSDL_RESP_STATUS = "ResponseStatus";
	//SOAP Body name
	public static String SOAP_BODY_NAME = "soapenv:Body";
	//SOAP Service name
	public static String SOAP_SERVICE_NAME = "tns:performService";

	//SOAP Service name
	public static String SOAP_MSG_REQUEST_TAG = "performService";
	//SOAP Service name
	public static String SOAP_MSG_RESPONSE_TAG = "performServiceResponse";	
	
	//Suffix of wsdl request
	public static String WSDL_SUFFIX = ".wsdl";
	//WSDL request URL
	public static String WSDL_URL = "Service/WSDL/";

}
