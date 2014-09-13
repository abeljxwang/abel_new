/*
 * Created on 2004-10-15
 *
 */
package org.abel.service.base.message;



/**
 * @author bluesunwind
 *
 */
public class MQMsgRef implements Cloneable {
	public static final String KEY_INSTANCE = "MQMsgRef.Instance";
	public byte[] MQMsgId = null;
	public byte[] MQMsgBody = null;

	public MQMsgRef() {
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			/* Never reach here.*/
			return new MQMsgRef();
		}
	}

	public static void main(String[] args) {		
	}
}//end of calss
