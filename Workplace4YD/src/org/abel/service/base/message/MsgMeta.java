/*
 * �������� 2004-10-12
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.base.message;

import java.util.Vector;


/**
 * MetaElement
 *.
 * @author Abel Wang
 */
public class MsgMeta {


	private Vector metas = new Vector();

	public MsgMeta() {
		super();
	}

	public void setMeta(MetaElement element) {
		metas.add(element);
	}
	public MetaElement getMeta(int key) {
		Object oo=metas.elementAt(key);
		MetaElement result=new MetaElement();
		if(oo!=null) result=(MetaElement)oo;
		return result;
	}

	public String getMetaName(int key) {
		return getMeta(key).getName();
	}
	public String getMetaType(int key) {
		return getMeta(key).getAttribute(MSGBASE.MEAT_TYPE);
	}
	public String getMetaLength(int key) {
		return getMeta(key).getAttribute(MSGBASE.MEAT_LENTH);
	}
	public String getMetaEnc(int key) {
		return getMeta(key).getAttribute(MSGBASE.MEAT_ENCRYPT);
	}		
}
