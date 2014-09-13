/*
 * Copyright (C) The IBM China BCS. All rights reserved.
 *
 */

package org.abel.service.base.message;

/**
 * CCBMSG
 *  A common constant parameter for MB-EAI.
  *
  * @author  Abel Wang;
  *
  *  @since 0.8.0 
  * */

public final class MSGBASE {

  public static final String EAI_STATUS_CIMP_COMPLETE="COMPLETE";	
  public static final String EAI_STATUS_CIMP_FAIL="FAIL";	



	public static final String TRAN_OPERATION_SINGLE="single";	
	public static final String TRAN_OPERATION_SERIAL="serial";	
	public static final String TRAN_OPERATION_PARALLEL="parallel";		

	public static final String MEAT_TYPE="type";
	public static final String MEAT_LENTH="length";
	public static final String MEAT_ENCRYPT="encrypt";
	


	public static final String MSG_ROOT="Message_ENV";
	public static final String MSG_HEADER="Message_Header";	
	public static final String MSG_TRAN_SN="message_sn";
	public static final String MSG_TRAN_ID="message_id";
	public static final String MSG_TRAN_NAME="name";
	public static final String MSG_TRAN_REQUEST="requester_id";
	//public static String MSG_TRAN_RESPONSE_STATUS="response_status";


	public static final String MSG_TRAN_ERROR="msg_response";
	public static final String MSG_TRAN_ERROR_NO="code";
	//public static final String MSG_TRAN_ERROR_TYPE="error_type";
	public static final String MSG_TRAN_ERROR_DESC="desc";
		
	public static final String MSG_TRAN_RESP_INFO="msg_response";	
	public static final String MSG_TRAN_RESP_CODE="code";	
	public static final String MSG_TRAN_RESP_STATUS="status";	
	public static final String MSG_TRAN_RESP_DESC="desc";	
				
	
	public static final String MSG_TRAN_START="start_timestamp";
	public static final String MSG_TRAN_END="end_timestamp";
	public static final String MSG_TRAN_STATUS="status";
	
	public static final String ROLLBACK_TRAN_INFO="rollback_info";
	public static final String ROLLBACK_TRAN_ORIG_SN="orignal_trans_sn";

	
	public static final String MSG_TRAN_TYPE="msg_type";


	public static final String MSG_TRAN_TIMEOUT="timeout";
	public static final String MSG_TRAN_ROLLBACK="msg_rollback";


	public static final String MSG_TRAN_PROCESS_ROOT="processes";
	public static final String MSG_TRAN_PROCESS="process";
	public static final String MSG_TRAN_PROCESSTIME="process_timestamp";
	public static final String MSG_TRAN_PROCESS_ROLLBACK="rollback";
	public static final String MSG_TRAN_TARGET="target_id";
	public static final String MSG_TRAN_TARGETQ="target_q";
	
	public static final String MSG_TRAN_OPERATION="operation";	
	public static final String MSG_TRAN_CURRENT="currentprocess";
	public static final String MSG_TRAN_PROCESS_ID="id";	

	public static final String MSG_TRAN_EXT_ATTR="ext_attributes";


	//add by mazhenmin
	public static final String MSG_MUTIREQUEST="multi_request";
	//add end
	
	public static final String MSG_REQUEST="request";	
	public static final String MSG_RESPONSE="response";	
	public static final String MSG_BODY="Message_Body";			

	public static final String MSG_ID="id";	
	public static final String ID="id";	

	public static final String FIELD_TYPE_NAME="p_type";	
	public static final String FIELD_TYPE_G="G";	
	public static final String FIELD_TYPE_A="A";	
	public static final String FIELD_TYPE_S="S";	
	
	public static final String MSG_TRAN_ORG_REQ_ID="org_requester_id";
	
	public static final String MSG_SND_TRANS_SN = "INM_SND_TX_LOG_NO";
	
	public static final String MSG_TRAN_VERSION_NO = "version_id";

	//add by tianbing 20050621 to support new data standard，返回报文头信息
	public static final String MSG_TRAN_PROVIDER_BIZ_DATE="provider_biz_date";
	public static final String MSG_TRAN_PROVIDER_PROCESS_DATE="provider_process_date";
	public static final String MSG_TRAN_PROVIDER_PROCESS_TIME="provider_process_time";
	public static final String MSG_TRAN_PROVIDER_TRANS_LOG="provider_trans_log";
	public static final String MSG_TRAN_PROVIDER_MSG_CODE="provider_msg_code";
	public static final String MSG_TRAN_PROVIDER_MSG_DESC="provider_msg_desc";
	
	//add by tianbing 20050621 to support new data standard
	public static final String MSG_TRAN_CHANNEL_ID="channel_id";
	public static final String MSG_TRAN_TRANS_DATE="msg_date";
	public static final String MSG_TRAN_TRANS_TIME="msg_time";
	public static final String MSG_TRAN_VERSION_ID="version_id";

}
