/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.jsonengine.JSONModelBase;
import org.json.simple.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.suning.haigou.bo.ProductProfile;

/**
 * @author Abel
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation. login { login_id login_pwd }
 * 
 *         User { user_id * first_name last_name sex prefix * * email address
 *         grade_ref order_ref shopping_cart_ref }
 * 
 */

public class MProductS extends JSONModelBase {



	MongoClient mongoClient = null;

	public JSONObject execute(OperationCfg opcfg, JSONObject mo_in)
			throws Exception {

		JSONObject mot = new JSONObject(); // message for output
		// Vector ERROR_INFO = new Vector(); // for error message

		String opname = opcfg.getPropertie("Name");
		// System.out.println(opname + " Action name from the config");
		// get collection name from config
		String collection = null; // collection name from config
		if (opcfg.getParameter("collection") != null)
			collection = opcfg.getParameter("collection");

		DBObject dbo_result = null;

		HashMap hm_in = (HashMap) mo_in;
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(hm_in);

		ProductProfile pp = new ProductProfile();

		if (opname.equalsIgnoreCase("show"))
			dbo_result = pp.getProductProfile(bdo_in);
		else if (opname.equalsIgnoreCase("list")) 
			dbo_result = pp.listProductProfile(bdo_in);
		else if (opname.equalsIgnoreCase("add")) {
			ArrayList list =  new ArrayList();
			ArrayList list_in =  new ArrayList();
			BasicDBObject inQuery = new BasicDBObject();
			if (bdo_in != null && bdo_in.get("product") != null)
				list = (ArrayList)bdo_in.get("product");
			for(int p=0;p<list.size();p++){
				HashMap hs_one_prod=(HashMap)list.get(p);
				BasicDBObject one_prod=new BasicDBObject();
				one_prod.putAll(hs_one_prod);
				System.out.println("---"+one_prod);
				pp.addProduct(one_prod);
				list_in.add(one_prod.getString("product_id"));
				
			}	
			inQuery.append("product_id", list_in);
			
			System.out.println(inQuery);
			dbo_result = pp.listProductProfile(inQuery);
			
			
			
			
		}
			

		

		if (dbo_result != null)
			mot.putAll(dbo_result.toMap());


		return mot;

	}



}
