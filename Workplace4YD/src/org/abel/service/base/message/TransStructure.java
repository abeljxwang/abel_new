/*
 * �������� 2004-10-12
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.base.message;

import java.util.Properties;
import java.util.Vector;


/**
 * @author abelwang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class TransStructure {

	Properties basicvalues = new Properties();

	Properties processes = new Properties();
	Vector pidkeys=new Vector();
	String operation = MSGBASE.TRAN_OPERATION_SINGLE;
	String currentprocessid = null;

	/**
	 * 
	 */

	String tmpresult = null;
	public TransStructure() {
		super();
	}

	public static void main(String[] args) {
	}

	public void setBasicAttr(String key, String value) {
		basicvalues.setProperty(key, value);
	}
	public String getBasicAttr(String key) {
		tmpresult = null;

		if (basicvalues.getProperty(key) != null)
			tmpresult = basicvalues.getProperty(key);

		return tmpresult;
	}
	//-------------------
	public void setOperation(String value) {
		operation = value;
	}
	public String getOperation() {
		return operation;
	}
	//------------------
	public void setCurrentProcess(String value) {
		currentprocessid = value;
	}
	public String getCurrentProcess() {
		return currentprocessid;
	}

	public Vector getProcessIDs() {

		return pidkeys;

	}

	public TranProcessDesc getProcess(String id) {
		TranProcessDesc resulttmp = new TranProcessDesc();
		if (processes.get(id) != null)
			resulttmp = (TranProcessDesc) processes.get(id);
		return resulttmp;

	}

	public void setProcess(String id, TranProcessDesc trandes) {
		
		if(processes.get(id)==null) pidkeys.add(id);
		processes.put(id, trandes);
	}

}
