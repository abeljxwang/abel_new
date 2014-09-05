package org.abel.webapp.db;

import java.util.*;
/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SQLStatement {

	public static void main(String[] args) {
		String qc =
			"select name, id from CONTACTLIST where name='#name#' and id=#id# and bbb=#id# and 1=1";
		String cnd = "name=wang,id=1,name=zhang,id=2;name=guan,id=3";

		SQLStatement st = new SQLStatement();
		
		Vector vv=st.createSQL(qc, cnd);
		//System.out.println(vv.get(0));
		//System.out.println(vv.get(1));
	}

	public Vector createSQL(String queryContent, String condition) {
		Vector result = new Vector();

		//HashMap mp = new HashMap();
		//HashSet s = new HashSet();

		StringTokenizer tmpst = null;
		StringTokenizer valuetmp = new StringTokenizer(condition, ";");

		//int insize = valuetmp.countTokens();
		//Vector cdv = new Vector(insize);

		while (valuetmp.hasMoreTokens()) {
			Properties p = new Properties();
			String tmp = valuetmp.nextToken();
			//System.out.println(tmp);
			tmpst = new StringTokenizer(tmp, ",");
			//System.out.println(tmpst);
			while (tmpst.hasMoreTokens()) {
				String tmp1 = tmpst.nextToken();
				//System.out.println(tmp1);
				int posi = tmp1.indexOf("=");
				String tname = tmp1.substring(0, posi);
				String tvalue = tmp1.substring(posi + 1);
				p.setProperty(tname, tvalue);

			}
						
			//cdv.add(p.clone());

			result.add(complete(queryContent, (Properties)p.clone()));
			//System.out.println(result);
		}

		return result;
	}
	public String complete(String queryContent, Properties p) {
		String result = "";

		String suntmp01="", suntmp02 = "", valunametmp = null;
		suntmp02 = queryContent;
		while (suntmp02.indexOf("#") >= 0) {
			int tmpposi = suntmp02.indexOf("#");
			if (tmpposi >= 0) {
				suntmp01 = suntmp02.substring(0, tmpposi);
				suntmp02 = suntmp02.substring(tmpposi + 1);
				valunametmp = suntmp02.substring(0, suntmp02.indexOf("#"));
				suntmp02 = suntmp02.substring(suntmp02.indexOf("#") + 1);
			}

			result= result+suntmp01 + p.getProperty(valunametmp);
		}
		result=result+suntmp02;
		return result;
	}

}