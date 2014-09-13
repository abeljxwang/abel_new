package com.cnpc.oms.model;

//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Properties;
//import java.util.Vector;


import org.abel.webapp.configure.ActionCfg;
import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

import com.cnpc.oms.poc.MenueElement;
import com.cnpc.oms.poc.UserProfile;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PocLogonModel extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
		Request request,
		Response response,
		ActionCfg conf) {
			try{

		//ArrayList al = new ArrayList();
		//System.out.println(request.getParameter("userid"));

			 UserProfile up=new UserProfile();
		     response.setSResult("ID","user1");
		     
		    

			 up.setUser("user1","Admin");
			 up.setAttribute("DEPT","SOA COE");
			 up.setAttribute("PICURL","user1.jpg");
			 
			 MenueElement me_1=new  MenueElement();
			 me_1.setName("Test User");
			 me_1.setURL("/ET/SOAAdmin/querySLst");
			
			MenueElement me_2=new  MenueElement();
			me_2.setName("Test User 2");
			me_2.setURL("/ET/SOAAdmin/");			 
			
			
			
			up.add(me_1);
			up.add(me_2);
			//up.add(me_3);
			//up.add(me_4);
			
			
			response.setSResult("user_profile",up);
			

					 
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return conf.getActionFword(); //afd;

	}

}
