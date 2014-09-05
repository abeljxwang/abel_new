/*
 * 创建日期 2004-10-12
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
