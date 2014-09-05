package com.ibm.ais.soa.commonmodel;

//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Properties;
//import java.util.Vector;

import java.sql.ResultSet;

import org.abel.webapp.configure.ActionCfg;
import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.db.DBHandler;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

import com.ibm.ais.util.MenueElement;
import com.ibm.ais.util.UserProfile;

/**
 * @author administrator
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class PocLogonModel extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(Request request, Response response,
			ActionCfg conf) {
		try {
			
			

			// construct sql string.
			String userid="";
			//System.out.println(request.getParameter("USER_ID"));
			if(request.getParameter("USER_ID")!=null) userid=request.getParameter("USER_ID");
			
			//String sql_user = "SELECT USER_ID,NAME,CNAME,GENDER,COMPANY " +
			//		" FROM USERBASICINFO " +
			//		" where  USER_ID = '"+userid+"'";
			
			String sql_user= "select A.USER_ID,A.NAME name,A.COMPANY COMPANY, B.ROLE_ID,C.name rolename " +
					" from userbasicinfo A, USERTOROLE B,ROLE C " +
					" where A.user_id=b.user_id and C.role_id=B.role_id and " +
					" A.user_id ='"+userid+"'";
			//System.out.println(sql_user);
			
			String sql = "SELECT A.user_id USER_ID, A.MENUID MENUID,b.funcname MENUNAME, b.func_link MENU_LINK," +
					"A.isopen ISOPEN,b.desc MENUDESC,b.catalog CATALOG " +
					"FROM USER_AUTHORIZATION A, func_tree B " 
					+" where  A.MENUID = B.FUNCID AND A.USER_ID = '"+userid+"'";

//			String sql2 = "SELECT A.user_id USER_ID,A.MENUID MENUID,b.funcname MENUNAME, b.func_link MENU_LINK," +
//			"A.isopen ISOPEN,b.desc MENUDESC,b.catalog CATALOG " +
//			"FROM USER_AUTHORIZATION A, func_tree B " 
//			+" where  A.MENUID = B.FUNCID AND A.USER_ID = '"+userid+"'";
			
			DBHandler dbh = new DBHandler();
			
			// ArrayList al = new ArrayList();
			UserProfile up = new UserProfile();
			
			ResultSet rsuser = dbh.stdquery(sql_user);
			while (rsuser.next()) {
				up.setUser(rsuser.getString("USER_ID"), rsuser.getString("NAME"));
				up.setAttribute("COMPANY", rsuser.getString("COMPANY"));
				up.setAttribute("ROLEID", rsuser.getString("ROLE_ID"));
				up.setAttribute("ROLENAME", rsuser.getString("ROLENAME"));
			}
			rsuser.close();
			
			
			
			
			ResultSet rs = dbh.stdquery(sql);
			while (rs.next()) {
				MenueElement me_1 = new MenueElement();
				me_1.setName(rs.getString("MENUNAME"));				
				me_1.setURL(rs.getString("MENU_LINK"));
				me_1.setID(rs.getString("MENUID"));
				
				up.add(me_1);
				
				//System.out.println(me_1);

			}
			rs.close();

			dbh.close();
			//System.out.println(up);
			if(up.getUserID()!=null)
			response.setSResult("user_profile", up);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conf.getActionFword(); // afd;

	}

}
