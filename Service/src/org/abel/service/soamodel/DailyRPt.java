/*
 * �������� 2006-12-13
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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


