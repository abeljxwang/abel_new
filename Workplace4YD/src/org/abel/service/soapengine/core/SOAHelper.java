/*
 * 创建日期 2005-5-23
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.soapengine.core;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.wsdl.extensions.ExtensibilityElement;
//import javax.wsdl.extensions.schema.Schema;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.Name;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import javax.wsdl.extensions.schema.Schema;

import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;

/**
 * @author abelwang
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class SOAHelper {

	/**
	 * Create element in SOAP
	 * @param envelope
	 * @param gltp
	 * @param key
	 * @param value
	 * @throws SOAPException
	 */	
	public static SOAPElement createElement(
		SOAPEnvelope envelope,
		SOAPElement gltp,
		String key,
		String value)
		throws SOAPException {
		Name name = envelope.createName(key);

		SOAPElement symbol = gltp.addChildElement(name);
		//symbol.setEncodingStyle("String");
		symbol.addAttribute(envelope.createName("xsi:type"), "xsd:string");
		//symbol.removeAttribute(envelope.createName("xmlns"));
		if (value != null) {
			symbol.addTextNode(value);
		}
		return symbol;

	}

	/**
	 * Get a named type from types definition
	 * @param types		Types element from WSDL
	 * @param typeName		The complex type name to retrieve definition
	 */
	public static Element getType(List types, String typeName) {
		Element type =null;
		//ExtensibilityElement test = (ExtensibilityElement) types.get(0);
		//Schema   schema = (Schema) test;

		//		UnknownExtensibilityElement uee =
		//			(UnknownExtensibilityElement) types.get(0);
		//	Element elmTypes = uee.getElement();
//		Element elmTypes = schema.getElement();
//		NodeList nlTypes = elmTypes.getElementsByTagName("complexType");
//		for (int i = 0; i < nlTypes.getLength(); i++) {
//			type = (Element) nlTypes.item(i);
//			if (type.getAttribute("name").equalsIgnoreCase(typeName)) {
//				return type;
//			}
//			
//		}
		return type;
	}

	/**
	 * Get value from a DOM element
	 * @param elm			Parent DOM element
	 * @param tagName		The name for child element
	 * @return				The value of child element
	 */
	public static String getElmValue(Element elm, String tagName) {
		String result=null;
		String prefix=null, nname=null;
		Element subElm = (Element) elm.getElementsByTagName(tagName).item(0);
		if (subElm != null) {
			NodeList cnl = subElm.getChildNodes();
			for (int i = 0; i < cnl.getLength(); i++) {
				Node n = cnl.item(i);
				//System.out.println("|||" + n.getNodeName());
				if (n.getNodeType() == 3) {
					result= n.getNodeValue();
				}
			}
		}
		return result;
	}



	/**
	 * Get value from SOAP Body
	 * @param		envelope		SOAPEnvelope
	 * @param		body			SOAPBody
	 * @param		name			The element name to be get value
	 */
	public static String getValue(
		SOAPEnvelope envelope,
		SOAPBody body,
		String name)
		throws SOAPException {
		Iterator elements = body.getChildElements(envelope.createName(name));
		SOAPElement node = (SOAPElement) elements.next();
		if (node != null) {
			return node.getValue();
		} else {
			return null;
		}
	}

	
	/**
	 * Get value from a DOM element
	 * @param elm			Parent DOM element
	 * @param tagName		The name for child element
	 * @return				The value of child element
	 */
	public static Vector getElmValues(Element elm, String tagName) {
		Element subElm = (Element) elm.getElementsByTagName(tagName);
		Vector v=new Vector();
		if (subElm != null) {
			NodeList cnl = subElm.getChildNodes();
			for (int i = 0; i < cnl.getLength(); i++) {
				Node n = cnl.item(i);
				System.out.println("|||" + n.getNodeName());
				if (n.getNodeType() == 3) {
					v.add(n.getNodeValue());
				}
			}
		}
		System.out.println(v);
		return v;
	}
	
	/**
	 * Get value from a DOM element
	 * @param elm			Parent DOM element
	 * @param tagName		The name for child element
	 * @return				The value of child element
	 */
	public static Vector getElmArrayValue(SOAPElement elm, String tagName) {
		//Element subElm = (Element) elm.getElementsByTagName(tagName).item(0);
		Iterator it = elm.getChildElements();
		Vector result=new Vector();
		String tmp;
		while (it.hasNext()){
			SOAPElement eltmp=(SOAPElement)it.next();
			//System.out.println(eltmp.getNodeName());
			if(eltmp.getNodeName().equals(tagName)) {
				
				tmp=eltmp.getFirstChild().getNodeValue();
				result.add(tmp);
			}
			
			//System.out.println(eltmp.getTextContent());
			//if(eltmp.getNodeName().equals("tagname"))  
				//result=eltmp.getFirstChild().getTextContent();
			//break;	
		}
		
		return result;
	}
	
	
	/**
	 * Generate Mime Headers for SOAP message
	 * @param req					HTTP request
	 * @return						MIME Headers for SOAP Message
	 */
	public static MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headers = req.getHeaderNames();
		MimeHeaders mimeHeaders = new MimeHeaders();
		while (headers.hasMoreElements()) {
			String name = (String) headers.nextElement();
			String values = req.getHeader(name);
			for (StringTokenizer value = new StringTokenizer(values, ",");
				value.hasMoreTokens();
				mimeHeaders.addHeader(name, value.nextToken().trim()));
		}
		return mimeHeaders;
	}

	/**
	 * Put SOAP MIME Headers on HTTP Response
	 * @param mimeHeaders			MIME Headers to be put into HTTP Response
	 * @param resp					HTTP Response
	 */
	public static void putHeaders(
		MimeHeaders mimeHeaders,
		HttpServletResponse resp) {
		for (Iterator iterator = mimeHeaders.getAllHeaders();
			iterator.hasNext();
			) {
			MimeHeader mimeHeader = (MimeHeader) iterator.next();
			String as[] = mimeHeaders.getHeader(mimeHeader.getName());
			if (as.length == 1) {
				resp.setHeader(mimeHeader.getName(), mimeHeader.getValue());
			} else {
				StringBuffer stringbuffer = new StringBuffer();
				for (int i = 0; i < as.length;) {
					if (i != 0)
						stringbuffer.append(',');
					stringbuffer.append(as[i++]);
				}
				resp.setHeader(mimeHeader.getName(), stringbuffer.toString());
			}
		}

	}


}
