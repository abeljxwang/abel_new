/*
 * �������� 2004-4-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.standardmodel;

import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.configure.*;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;
//import org.abel.webapp.db.*;
//import org.abel.webapp.common.*;
import java.util.*;
import org.abel.webapp.info.*;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class NewsModel extends ModelBase {

	InfoHandler infoh=new InfoHandler();
	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
		Request request,
		Response response,
		ActionCfg conf) {
		try{
		
		Vector infolist=infoh.getInfos(false);
		response.setRResult("infolist",infolist);

		}catch (Exception e){}
		return conf.getActionFword(); //afd;

	}
}