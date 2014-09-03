/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.soamodel;


//import com.ibm.ais.base.message.MsgObject;


//import java.util.Properties;
//import java.util.Vector;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
//import org.abel.service.base.common.*;
import org.abel.service.soapengine.core.SOAModelBase;
import org.abel.service.soapengine.core.ServiceMessage;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class BlankMD extends SOAModelBase {


	public ServiceMessage execute(OperationCfg opcfg,ServiceMessage  mo) {

		ServiceMessage mot=null;
		
		System.out.println("--------------Thsi BLANK Model------------------");
		
		try {
			//System.out.print(":"+"="+ mo.getValue("well_id"));
			//
			String opname=opcfg.getPropertie("Name");
			mot=new ServiceMessage(opname+"_response");
			System.out.println("Operation name:="+opname);
			
			
			System.out.println("1st p:="+mo.getValue("epr_code"));
			System.out.println("2nd p:="+mo.getValue("epr_acceptor"));
			
			System.out.println(" Some business logic here");
			
			//System.out.println(opcfg.getParameter("sqlbase")+"   p: sqlbase");
			
			System.out.println(" prepare the response");
			
			
			//mot.set("CMP", "CNPC");
			
			ServiceMessage wellinfo=new ServiceMessage("express");
			wellinfo.set("epr_code", "99999999999999999");
			mot.setChildEle(mot,wellinfo);
			mot.setChildEleValue("express", "epr_acceptor", "Abel Test for BlankModel");
			//org.apache.axis.holders.DateHolder epr_accepttime=new org.apache.axis.holders.DateHolder();
			
			mot.set("epr_accepttime", "20070909");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mot;

	}
	
	void  prepareResult(ServiceMessage  mo,EIPResultSet eresult ){
		
		
		
	}

}
