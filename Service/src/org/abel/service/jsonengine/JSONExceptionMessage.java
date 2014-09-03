
package org.abel.service.jsonengine;


import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.json.simple.JSONObject;



public class JSONExceptionMessage {
	
	

	public static void  createFaltMsg(OutputStream out, Exception e) throws Exception {
		JSONObject  message = new JSONObject();
		message.put("Status", "500");
		message.put("error_info", e.getMessage());
		message.put("ExceptionActor","Rest4JSONPEngine");
		message.put("ExceptionCode","Server");
		OutputStreamWriter writer=new OutputStreamWriter(out);

		message.writeJSONString( writer);
		 writer.close();
	}
	
	public static JSONObject  createFaltMsg(Exception e)  {
		JSONObject  message = new JSONObject();
		message.put("Status", "500");
		message.put("error_info", e.getMessage());
		message.put("ExceptionActor","Rest4JSONPEngine");
		message.put("ExceptionCode","Server");
		return message;



	}

	public static void main(String[] aa) throws Exception{
		//MessageFactory msgFactory = MessageFactory.newInstance();
		createFaltMsg(System.out,new Exception("wr"));
		
	}
}
