/*
 * �������� 2005-4-22
 * CCB EAIH Project team
 */
package org.abel.service.soa.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import org.abel.webapp.db.DBHandler;



/**
 * @author wangjx@cn.ibm.com
 * 
 * 
 */
public class ServiceListDAO {

	/**
	 * 
	 */
	String dbName = "eai_trans_list";

	public ServiceListDAO() {
		super();

	}

	/**
	 * ������е�transactionID���б�
	 * 
	 * @return ArrayList
	 */
	public static Hashtable getServiceBasicList() {
		Hashtable result = new Hashtable();
		ServiceDesc sdesc = null;
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString
					.append("select * from servicelist");

			// get connection to db
			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sqlString.toString());

			while (rs.next()) {
				if (rs.getString("serviceid") != null) {
					sdesc = new ServiceDesc(rs.getString("serviceid"));
					
					sdesc.setChineseName(rs.getString("servicename"));
					sdesc.setEndPoint(rs.getString("endpoint"));
					sdesc.setDesc(rs.getString("servdesc"));
					//System.out.print(b)
					// result.s(rs.getLong("mdfvalue"));
					result.put(sdesc.getName(), sdesc);
					//System.out.print(sdesc);
				}

			}

			dbh.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	/**
	 * ������е�transactionID���б�
	 * 
	 * @return ArrayList
	 */
	public static ServiceDesc getServiceBasic(String serviceid) {
		//Hashtable result = new Hashtable();
		ServiceDesc sdesc = null;
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString
					.append("select A.ServiceID, A.ServiceName, A.ServDesc,A.endpoint,B.operation_name,B.input,b.output from  servicelist A left join  serviceoperation B on A.ServiceID=B.ServiceID where A.serviceid='"+serviceid+"'");

			// get connection to db
			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sqlString.toString());
			OperationDesc op=null;
			if (rs.next()) {
				if (rs.getString("serviceid") != null) {
					sdesc = new ServiceDesc(rs.getString("serviceid"));
					sdesc.setChineseName(rs.getString("servicename"));
					sdesc.setEndPoint(rs.getString("endpoint"));
					sdesc.setDesc(rs.getString("servdesc"));
					//sdesc.setDesc(rs.getString("operation_name"));
					op=new OperationDesc(rs.getString("operation_name"));
					op.setInputName(rs.getString("input"));
					op.setOutputName(rs.getString("output"));
					sdesc.setOperation(op);
					
					while (rs.next()) 	{
						op=new OperationDesc(rs.getString("operation_name"));
						op.setInputName(rs.getString("input"));
						op.setOutputName(rs.getString("output"));
						sdesc.setOperation(op);					
						
					}
					
					//System.out.print(b)
					// result.s(rs.getLong("mdfvalue"));

				}

			}
			System.out.println("A: "+sdesc);

			dbh.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return sdesc;
	}
	
	// for testing
	public static void main(String[] a)

	{

		ServiceListDAO sdao = new ServiceListDAO();

		 ServiceDesc h = sdao.getServiceBasic("Order_Mgr");
		 //h.getOperation("query_Well_Info").getInputName();
		
		System.out.println(h);
		 //System.out.println(h.getOperation("query_Well_Info").getInputName());

	}

}
