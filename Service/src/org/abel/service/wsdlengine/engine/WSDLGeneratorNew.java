/*
 * 创建日期 2005-4-12
 *
 */
package org.abel.service.wsdlengine.engine;

import java.io.ByteArrayOutputStream;
// import java.io.File;
// import java.io.FileWriter;
import java.io.OutputStream;
//import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
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
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Name;
//import javax.xml.soap.Node;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
// import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;
import javax.xml.datatype.*;

import org.abel.service.soa.message.DescCacheManager;
import org.abel.service.soa.message.ElementDesc;
import org.abel.service.soa.message.MetaDesc;
import org.abel.service.soa.message.OperationDesc;
import org.abel.service.soa.message.ServiceDesc;
import org.abel.service.xsd.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

// import com.ibm.ais.wsdlengine.common.ConstantVariables;

import com.ibm.wsdl.extensions.soap.SOAPAddressImpl;
import com.ibm.wsdl.extensions.soap.SOAPBindingImpl;
import com.ibm.wsdl.extensions.soap.SOAPBodyImpl;
import com.ibm.wsdl.extensions.soap.SOAPOperationImpl;



/**
 * @author wangjx@cn.ibm.com
 * 
 */
public class WSDLGeneratorNew {

	// define namespace
	public static final String tnsBase = "http://ais_gbs.ibm/Service/wsdl/";

	public static final String TNS = "http://ais_gbs.ibm/Service/wsdl/";

	public static final String XSD = "http://www.w3.org/2001/XMLSchema";

	public static final String WSDL = "http://schemas.xmlsoap.org/wsdl/";

	public static final String SOAP = "http://schemas.xmlsoap.org/wsdl/soap/";

	public static final String HTTP = "http://schemas.xmlsoap.org/soap/http";

	public static final String SOAPENC = "http://schemas.xmlsoap.org/soap/encoding/";

	public static final String OPERATION_NAME = "performService";

	public static final String SOAP_ADDRESS = "http://ais_gbs.ibm/Service/SOAP/";

	public static final String ns_prefix = "ais_tns";

	public static WSDLCacheManager wsdlcm = null;

	// public static TransactionStructure transactionStructure = null;
	public String _serviceName = "Well_Mgr";

	public String _operationName = "query_Well_Info";

	public Hashtable _addtional_complex_type = new Hashtable();

	SOAPFactory sf = null;
	DescCacheManager dcm;

	public WSDLGeneratorNew() {
		try {
			if (sf == null)
				sf = SOAPFactory.newInstance();
			dcm=DescCacheManager.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		DescCacheManager dc = DescCacheManager.getInstance();

		ServiceDesc ms = dc.getServiceDesc("Well_Mgr");
		// ms.setChineseName("井信息维护");
		// ms.setEndPoint(ep)("井信息维护");

		WSDLGeneratorNew temp = new WSDLGeneratorNew();
		System.out.print(temp.generatorSingleWSDL(ms));

	}
	
	//set service name and operation name;
	
	public void setSO(String s,String o) {
		_serviceName = s;

		_operationName = o;
	

	}

	/**
	 * Generate WSDL for a transaction by a transactionID
	 * 
	 * @param service_ID
	 * @return
	 */
	public OutputStream generatorSingleWSDL(ServiceDesc service_Structuretmp) {

		ByteArrayOutputStream result = new ByteArrayOutputStream();
		// String tns = tnsBase;

		try {

			// get wsdlCacheManger instance
			if (null == wsdlcm) {
				// wsdlcm = WSDLCacheManager.getInstance();
			}
			String servname = service_Structuretmp.getName();
			//System.out.println(servname + "------------------");

			// String op_name=service_Structuretmp.getOperation(key)

			WSDLFactory factory = WSDLFactory.newInstance();
			WSDLWriter writer = factory.newWSDLWriter();

			// The Definition interface presents a WSDL definition.
			Definition def = generatorSingleWSDL_DEF(service_Structuretmp);

			//writer.writeWSDL(def, System.out);
			writer.writeWSDL(def, result);

			// System.out.println(def.toString());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;

	}
	
	
	/**
	 * Generate WSDL for a transaction by a transactionID
	 * 
	 * @param service_ID
	 * @return
	 */
	public Definition generatorSingleWSDL_DEF(ServiceDesc service_Structuretmp) {

		//ByteArrayOutputStream result = new ByteArrayOutputStream();
		// String tns = tnsBase;
		Definition def=null;

		try {


			String servname = service_Structuretmp.getName();
			//System.out.println(servname + "------------------");

			// String op_name=service_Structuretmp.getOperation(key)

			WSDLFactory factory = WSDLFactory.newInstance();
			//WSDLWriter writer = factory.newWSDLWriter();

			// The Definition interface presents a WSDL definition.
			def = factory.newDefinition();
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();

			// ---------------------------------------------
			// create namespace
			// ---------------------------------------------
			def.setQName(new QName(tnsBase, _serviceName));
			def.setTargetNamespace(tnsBase);
			def.addNamespace(ns_prefix, tnsBase);
			def.addNamespace("xsd", XSD);
			def.addNamespace("soap", SOAP);
			def.addNamespace("soapenc", SOAPENC);
			def.addNamespace("http", HTTP);

			
			
			if(_operationName==null) _operationName="query_Comm_Well_Information";
			//System.out.println(servname+"   "+dcm);
			//if(dcm==null) dcm=DescCacheManager.getInstance();
			//ServiceDesc sdc=dcm.getServiceDesc(servname);
			
			
			
			Vector keys=service_Structuretmp.getAllOperationKeys();
			
//			 Add types section
			def.setTypes(typesConstructor(doc, def, service_Structuretmp));

			//System.out.println(keys+"''''''''''''''''''''''");
			//ServiceDesc sdsc=dcm.getServiceDesc(servicename);
			 //Vector op_keys=service_Structuretmp.getAllOperationKeys();
			 //System.out.println(op_keys);
			 String key_tmp=null;
			 if(keys.size()>0) key_tmp=keys.get(0)+"";
			 
			 _operationName=key_tmp;
			 Binding binding = def.createBinding();
			binding.setQName(new QName(TNS, "B_"+servname ));
			SOAPBinding soapBinding = new SOAPBindingImpl();
			soapBinding.setStyle("document");
			soapBinding.setTransportURI(HTTP);
			binding.addExtensibilityElement(soapBinding);
			
			 PortType portType = def.createPortType();
			 portType.setQName(new QName(tnsBase, "PT_"+servname));
			 
			 for(int ia=0;ia<keys.size();ia++){
					_operationName=keys.get(ia)+"";
					//System.out.println(ia+"          "+_operationName);
//					 Add Message section
			OperationDesc opd = service_Structuretmp.getOperation(_operationName);
					// opd.getInputName();
					
			ElementDesc mdsc_tmp=dcm.getMessageDesc(opd.getInputName());
			if(mdsc_tmp!=null&&mdsc_tmp.getName()!=null){
				//System.out.println("input "+mdsc_tmp.getName()+"\n");
			Message reqMessage = requestMessageConstructor(def, mdsc_tmp.getName());
			//String outstr=opd.getOutputName();
			mdsc_tmp=dcm.getMessageDesc(opd.getOutputName());
			Message respMessage = responseMessageConstructor(def,mdsc_tmp.getName());  //_operationName
			 
			reqMessage.setUndefined(false);
			respMessage.setUndefined(false);
			def.addMessage(reqMessage);
			def.addMessage(respMessage);
			
			// Add portType Section
			portTypeConstructor(portType,reqMessage, respMessage,servname, _operationName, def);
			
			 
			// Add binding section
			
			bindingConstructor(binding,servname, _operationName, def,
					portType);
			
			 
			}
			
			 }
			 
			 def.addPortType(portType);
			 def.addBinding(binding);
			 
//			 Add service section
				//def.addService(serviceConstructor(servname, def, binding));
				
				def.addService(serviceConstructor(service_Structuretmp,def, binding));
			//writer.writeWSDL(def, System.out);
			//writer.writeWSDL(def, result);

			// System.out.println(def.toString());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return def;

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
	private Types typesConstructor(Document doc, Definition def,
			ServiceDesc service_Structuretmp) throws Exception {
		Types types = def.createTypes();

		// Schema schema = new SchemaImpl();
		// schema.setElement(schemaConstructor(doc, def));
		// schema.setElementType(new QName(XSD, "schema"));

		// types.addExtensibilityElement(schema);

		types.setDocumentationElement(schemaConstructor(doc, def,
				service_Structuretmp));

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
	private Element schemaConstructor(Document doc, Definition def,
			ServiceDesc service_Structuretmp) throws Exception {

		//SOAPFactory sf = SOAPFactory.newInstance();
		//Name bodyName = sf.createName("schema", "xsd", XSD);

		Element schemaElement = doc.createElement("schema");
		//schemaElement.setAttributeNS();
		//Element schemaElement = doc.createElementNS("xsd","schema");
		
		//Element schemaElement = sf.createElement(bodyName);
		schemaElement.setAttribute("targetNamespace", TNS);
		schemaElement.setAttribute("xmlns", XSD);

		// ArrayList msg = null;

		Vector op_keys = service_Structuretmp.getAllOperationKeys();
		// construct request message.
		String kk=null;
		for (int i = 0; i < op_keys.size(); i++) {
			kk=op_keys.elementAt(i)+"";
			//System.out.println(kk+"---------------------------");
			OperationDesc opd = service_Structuretmp.getOperation(kk);
			// opd.getInputName();
			
			ElementDesc mdsc=dcm.getMessageDesc(opd.getInputName());
			//System.out.println(mdsc);
			if(mdsc!=null&&mdsc.getName()!=null){
			Element requestElement = complexDataOperationConstructor(doc, mdsc, opd);
			
			//Node doc1OwnedNode = doc.importNode(doc2.getDocumentElement(), true);
			//doc.appendChild(doc1OwnedNode);
			
			//Node tempNode = schemaElement.importNode(requestElement,true); //true if you want a deep copy
			 
			//domYouAreAddingTheNodeTo.appendNode(tempNode);
			//Node requestElement1 = doc.importNode((Node)requestElement,true);
			//requestElement.cloneNode(true);

			schemaElement.appendChild(requestElement);
			}
			//addSubElement
			
			// construct response message
			ElementDesc mdsc1=dcm.getMessageDesc(opd.getOutputName());
			if(mdsc1!=null&&mdsc1.getName()!=null){
			Element responseElement = complexDataOperationConstructor(doc, mdsc1, opd);
			 schemaElement.appendChild(responseElement);
			}
		}
		
		//System.out.println(_addtional_complex_type);
		//DescCacheManager dcm = DescCacheManager.getInstance();

		
		for (int i = 0; i < _addtional_complex_type.size(); i++) {
			Enumeration keys=_addtional_complex_type.keys();
			while (keys.hasMoreElements()){
				Element tmpe=(Element)_addtional_complex_type.get(keys.nextElement());
				//Element emxpElement =  complexTypeConstructor(doc,tmpe);
				if(tmpe!=null) schemaElement.appendChild(tmpe);
				
			}
			
			
			
			
		}

		// Complex data type -- response status
		//Element responseStatus = responseStatus(doc);
		//schemaElement.appendChild(responseStatus);

		return schemaElement;
	}

	/**
	 * Generate ComplexType element of performService
	 * 
	 * @param doc
	 * @return Element
	 */

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
		//attribute.getSchemaTypeInfo().
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
			ElementDesc emd1, OperationDesc msg) throws Exception {

		//DescCacheManager dcm = DescCacheManager.getInstance();
		//System.out.println(emd1+"------------");
		//ElementDesc emd1 = dcm.getMessageDesc(operation_element_id);
		//System.out.println(emd1+"------------");
		Element belement = doc.createElement("element");
		belement.setAttribute("name", emd1.getName());
		Element operationElement = doc.createElement("complexType");
		Element all = doc.createElement("sequence");
		
		//Vector mtlst= emd1.getAllMetaDesc();

		MetaDesc md = null;
		for (int i = 0; i < emd1.size(); i++) {
			Element iterator = doc.createElement("element");
			md = emd1.getMetaDesc(i);
			if (md.isSubEle()) {
				ElementDesc emd2 = dcm.getMessageDesc(md.getName());
				//System.out.println("------------"+emd2.getID()+"-------------");
				Element e11= complexTypeConstructor(doc, emd2.getID());
				//System.out.println("------------"+emd2.getID()+"-------------");
				//System.out.print(e11.getLocalName());
				if(e11!=null) _addtional_complex_type.put(emd2.getName(), e11);

				iterator.setAttribute("name", emd2.getName());
				iterator.setAttribute("type", ns_prefix + ":" + emd2.getName());

			} else {
				iterator.setAttribute("name", md.getName());
				//parseType(md.getType());
				iterator.setAttribute("type", parseType(md.getType()));     //"xsd:string" need to be updated
			}
			if(md.getRpt()>1) iterator.setAttribute("maxOccurs", md.getRpt()+"");
			all.appendChild(iterator);
		}
		operationElement.appendChild(all);
		belement.appendChild(operationElement);
		return belement;
	}

	// for pure addtional complex type
	private Element complexTypeConstructor(Document doc,
			String element_id) throws Exception {

		DescCacheManager dcm = DescCacheManager.getInstance();

		ElementDesc emd1 = dcm.getMessageDesc(element_id);
		
		//System.out.println(emd1+"----------"+element_id);
		//System.out.println(emd1);
		Element complexElement = doc.createElement("complexType");
		complexElement.setAttribute("name", emd1.getName());
		Element all = doc.createElement("sequence");
		//emd1

		MetaDesc md = null;
		for (int j = 0; j < emd1.size(); j++) {
			Element iterator = doc.createElement("element");
			md = emd1.getMetaDesc(j);
			if (md.isSubEle()) {
				ElementDesc emd2 = dcm.getMessageDesc(md.getName());
				iterator.setAttribute("name", emd2.getName());
				iterator.setAttribute("type", ns_prefix + ":" + emd2.getName());
				Element e = complexTypeConstructor(doc, emd2.getID());
				_addtional_complex_type.put(emd2.getName(), e);

			} else {
				iterator.setAttribute("name", md.getName());			
				iterator.setAttribute("type", parseType(md.getType()));  //"xsd:string"
				//javax.xml.datatype.
				
			}
			if(md.getRpt()>1) iterator.setAttribute("maxOccurs", md.getRpt()+"");
			all.appendChild(iterator);
		}
		complexElement.appendChild(all);

		return complexElement;
	}

	/**
	 * Generate requestMessage
	 * 
	 * @param def
	 * @return
	 */
	private Message requestMessageConstructor(Definition def, String op_name) {
		Message reqMessage = def.createMessage();

		Part part = def.createPart();
		part.setName("request");
		// part.s

		// part.setTypeName(new QName(xsd, "string"));
		part.setElementName(new QName(tnsBase, op_name));
		reqMessage.setQName(new QName(tnsBase, "msg_"+op_name+"_req"));
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
	private Message responseMessageConstructor(Definition def, String op_name) {
		Message reqMessage = def.createMessage();

		Part part = def.createPart();
		part.setName("response");

		// part.setTypeName(new QName(xsd, "string"));
		part.setElementName(new QName(tnsBase, op_name));
		reqMessage.setQName(new QName(tnsBase, "msg_"+op_name+"_resp"));
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
	private void portTypeConstructor(PortType portType, Message reqMessage,
			Message respMessage, String service_Name, String op_name,
			Definition def) {
		
		
		Operation operation = def.createOperation();
		operation.setName(op_name);
		Input input = def.createInput();
		input.setMessage(reqMessage);
		input.setName("req_"+op_name);
		Output output = def.createOutput();
		output.setMessage(respMessage);
		output.setName("resp_"+op_name);
		operation.setInput(input);
		operation.setOutput(output);
		operation.setUndefined(false);
		portType.addOperation(operation);
		portType.setUndefined(false);

		//return portType;

	}

	/**
	 * Generate Binding
	 * 
	 * @param transactionID
	 * @param def
	 * @param portType
	 * @return
	 */
	private void bindingConstructor(Binding binding,String service_Name, String op_name,
			Definition def, PortType portType) throws Exception {

		

		SOAPBody soapBody = new SOAPBodyImpl();
		soapBody.setUse("literal");

		BindingInput bindingInput = def.createBindingInput();
		bindingInput.setName("req_"+op_name);
		bindingInput.addExtensibilityElement(soapBody);

		BindingOutput bindingOutput = def.createBindingOutput();
		bindingOutput.setName("resp_"+op_name);
		bindingOutput.addExtensibilityElement(soapBody);

		BindingOperation bindingOperation = def.createBindingOperation();
		bindingOperation.setName(op_name);
		bindingOperation.setBindingInput(bindingInput);
		bindingOperation.setBindingOutput(bindingOutput);

		SOAPOperation soapOperation = new SOAPOperationImpl();
		soapOperation.setSoapActionURI(service_Name + "/" + op_name);
		bindingOperation.addExtensibilityElement(soapOperation);
		

		binding.addBindingOperation(bindingOperation); // repeat for multi
														// operation
		binding.setPortType(portType);
		binding.setUndefined(false);

		//return binding;
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
		
		address.setLocationURI(SOAP_ADDRESS + _serviceID);
		port.addExtensibilityElement(address);
		service.setQName(new QName(WSDL, _serviceID));
		service.addPort(port);

		return service;

	}

	/**
	 * Generate Service
	 * 
	 * @param transactionID
	 * @param def
	 * @param binding
	 * @return
	 */
	private Service serviceConstructor(ServiceDesc servicedesc, Definition def,
			Binding binding) {

		Service service = def.createService();
		Port port = def.createPort();
		port.setName(servicedesc.getName());
		port.setBinding(binding);

		// port.
		// def.

		SOAPAddress address = new SOAPAddressImpl();
		
		address.setLocationURI(servicedesc.getEndPoint());   //.SOAP_ADDRESS + _serviceID
		port.addExtensibilityElement(address);
		service.setQName(new QName(WSDL, servicedesc.getName()));
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
	
	public void addSubElement(SOAPElement sup_ele,ElementDesc ed,SOAPFactory sf) throws SOAPException {
		DescCacheManager dm=DescCacheManager.getInstance();
		for(int i=0;i<ed.getMetaAcount();i++){
			 MetaDesc mdsctmp=ed.getMetaDesc(i);
			 SOAPElement  cele =null;
			 
			 if(mdsctmp.getType().equals("E"))
			 {
				
				 ElementDesc tmpme=dm.getMessageDesc(mdsctmp.getName());
				 cele=sf.createElement(tmpme.getName());
				 addSubElement(cele,tmpme,sf);
				 
			 }
			 else{
				 cele=sf.createElement(mdsctmp.getName());
				 cele.addTextNode(mdsctmp.getDF_Value());
			 }
			
			 sup_ele.addChildElement(cele);
			 
		 }

	}	
	
	String parseType(String tp){
		String result=Type.STRING;
		if(tp.equals("S")) result=Type.STRING;
		else if(tp.equals("D")) result=Type.DATE;
		else if(tp.equals("F")) result=Type.FLOAT;
		else if(tp.equals("I")) result=Type.INT;
		
		return result;
		
		
			
		
	}

}