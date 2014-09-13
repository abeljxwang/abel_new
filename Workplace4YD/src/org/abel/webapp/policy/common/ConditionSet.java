/*
 * �������� 2004-3-4
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.common;
import java.util.*;

import org.abel.webapp.policy.base.Operator;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ConditionSet {

	Vector cbox=new Vector();
	String algorithm="AND"; 
	
	public ConditionSet() {

	}
	
	public void set(Condition ci){
		cbox.add(ci);
	}

	public Vector getAll(){
		return cbox;
	}

	public void setOpt(String copt){
		algorithm=copt;
	}

	public String getOpt(){
		return algorithm;
	}

	public boolean parsing(BaseDO bdo,WFParameters p) {
		boolean result=true;
		boolean btmp=false;
		Condition tmp;
		for(int i=0;i<cbox.size();i++){
			tmp=(Condition)cbox.elementAt(i);
			btmp=tmp.parsing(bdo,p);
			//System.out.print(btmp+"-----"+result+"  "+algorithm);
			result=Operator.getBooleanResult(result,btmp,algorithm); 
			//System.out.print(result+" �� result");
			// parsingItem(tmp);
		}
		return result;
	}

	public static void main(String[] args) {
		
		ConditionSet cst=new ConditionSet();
			  Condition cnd=new Condition();
			  cnd.addItem("TARGETFLAG EQ '02'");
			  cst.set(cnd);
		
			  //tran.setConditionSet(cst);
		
			  BaseDO bdo=new BaseDO();
			  bdo.setParameter("TARGETFLAG","02");
			  WFParameters wfp = new WFParameters();

		
			  System.out.println(cst.parsing(bdo, wfp));
		
	}
}
