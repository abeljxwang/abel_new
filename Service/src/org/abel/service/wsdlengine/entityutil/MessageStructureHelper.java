/*
 * 创建日期 2005-5-24
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.wsdlengine.entityutil;
import java.util.ArrayList;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.entity.MessageStructure;


/**
 * @author Abel Wnag
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MessageStructureHelper {

	public ArrayList<MessageStructure> DelDuplicate(ArrayList<?> messageList, String flag) {
		ArrayList<MessageStructure> result = new ArrayList<MessageStructure>();
		MessageStructure ms = null;
		for (int i = 0; i < messageList.size(); i++) {
			ms = (MessageStructure) messageList.get(i);
			if (checkList(result, ms, flag) == false) {
				// the group message hasn't been in the group
				result.add(ms);
			}

		}
		return result;
	}

	/**
	 * If the group mssage has been in the group, return true, else return false.
	 * @param messageList
	 * @param m1
	 * @return
	 */
	public boolean checkList(
		ArrayList<MessageStructure> messageList,
		MessageStructure m1,
		String flag) {
		boolean result = false;
		MessageStructure ms = null;
		for (int i = 0; i < messageList.size(); i++) {
			ms = (MessageStructure) messageList.get(i);
			
			if ((flag.equalsIgnoreCase(ConstantVariables.DUPLICATE_MULTIPLE) && equalMulti(ms, m1) == true)
				|| (flag.equalsIgnoreCase(ConstantVariables.DUPLICATE_SINGLE) && equal(ms, m1) == true)) {
			
				result = true;
				break;
			}
			
		}
		return result;
	}
	/**
	 * Judge wether two group message are equal.
	 * @param m1
	 * @param m2
	 * @return boolean
	 */
	public boolean equal(MessageStructure m1, MessageStructure m2) {
		boolean result = false;
		if ((m1.getMessageID() == m2.getMessageID())
			&& (m1.getMetaID() == m2.getMetaID())
			&& (m1
				.getProviderFieldName()
				.equalsIgnoreCase(m2.getProviderFieldName())
				&& m1.getRequesterFieldName().equalsIgnoreCase(
					m2.getRequesterFieldName()))) {
			result = true;
		}

		return result;
	}

	/**
	 * Judge wether two message(used for multiple steps)
	 * @param m1
	 * @param m2
	 * @return boolean
	 */
	public boolean equalMulti(MessageStructure m1, MessageStructure m2) {
		boolean result = false;

		if (m1
			.getRequesterFieldName()
			.equalsIgnoreCase(m2.getRequesterFieldName())
			&& m1.getWsdlType().equalsIgnoreCase(m2.getWsdlType())) {
			result = true;
		}

		return result;
	}

}
