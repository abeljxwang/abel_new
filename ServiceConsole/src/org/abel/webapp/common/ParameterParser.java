package org.abel.webapp.common;

import java.util.*;
/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ParameterParser {

	public static Vector pasingStr(String str,String spilttag) {

		StringTokenizer sttmp = new StringTokenizer(str, spilttag);
		int insize = sttmp.countTokens();
		Vector result = new Vector();
		for (int i = 0; i < insize; i++) {
			result.add(sttmp.nextToken());
		}

		return result;
	}


	public Vector getparameters(String parastr) {

		Vector result = new Vector();

		StringTokenizer valuetmp = new StringTokenizer(parastr, ",");
		String tmp = null;
		int posi = 0;
		//int insize = valuetmp.countTokens();
		while (valuetmp.hasMoreTokens()) {

			tmp = valuetmp.nextToken();
			result.add(posi, tmp);
			posi++;

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
			String tmpvlu="";
			if(p.getProperty(valunametmp)!=null) tmpvlu=p.getProperty(valunametmp);
			result= result+suntmp01 + tmpvlu;
		}
		result=result+suntmp02;
		return result;
	}
	
	public static void main(String[] args) {
		
		ParameterParser ps=new ParameterParser();
		System.out.println(ps.getparameters("a,b,wrfw,swfaf,ssfd"));
		
	}
}
