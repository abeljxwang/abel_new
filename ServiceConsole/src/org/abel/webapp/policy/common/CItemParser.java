package org.abel.webapp.policy.common;

import java.util.*;


/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CItemParser {
	Vector record = new Vector();

	public Vector getparameters(String parastr) {
		record.clear();
		int index = 0, posi = 0;
		String tmp = null, sunstr = "";
		index = parastr.indexOf(" ");
		record.add(0, parastr.substring(0, index));
		parastr = parastr.substring(index + 1).trim();
		index = parastr.indexOf(" ");
		record.add(1, parastr.substring(0, index));
		record.add(2, parastr.substring(index + 1));

		return record;
	}

	public String getName() {
		String result = null;
		if (record != null && record.size() == 3)
			result = record.elementAt(0).toString();
		return result;

	}

	public String getValueName() {
		String result = null;
		String tmps = null;
		if (record != null && record.size() == 3)
			tmps = record.elementAt(2).toString();
		int startpoint = tmps.indexOf("'");
		int lastpoint = tmps.lastIndexOf("'");
		result = tmps.substring(startpoint + 1, lastpoint);
		return result;

	}

	public String getOptName() {
		String result = null;
		if (record != null && record.size() == 3)
			result = record.elementAt(1).toString();

		return result;

	}

	public String complete(Properties p) {
		String result = "";
		String tempstr = null;
		String name, opt, value;
		if (p.getProperty(this.getName()) != null)
			name = p.getProperty(this.getName());
		else
			name = null;
		opt = this.getOptName();
		String valueStr = this.getValueName();
		if (valueStr.indexOf("$") == 0) {
			valueStr = valueStr.substring(1);
			if (p.getProperty(valueStr) != null)
				value = p.getProperty(valueStr);
			else
				value = null;
		} else
			value = valueStr;

		result = name + " " + opt + " " + value;
		return result;
	}
	public String completeStr(BaseDO bdo, WFParameters p) {
		String result = "";
		String tempstr = null;
		String name, opt, value;
		if (bdo.getParameter(this.getName()) != null)
			name = bdo.getParameter(this.getName());
		else
			name = null;
		opt = this.getOptName();
		String valueStr = this.getValueName();
		if (valueStr.indexOf("$") == 0) {
			valueStr = valueStr.substring(1);
			if (p.get(valueStr) != null)
				value = p.get(valueStr);
//			else if (res.get(valueStr) != null)
//				value = res.get(valueStr).toString();
			else
				value = null;
		} else
			value = valueStr;

		result = name + " " + opt + " " + value;
		return result;
	}

	public Vector complete(BaseDO bdo, WFParameters p) {
		Vector result = new Vector();
		String tempstr = null;
		String name = this.getName(), opt = this.getOptName(), value = null;
		if (name.startsWith("PNA_")) {

			String substr = name.substring(4);
			//System.out.println(substr + " : Substr"); //if(name.s)
			

		} else {
			
			if (bdo.getParameter(name) != null)
				name = bdo.getParameter(this.getName());
			else
				name = null;
		}

		String valueStr = this.getValueName();
		if (valueStr.indexOf("$") == 0) {
			valueStr = valueStr.substring(1);
			if (p.get(valueStr) != null)
				value = p.get(valueStr);
//			else if (res.get(valueStr) != null)
//				value = res.get(valueStr).toString();
			else
				value = null;
		} else
			value = valueStr;

		//System.out.println(name + " : " + opt + " : " + value);
		if(name!=null){
			result.add(name);
			result.add(opt);
			result.add(value);
		}


		return result;
	}

	public boolean parsing() {

		return false;
	}

	public static void main(String[] args) {

		CItemParser ps = new CItemParser();
		Properties pppp = new Properties();
		pppp.setProperty("DEPT", "10");
		pppp.setProperty("did", "9");
		//System.out.println(ps.getparameters("DEPT EQ '$did'"));
		System.out.println(ps.getparameters("DEPT EQ '01'"));
		BaseDO bdo = new BaseDO();
		bdo.setParameter("DEPT", "01");
		WFParameters wfp = new WFParameters();

		wfp.set("did", "01");

		//Vector aaa = ps.complete(bdo, wfp);


		//Operator.getBooleanResult(result,parsingItem(tmp,bdo, p, res),algorithm)

		//System.out.println(ps.complete(bdo, wfp, response) + "  " + ars);
		//Operator.getResult()

	}
}
