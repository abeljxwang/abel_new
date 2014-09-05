/*
 * �������� 2006-12-11
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.soamodel;


//import com.ibm.ais.base.message.MsgObject;


import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import org.abel.service.base.common.*;
import org.abel.service.soapengine.core.ModelBase;
import org.abel.service.soapengine.core.ServiceMessage;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FDBQueryList extends ModelBase {


	public  EIPResultSet execute(OperationCfg opcfg,HttpServletRequest req) {

		ServiceMessage mot=null;
		EIPResultSet eresult =null;		
		//System.out.println("--------------------------------");
		
		try {
			//System.out.print(":"+"="+ mo.getValue("well_id"));
			//
			String opname=opcfg.getPropertie("Name");
			mot=new ServiceMessage(opname+"_response");
			//System.out.println(opcfg.getParameter("sqlbase")+"   p: sqlbase");
			
			
			Vector ERROR_INFO = new Vector(); //null;

			
			String sql = null;
			String                             //sqltable = null,
				sqlbase = null,
				//sqlparas = null,
				sqlfield = null;
				//fieldname = null;
				//sqlcondition = null,
				//fieldlength = null;
			ParameterParser ps = new ParameterParser();
			Properties ppkv = new Properties();

			//basic info for all db process model
			
			//sqlbase	
			if (opcfg.getParameter("sqlbase") != null)
				sqlbase = opcfg.getParameter("sqlbase");

			//sqlfield
			Vector fieldv = new Vector();
			if (opcfg.getParameter("sqlfield") != null) {
				sqlfield = opcfg.getParameter("sqlfield");
				fieldv = ps.getparameters(sqlfield);
				String tmp1=null;
				for(int k=0;k<fieldv.size();k++){
					tmp1=fieldv.elementAt(k)+"";

					if (req.getParameter(tmp1) != null && req.getParameter(tmp1).trim().length() > 0) 
							ppkv.setProperty(tmp1, req.getParameter(tmp1));
				}
				
			}
			
			
			//fieldname
//			Vector namev = new Vector();
//			if (opcfg.getParameter("fieldname") != null) {
//				fieldname = opcfg.getParameter("fieldname");
//				namev = ps.getparameters(fieldname);
//			}

	
			//sqlfield
			Vector resultkeyv = new Vector();
			//System.out.println("responsekey:");
			if (opcfg.getParameter("responsekey") != null) {
				sqlfield = opcfg.getParameter("responsekey");
				//System.out.println(sqlfield);
				resultkeyv = ps.getparameters(sqlfield);
				//System.out.println(resultkeyv);
			}
			
			String  listkey = "listinfo";
			if (opcfg.getParameter("listkey") != null) {
				listkey = opcfg.getParameter("listkey");
			}

			

			sql = sqlbase;
			//System.out.println(sql+"---------------"+ppkv);
			sql = ps.complete(sql, ppkv);
			//System.out.println(sql+"---------------"+ppkv);

			DBHandler dbh = new DBHandler();
			String ktmp=null;
			try {
				eresult = dbh.query(sql,3000);
				//System.out.println(eresult.size());
				dbh.close();
				dbh = null;
				
							
				while (eresult.next()) {
					ServiceMessage listinfo=new ServiceMessage(listkey);
				for(int k=0;k<resultkeyv.size();k++){
					//System.out.println(resultkeyv.elementAt(k)+"---------------"+eresult.getString(k+1));
					if(resultkeyv.elementAt(k)!=null) ktmp=resultkeyv.elementAt(k).toString();
					if(eresult.getString(k+1)!=null) 	listinfo.set(ktmp, eresult.getString(k+1));
					
				}
				mot.setChildEle(mot,listinfo);	
				}
				//mot.set(resultkeyv., "20070909T12:00:00");
				
				
				//prepareResult(mot,eresult);
			} catch (Exception se) {
				se.printStackTrace();
				ERROR_INFO.add("���ݿ��ѯ�����������");

			} finally {
				if (dbh != null)
					dbh.close();
			}
			
			
			
			
			//mot.set("epr_accepttime", "20070909T12:00:00");
			//mot.set("CMP", "CNPC");
			
			//ServiceMessage wellinfo=new ServiceMessage("express");
			//listkey
			//wellinfo.set("epr_code", "1111");
			//mot.setChildEle(mot,wellinfo);
			//mot.setChildEleValue("express", "epr_acceptor", "Abel");
		
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eresult ;

	}
	
	void  prepareResult(ServiceMessage  mo,EIPResultSet eresult ){
		
		
		
	}

}
