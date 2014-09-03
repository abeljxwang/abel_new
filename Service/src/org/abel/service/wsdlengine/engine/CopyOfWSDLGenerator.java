/*
 * 创建日期 2005-4-12
 *
 */
package org.abel.service.wsdlengine.engine;

import java.io.ByteArrayOutputStream;
// import java.io.File;
// import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
// import java.util.List;

import javax.wsdl.Binding;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.Types;
// import javax.wsdl.extensions.schema.Schema;
// import javax.wsdl.extensions.schema.Schema;
// import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Name;
// import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;

import org.abel.service.soa.message.DescCacheManager;
import org.abel.service.soa.message.ElementDataDAO;
import org.abel.service.soa.message.ElementDesc;
import org.abel.service.soa.message.MetaDesc;
import org.abel.service.soa.message.ServiceDesc;
import org.abel.service.wsdlengine.entity.MessageStructure;
import org.abel.service.wsdlengine.entity.TransactionStructure;
import org.abel.service.wsdlengine.entityutil.MetaDataHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import com.ibm.wsdl.extensions.soap.SOAPAddressImpl;
import com.ibm.wsdl.extensions.soap.SOAPBindingImpl;
import com.ibm.wsdl.extensions.soap.SOAPBodyImpl;
import com.ibm.wsdl.extensions.soap.SOAPOperationImpl;

/**
 * @author daijun@cn.ibm.com
 * 
 */
public class CopyOfWSDLGenerator {

	// define namespace
	public static final String tnsBase = "http://www.cnpc.com/Service/wsdl/";
	public static final String TNS = "http://www.cnpc.com/Service/wsdl/";
	public static final String XSD = "http://www.w3.org/2001/XMLSchema";
	public static final String WSDL = "http://schemas.xmlsoap.org/wsdl/";
	public static final String SOAP = "http://schemas.xmlsoap.org/wsdl/soap/";
	public static final String HTTP = "http://schemas.xmlsoap.org/soap/http";
	public static final String SOAPENC = "http://schemas.xmlsoap.org/soap/encoding/";
	public static final String OPERATION_NAME = "performService";
	public static final String SOAP_ADDRESS = "http://www.cnpc.com/Service/SOAP/";
	public static WSDLCacheManager wsdlcm = null;
	public static TransactionStructure transactionStructure = null;
	public String _serviceName = "Well_Mgr";
	public String _operationName = "query_Well_Info";

	SOAPFactory sf = null;

	public CopyOfWSDLGenerator() {
		try {
			if (sf == null)
				sf = SOAPFactory.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		DescCacheManager dc=DescCacheManager.getInstance();
		
		ServiceDesc ms = dc.getServiceDesc("Well_Mgr");
		//ms.setChineseName("井信息维护");
		//ms.setEndPoint(ep)("井信息维护");
		
		

		CopyOfWSDLGenerator temp = new CopyOfWSDLGenerator();
		temp.generatorSingleWSDL(ms);

	}

	/**
	 * Generate WSDL for a transaction by a transactionID
	 * 
	 * @param service_ID
	 * @return
	 */
	public OutputStream generatorSingleWSDL(ServiceDesc service_Structuretmp) {

		ByteArrayOutputStream result = new ByteArrayOutputStream();
		String tns = tnsBase;

		try {

			// get wsdlCacheManger instance
			if (null == wsdlcm) {
				// wsdlcm = WSDLCacheManager.getInstance();
			}
			String servname=service_Structuretmp.getName();
			System.out.println(servname+"------------------");
			
			
			//String op_name=service_Structuretmp.getOperation(key)
			
			
			WSDLFactory factory = WSDLFactory.newInstance();
			WSDLWriter writer = factory.newWSDLWriter();

			// The Definition interface presents a WSDL definition.
			Definition def = factory.newDefinition();
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();

			// get transaction structure information.
			//transactionStructure = transactionStructuretmp;
			// wsdlcm.getTransactionStructure(transactionID);

			// ---------------------------------------------
			// create namespace
			// ---------------------------------------------
			def.setQName(new QName(tnsBase, _serviceName));
			def.setTargetNamespace(tnsBase);
			def.addNamespace("tns", tnsBase);
			def.addNamespace("xsd", XSD);
			def.addNamespace("soap", SOAP);
			def.addNamespace("soapenc", SOAPENC);
			def.addNamespace("http", HTTP);

			//Add types section
			def.setTypes(typesConstructor(doc, def));
			
			//Add Message section
			Message reqMessage = requestMessageConstructor(def, _operationName);
			Message respMessage = responseMessageConstructor(def, _operationName);
			reqMessage.setUndefined(false);
			respMessage.setUndefined(false);
			def.addMessage(reqMessage);
			def.addMessage(respMessage);
			
			//Add portType Section
			PortType portType = portTypeConstructor(reqMessage, respMessage,
					servname,	_operationName, def);
			def.addPortType(portType);
			
			//Add binding section
			Binding binding = bindingConstructor(servname,_operationName, def, portType);
			def.addBinding(binding);
			
			//Add service section
			def.addService(serviceConstructor(servname, def, binding));

			writer.writeWSDL(def, System.out);
			writer.writeWSDL(def, result);
			// File outFile = new File("C:\\daijun\\temp\\a.xml");
			// FileWriter out = new FileWriter(outFile);
			// out.write(result.toString());

			// System.out.println(def.toString());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;

	}



	/**
	 * Generate Types
	 * 
	 * @param doc
	 * @param bcm
	 * @param def
	 * @param host
	 * @return
	 */
	private Types typesConstructor(Document doc, Definition def) throws Exception {
		Types types = def.createTypes();

		// Schema schema = new SchemaImpl();
		// schema.setElement(schemaConstructor(doc, def));
		// schema.setElementType(new QName(XSD, "schema"));

		// types.addExtensibilityElement(schema);

		types.setDocumentationElement(schemaConstructor(doc, def));

		return types;
	}

	/**
	 * Generate Schema
	 * 
	 * @param doc
	 * @param bcm
	 * @param def
	 * @param host
	 * @return
	 */
	private Element schemaConstructor(Document doc, Definition def) throws Exception {

		SOAPFactory sf = SOAPFactory.newInstance();
		Name bodyName = sf.createName("schema", "xsd", XSD);

		// Element schemaElement = doc.ccreateElement("schema");
		Element schemaElement = sf.createElement(bodyName);
		schemaElement.setAttribute("targetNamespace", TNS);
		schemaElement.setAttribute("xmlns", XSD);

		ArrayList msg = null;

		// construct request message.
		Element requestElement = complexDataOperationConstructor(doc,
				_operationName, null);
		schemaElement.appendChild(requestElement);

		// construct response message
		Element responseElement = complexDataOperationConstructor(doc,
				_operationName + "Response", null);
		schemaElement.appendChild(responseElement);

		// Complex data type -- response status
		Element responseStatus = responseStatus(doc);
		schemaElement.appendChild(responseStatus);


		return schemaElement;
	}

	/**
	 * Generate ComplexType element of performService
	 * 
	 * @param doc
	 * @return Element
	 */
	private Element performService(Document doc) {
		Element belement = doc.createElement("element");
		belement.setAttribute("name", "performService");

		Element requestElement = doc.createElement("complexType");
		// responseStatusElement.setAttribute("name", "ResponseStatus");
		Element sequence = doc.createElement("sequence");
		sequence.appendChild(prepareRefElement(doc, "tns:requestExtension"));

		sequence.appendChild(prepareElement(doc, "transaction_sn",
				"xsd:string", false));
		sequence.appendChild(prepareElement(doc, "transaction_id",
				"xsd:string", false));
		sequence.appendChild(prepareElement(doc, "requester_id", "xsd:string",
				false));

		ArrayList requestPartArray = transactionStructure.getRequestMessage();
		MessageStructure fieldTemp = null;
		String name, value, type;

		for (int i = 0; i < requestPartArray.size(); i++) {

			// partFeild = def.createPart();
			fieldTemp = (MessageStructure) requestPartArray.get(i);
			name = fieldTemp.getRequesterFieldName();

			sequence.appendChild(prepareElement(doc, name, fieldTemp
					.getWsdlType(), true));
		}

		requestElement.appendChild(sequence);
		belement.appendChild(requestElement);

		return belement;
	}

	/**
	 * Generate ComplexType element of performService
	 * 
	 * @param doc
	 * @return Element
	 */
	private Element performServiceResponse(Document doc) {
		Element belement = doc.createElement("element");
		belement.setAttribute("name", "performServiceResponse");

		Element responseElement = doc.createElement("complexType");
		// responseStatusElement.setAttribute("name", "ResponseStatus");
		Element sequence = doc.createElement("sequence");
		sequence.appendChild(prepareRefElement(doc, "tns:responseExtension"));
		sequence.appendChild(prepareRefElement(doc, "tns:responseStatus"));
		sequence.appendChild(prepareElement(doc, "transaction_sn",
				"xsd:string", false));
		sequence.appendChild(prepareElement(doc, "transaction_id",
				"xsd:string", false));
		sequence.appendChild(prepareElement(doc, "requester_id", "xsd:string",
				false));

		MessageStructure fieldTemp = null;
		String name, value, type;

		ArrayList responsePartArray = transactionStructure.getResponseMessage();

		for (int i = 0; i < responsePartArray.size(); i++) {

			fieldTemp = (MessageStructure) responsePartArray.get(i);

			sequence.appendChild(prepareElement(doc, fieldTemp
					.getRequesterFieldName(), fieldTemp.getWsdlType()));

		}

		responseElement.appendChild(sequence);
		belement.appendChild(responseElement);

		return belement;
	}

	/**
	 * Generate ComplexType element of ResponseStatus
	 * 
	 * @param doc
	 * @return Element
	 */
	private Element responseStatus(Document doc) {
		Element belement = doc.createElement("element");
		belement.setAttribute("name", "responseStatus");

		Element responseStatusElement = doc.createElement("complexType");
		// responseStatusElement.setAttribute("name", "ResponseStatus");
		Element sequence = doc.createElement("sequence");

		// -------------------
		// so far, the fields for responseStatus is fixed.
		// Any modification to responseStatus, pls. add elements below.
		// Here, I use clone, which may make some confusing. Pls. pay attention
		// to using it. Sorry for it.
		// -------------------

		sequence.appendChild(prepareElement(doc, "status", "xsd:string"));
		sequence.appendChild(prepareElement(doc, "code", "xsd:string"));
		sequence.appendChild(prepareElement(doc, "desc", "xsd:string"));

		responseStatusElement.appendChild(sequence);
		belement.appendChild(responseStatusElement);
		return belement;
	}

	/**
	 * Generate Complex Data Type -- Array
	 * 
	 * @param doc
	 * @return Element
	 */
	private Element ArrayDataType(Document doc) {
		Element array = doc.createElement("complexType");
		array.setAttribute("name", "ArrayOfString");
		Element complexContent = doc.createElement("complexContext");
		Element restriction = doc.createElement("restriction");
		restriction.setAttribute("base", "soapenc:Array");
		Element attribute = doc.createElement("attribute");
		attribute.setAttribute("ref", "soapenc:arrayType");
		attribute.setAttribute("wsdl:arrayType", "string[]");
		restriction.appendChild(attribute);
		complexContent.appendChild(restriction);
		array.appendChild(complexContent);
		return array;
	}

	/**
	 * Generate Complex Data Type -- Array
	 * 
	 * @param doc
	 * @return Element
	 */
	// <xsd:element name="Test">
	// <xsd:complexType>
	// <xsd:sequence>
	// <xsd:element maxOccurs="unbounded" name="item" type="xsd:string" />
	// </xsd:sequence>
	// </xsd:complexType>
	// </xsd:element>
	private Element ArrayDataTypeBasic(Document doc, String name, String type,
			int size) {
		Element array = doc.createElement("element");
		// array.setAttribute("name", "ArrayOfString");
		Element complexContent = doc.createElement("complexType");
		Element sequence = doc.createElement("sequence");
		Element item = doc.createElement("element");
		item.setAttribute("name", "item");
		item.setAttribute("maxOccurs", "unbounded");
		item.setAttribute("type", type);
		sequence.appendChild(item);
		complexContent.appendChild(sequence);
		array.appendChild(complexContent);
		return array;
	}

	/**
	 * Generate resquest and response data type for schema
	 * 
	 * @param doc
	 * @param operationType
	 * @param msg
	 * @param bcm
	 * @return
	 */
	private Element complexDataOperationConstructor(Document doc,
			String operationType, ArrayList msg) throws Exception {

		Element belement = doc.createElement("element");
		belement.setAttribute("name", operationType);
		Element operationElement = doc.createElement("complexType");
		Element all = doc.createElement("sequence");
		
		//for Testing : start
		ElementDesc field = new ElementDesc();
		MetaDesc md1 = new MetaDesc();
		md1.setName("well_id");
		md1.setType("S");
		field.AddMeta(md1);

		MetaDesc md2 = new MetaDesc();
		md2.setName("well_name");
		md2.setType("S");
		field.AddMeta(md2);
//		for Testing : end

		ElementDataDAO mh = new ElementDataDAO();
		MetaDesc md = null;
		for (int i = 0; i < field.size(); i++) {
			Element iterator = doc.createElement("element");
			md = field.getMetaDesc(i);
			iterator.setAttribute("name", md.getName());
			iterator.setAttribute("type", "xsd:string");
			all.appendChild(iterator);
		}
		operationElement.appendChild(all);
		belement.appendChild(operationElement);
		return belement;
	}

	/**
	 * Generate requestMessage
	 * 
	 * @param def
	 * @return
	 */
	private Message requestMessageConstructor(Definition def,
			String op_name) {
		Message reqMessage = def.createMessage();

		Part part = def.createPart();
		part.setName("request");
		// part.s

		// part.setTypeName(new QName(xsd, "string"));
		part.setElementName(new QName(tnsBase, op_name));
		reqMessage.setQName(new QName(tnsBase, "msgRequest"));
		reqMessage.addPart(part);
		reqMessage.setUndefined(false);

		return reqMessage;
	}

	/**
	 * Generate requestMessage
	 * 
	 * @param def
	 * @return
	 */
	private Message responseMessageConstructor(Definition def,
			String op_name) {
		Message reqMessage = def.createMessage();

		Part part = def.createPart();
		part.setName("response");

		// part.setTypeName(new QName(xsd, "string"));
		part.setElementName(new QName(tnsBase, op_name + "Response"));
		reqMessage.setQName(new QName(tnsBase, "msgResponse"));
		reqMessage.addPart(part);
		reqMessage.setUndefined(false);

		return reqMessage;
	}

	/**
	 * Generate PortType
	 * 
	 * @param reqMessage
	 * @param respMessage
	 * @param transactionID
	 * @param def
	 * @return
	 */
	private PortType portTypeConstructor(Message reqMessage,
			Message respMessage, String service_Name,String op_name, Definition def) {
		PortType portType = def.createPortType();
		portType.setQName(new QName(tnsBase, service_Name + "PT"));
		Operation operation = def.createOperation();
		operation.setName(op_name);
		Input input = def.createInput();
		input.setMessage(reqMessage);
		input.setName("request");
		Output output = def.createOutput();
		output.setMessage(respMessage);
		output.setName("response");
		operation.setInput(input);
		operation.setOutput(output);
		operation.setUndefined(false);
		portType.addOperation(operation);
		portType.setUndefined(false);

		return portType;

	}

	/**
	 * Generate Binding
	 * 
	 * @param transactionID
	 * @param def
	 * @param portType
	 * @return
	 */
	private Binding bindingConstructor(String service_Name, String op_name, Definition def,
			PortType portType) throws Exception {

		Binding binding = def.createBinding();
		binding.setQName(new QName(TNS, service_Name + "B"));

		
		SOAPBody soapBody = new SOAPBodyImpl();
		soapBody.setUse("literal");

		BindingInput bindingInput = def.createBindingInput();
		bindingInput.setName("request");
		bindingInput.addExtensibilityElement(soapBody);
		
		BindingOutput bindingOutput = def.createBindingOutput();
		bindingOutput.setName("response");
		bindingOutput.addExtensibilityElement(soapBody);
		
		BindingOperation bindingOperation = def.createBindingOperation();
		bindingOperation.setName(op_name);
		bindingOperation.setBindingInput(bindingInput);
		bindingOperation.setBindingOutput(bindingOutput);
		
		SOAPOperation soapOperation = new SOAPOperationImpl();
		soapOperation.setSoapActionURI(service_Name+"/"+op_name);
		bindingOperation.addExtensibilityElement(soapOperation);
		SOAPBinding soapBinding = new SOAPBindingImpl();
		soapBinding.setStyle("document");
		soapBinding.setTransportURI(HTTP);
		binding.addExtensibilityElement(soapBinding);
		
		binding.addBindingOperation(bindingOperation);  //repeat for multi operation
		binding.setPortType(portType);
		binding.setUndefined(false);

		return binding;
	}

	/**
	 * Generate Service
	 * 
	 * @param transactionID
	 * @param def
	 * @param binding
	 * @return
	 */
	private Service serviceConstructor(String _serviceID, Definition def,
			Binding binding) {

		Service service = def.createService();
		Port port = def.createPort();
		port.setName(_serviceID);
		port.setBinding(binding);

		// port.
		// def.

		SOAPAddress address = new SOAPAddressImpl();
		address.setLocationURI(SOAP_ADDRESS+_serviceID);
		port.addExtensibilityElement(address);
		service.setQName(new QName(WSDL, _serviceID));
		service.addPort(port);

		return service;

	}

	// transaction_sn" type="xsd:string" nillable="false
	public Element prepareElement(Document document, String name, String type,
			boolean nillable) {
		Element element = document.createElement("element");
		element.setAttribute("name", name);
		element.setAttribute("type", type);
		element.setAttribute("nillable", "" + nillable);

		return element;

	}

	// transaction_sn" type="xsd:string" nillable="false
	public Element prepareElement(Document document, String name, String type) {
		Element element = document.createElement("element");
		element.setAttribute("name", name);
		element.setAttribute("type", type);

		return element;

	}

	// <xsd:element ref="eains:requestExtension"></xsd:element>
	public Element prepareRefElement(Document document, String ref) {
		Element element = document.createElement("element");
		element.setAttribute("ref", ref);
		return element;

	}

}