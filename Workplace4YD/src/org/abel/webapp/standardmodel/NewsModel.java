/*
 * 创建日期 2004-4-2
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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