package org.abel.webapp.policy.base;
import org.abel.webapp.policy.meta.*;

import java.util.*;
import java.text.*;

public class PolicyEngine {

	//PolicyItem pr;
	static SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
	public static void main(String[] args) throws Exception {
		ContentMeta cmm=new ContentMeta();
		UserMeta umm=new UserMeta();

		//-------------------------------------------
		//Test Content Meta, cpntent profile msg
		cmm.set("Dept","AI");
		cmm.set("Role","ITA,ITS");
		cmm.set("StartTime","2004-10-10");
		cmm.set("EndTime","2005-10-10");
		//-------------------------------------------
		//Test UserMeta, user profile msg
		umm.set("Dept","AI");
		umm.set("Title","ITA");
		
		//-------------------------------------------
		//Policy from file
		
		Policy p=new Policy();
		
		//System.
		
		PolicyBox pb=PolicyPaser.parse("WebContent\\WEB-INF\\script\\Policy.xml");
		p=pb.get("001");
		System.out.println(System.currentTimeMillis());		
		System.out.println(pase(cmm,umm,p)+"--------------"+System.currentTimeMillis());
		
		
		//parsera.parse("NewFile.xml");
	}

	public static boolean pase(ContentMeta cm, UserMeta um, Policy p1)
		throws Exception {

		boolean rst = true;

		//Vector vv = p1.policies;
		
		int size=p1.size();
		//System.out.println("本策略包含 "+size+" 项内容");
		Object AO=null, BO=null;
		UnitObj A=null,B=null;
		PolicyItem pii=null;
		boolean tmpr=false;
		for (int i = 0; i < size; i++) {

			pii = p1.get(i);

			A = pii.A;
			B = pii.B;
	

			if (A.type.equals("User")) {
				AO = um.get(A.prop);
			} else if (A.type.equals("Content")) {
				AO = cm.get(A.prop);
			} else {
				Object oo = pasing(A.prop);
				AO = oo;
			}

			if (B.type.equals("User")) {
				BO = um.get(B.prop);
			} else if (B.type.equals("Content")) {
				BO = cm.get(B.prop);
			} else {
				BO = B.prop;
			}

			tmpr = Operator.getResult(AO, BO, pii.operator.value);
			
			//stem.out.println("策略条目 "+i+": ");
			//stem.out.println(A.type+"."+A.prop+"("+AO+") " +pii.operator.value+" "+B.type+"."+B.prop+"("+BO+")    验证结果："+tmpr);
			rst = rst & tmpr;

		}
		//System.out.println(rst + "--");
		System.out.println("总体结果："+rst);
		return rst;

	}

	public static Object pasing(String iii) {

		Object result = null;
		//Calendar cc = Calendar.getInstance();
		Date date = new Date();


		if (iii.equals("CURRENTDATE"))
			result = sdfd.format(date);
		else if (iii.equals("CURRENTTIME"))
			result = sdft.format(date);
		else
			result = iii;

		return result;
	}

}
