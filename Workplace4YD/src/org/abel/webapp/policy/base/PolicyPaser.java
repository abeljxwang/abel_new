package org.abel.webapp.policy.base;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.*;


import java.util.*;

/**
 * A sample DOM counter. This sample program illustrates how to

 */
public class PolicyPaser {

	public static Policy parsing(String fileurl) throws Exception {
		Policy pp = new Policy();

		//Vector pp = new Vector();
		DOMParser parsera = new DOMParser();

		parsera.parse(fileurl);

		Document dc = parsera.getDocument();

		NodeList ee = dc.getElementsByTagName("policy");
		Node nd = ee.item(0);
		//NamedNodeMap nnm = nd.getAttributes();
		//int asize = nnm.getLength();
/*		for (int i = 0; i < asize; i++) {
			Node anode = nnm.item(i);
					System.out.println(
							anode.getNodeName() + "=" + anode.getNodeValue());
		}*/

		NodeList cnl = nd.getChildNodes();
		int leng = cnl.getLength();
		for (int i = 0; i < leng; i++) {
			Node cnd = cnl.item(i);

			if (cnd != null && cnd.getNodeType() == Node.ELEMENT_NODE) {
				String name = cnd.getNodeName();
				String prstr = cnd.getFirstChild().getNodeValue();
				//System.out.println(i);
				//System.out.println(name+"-"+prstr);
				StringTokenizer valuetmp = new StringTokenizer(prstr, " ");
				String tmp;
				Vector vv = new Vector();
				for (int ii = 0; valuetmp.hasMoreTokens(); ii++) {
					tmp = valuetmp.nextToken();
					vv.add(ii, tmp);
				}
				if (name.equals("rule")) {
					PolicyItem pi = new PolicyItem();
					String Astr = vv.elementAt(0).toString();
					int posi = Astr.indexOf(".");
					if (posi > 0) {
						UnitObj Auo =
							new UnitObj(
								Astr.substring(0, posi),
								Astr.substring(posi + 1));
						pi.setA(Auo);
					} else {
						UnitObj Auo = new UnitObj("FixV", Astr);
						pi.setA(Auo);
					}

					String OStr = vv.elementAt(1).toString();

					Operator o = new Operator(OStr);
					pi.setOperator(o);

					String Bstr = vv.elementAt(2).toString();
					posi = Bstr.indexOf(".");
					if (posi > 0) {
						UnitObj Buo =
							new UnitObj(
								Bstr.substring(0, posi),
								Bstr.substring(posi + 1));
						pi.setB(Buo);
					} else {
						UnitObj Buo = new UnitObj("", Bstr);
						pi.setB(Buo);
					}

					pp.add(pi);
				}
			}

			//System.out.println("-----------");
		}

		return pp;

	}

	public static Policy parsingP(Node nd) throws Exception {
		Policy pp = new Policy();

		NamedNodeMap nnm = nd.getAttributes();
		int asize = nnm.getLength();
		for (int i = 0; i < asize; i++) {
			Node anode = nnm.item(i);
			if (anode.getNodeName().endsWith("id"))
				pp.setkey(anode.getNodeValue());
		}

		NodeList cnl = nd.getChildNodes();
		int leng = cnl.getLength();
		for (int i = 0; i < leng; i++) {
			Node cnd = cnl.item(i);

			if (cnd != null && cnd.getNodeType() == Node.ELEMENT_NODE) {
				String name = cnd.getNodeName();
				String prstr = cnd.getFirstChild().getNodeValue();
				//System.out.println(i);
				//System.out.println(name+"-"+prstr);
				StringTokenizer valuetmp = new StringTokenizer(prstr, " ");
				String tmp;
				Vector vv = new Vector();
				for (int ii = 0; valuetmp.hasMoreTokens(); ii++) {
					tmp = valuetmp.nextToken();
					vv.add(ii, tmp);
				}
				if (name.equals("rule")) {
					PolicyItem pi = new PolicyItem();
					String Astr = vv.elementAt(0).toString();
					int posi = Astr.indexOf(".");
					if (posi > 0) {
						UnitObj Auo =
							new UnitObj(
								Astr.substring(0, posi),
								Astr.substring(posi + 1));
						pi.setA(Auo);
					} else {
						UnitObj Auo = new UnitObj("FixV", Astr);
						pi.setA(Auo);
					}

					String OStr = vv.elementAt(1).toString();

					Operator o = new Operator(OStr);
					pi.setOperator(o);

					String Bstr = vv.elementAt(2).toString();
					posi = Bstr.indexOf(".");
					if (posi > 0) {
						UnitObj Buo =
							new UnitObj(
								Bstr.substring(0, posi),
								Bstr.substring(posi + 1));
						pi.setB(Buo);
					} else {
						UnitObj Buo = new UnitObj("", Bstr);
						pi.setB(Buo);
					}

					pp.add(pi);
				}
			}

			//System.out.println("-----------");
		}

		return pp;

	}

	public static PolicyBox parse(String fileurl) throws Exception {
		//Policy  = new Policy();

		PolicyBox pp = new PolicyBox();
		DOMParser parsera = new DOMParser();
		parsera.parse(fileurl);
		Document dc = parsera.getDocument();

		NodeList ee = dc.getElementsByTagName("policy");
		int psize = ee.getLength();
		Policy ptmp = null;
		String pkey="";
		for (int i = 0; i < psize; i++) {
			Node nd = ee.item(i);
			ptmp = parsingP(nd);
			pkey=ptmp.getkey();
			pp.set(pkey, ptmp);
		}

		return pp;

	}

	public static Policy parsePR(PolicyRecord pr) throws Exception {
		return null;

	}

	/** Main program entry point. */
	public static void main(String argv[]) throws Exception {
		Vector pp = new Vector();

		DOMParser parsera = new DOMParser();

		parsera.parse("C:\\data\\Policy.xml");

		Document dc = parsera.getDocument();

		NodeList ee = dc.getElementsByTagName("policy");
		Node nd = ee.item(1);
		NamedNodeMap nnm = nd.getAttributes();
		int asize = nnm.getLength();
		for (int i = 0; i < asize; i++) {
			Node anode = nnm.item(i);
			System.out.println(
				anode.getNodeName() + "=" + anode.getNodeValue());
		}

		NodeList cnl = nd.getChildNodes();
		int leng = cnl.getLength();
		for (int i = 0; i < leng; i++) {
			Node cnd = cnl.item(i);

			if (cnd != null && cnd.getNodeType() == Node.ELEMENT_NODE) {
				String name = cnd.getNodeName();
				String prstr = cnd.getFirstChild().getNodeValue();
				//System.out.println(i);
				//System.out.println(name+"-"+prstr);
				StringTokenizer valuetmp = new StringTokenizer(prstr, " ");
				String tmp;
				Vector vv = new Vector();
				for (int ii = 0; valuetmp.hasMoreTokens(); ii++) {
					tmp = valuetmp.nextToken();
					vv.add(ii, tmp);
				}
				//System.out.println(vv);
				if (name.equals("rule")) {
					//System.out.println(vv);
					//System.out.println(vv.elementAt(2));
					//System.out.println(vv.elementAt(0));
					//System.out.println(vv.elementAt(1));
					PolicyRecord pr =
						new PolicyRecord(
							vv.elementAt(0),
							vv.elementAt(2),
							vv.elementAt(1));
					//System.out.println(pr.operator);
					//System.out.println(pr.userp);
					//System.out.println(pr.contentp);							
					pp.add(pr);
				}
			}

			//System.out.println("-----------");
		}

		PolicyRecord prp = (PolicyRecord) pp.get(1);
		System.out.println(prp.operator);
		System.out.println(prp.userp);
		System.out.println(prp.contentp);
		System.out.println("----");

	}

}
