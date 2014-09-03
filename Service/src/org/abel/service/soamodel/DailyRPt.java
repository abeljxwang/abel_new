/*
 * 创建日期 2006-12-13
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.soamodel;

//import java.util.ArrayList;

import org.abel.service.base.config.OperationCfg;
//import org.abel.service.base.message.GroupRecord;
//import org.abel.service.base.message.MsgObject;
import org.abel.service.soapengine.core.SOAModelBase;
import org.abel.service.soapengine.core.ServiceMessage;


/**
 * @author abelwang
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class DailyRPt extends SOAModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ServiceMessage execute(OperationCfg opcfg,ServiceMessage mo) {
		ServiceMessage mot=null;
		
		try {
			mot=new ServiceMessage("query_well_info_resp");
			mot.set("WELL_ID", "124125125");
			mot.set("CMP", "CNPC");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mot;
	}


}


