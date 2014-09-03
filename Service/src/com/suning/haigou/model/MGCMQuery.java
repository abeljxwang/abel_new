/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;

//import com.ibm.ais.base.message.MsgObject;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import org.abel.service.base.common.*;
import org.abel.service.jsonengine.JSONModelBase;
import org.abel.service.soapengine.core.ServiceMessage;
import org.json.simple.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.suning.haigou.bo.MGDBHandler;

/**
 * @author administrator
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class MGCMQuery extends JSONModelBase {

	public JSONObject execute(OperationCfg opcfg, JSONObject mo)  throws Exception{

		JSONObject mot = new JSONObject();

		

			String opname = opcfg.getPropertie("Name");

			String collection = null;
			String query_base = null;
			String q_field = null;
			String responsekey = "result_list";

			ParameterParser ps = new ParameterParser();
			Properties ppkv = new Properties();

			
			//Collection
			if (opcfg.getParameter("collection") != null)
				collection = opcfg.getParameter("collection");
			
			//Responsekey
			if (opcfg.getParameter("responsekey") != null)
				responsekey = opcfg.getParameter("responsekey");
			
			// sqlbase
			if (opcfg.getParameter("query_base") != null)
				query_base = opcfg.getParameter("query_base");

			// sqlfield
			
			Vector fieldv = new Vector();
			if (opcfg.getParameter("q_field") != null) {
				q_field = opcfg.getParameter("q_field");
				fieldv = ps.getparameters(q_field);
				
				String tmp1 = null;
				for (int k = 0; k < fieldv.size(); k++) {
					tmp1 = fieldv.elementAt(k) + "";
					if (mo.get(tmp1) != null
							&& mo.get(tmp1).toString().trim().length() > 0)
						ppkv.setProperty(tmp1, mo.get(tmp1).toString());
					
				}

			}
			
			query_base = ps.complete(query_base, ppkv);

		
			MGDBHandler mgdh=new MGDBHandler();
			
			
				DBCollection coll = mgdh.getCollection(collection);
				DBCursor cursor=null;
				ArrayList item_list = new ArrayList();
				//System.out.println(new BasicDBObject("$regex", String.format(".*((?i)%s).*", "aaa")) );

				
				Object o = com.mongodb.util.JSON.parse(query_base);
				//DBObject dbObj = (DBObject) o;
				BasicDBObject query = (BasicDBObject) o;
				//query.
				//System.out.println(query.toString());

				cursor = coll.find(query);
				try {
					while (cursor.hasNext()) {
						DBObject dbo = cursor.next();
						//System.out.println(dbo.toString());
						JSONObject result_item=new JSONObject(); 
						result_item.putAll(dbo.toMap());
						item_list.add(result_item);
					}
				} finally {
					if(cursor!=null) cursor.close();
				}
				mot.put(responsekey, item_list);

		
		return mot;

	}


}




