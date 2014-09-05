package org.abel.webapp.commodel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.abel.webapp.db.DBHandler;
import org.abel.webapp.db.EIPResultSet;

public class AutoCompleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		doPost( request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		//String sql=null;
		//Enumeration names=request.getParameterNames();
		//while(names.hasMoreElements()){
		//	System.out.println(names.nextElement().toString());
		//}
		
		String param_value_name=request.getParameter("term")==null?"":request.getParameter("term");
		String param_value_type=request.getParameter("type")==null?"":request.getParameter("type");
		// String curString=param_value_name;

		DBHandler dbh=new DBHandler();
		EIPResultSet resultSet=null;
		
		try {
			//得到数据集
			//System.out.println(createSql(param_value_type,param_value_name));
			
			resultSet=dbh.query(createSql(param_value_type,param_value_name),10);
			//解析数据集,
			String rData=parseResultSet(resultSet);
			response.setContentType("text/html;charset=UTF-8");
        	PrintWriter out = response.getWriter();
        	out.print(rData);
        	out.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private String parseResultSet(EIPResultSet result)
	{
		if(result==null)
		{
			return "";
		}
		String data="";
		while(result.next())
			
			// {"id":"Branta canadensis","label":"Greater Canada Goose","value":"Greater Canada Goose"},
			//data=data+",'"+result.getString(1)+"'";
		data=data+",{\"id\":\""+result.getString(1)+"\", \"label\":\""+result.getString(2)+"\", \"value\":\""+result.getString(1)+"\"}";
		data=data.startsWith(",")?data.substring(1):data;
		data="["+data+"]";
		
		return data;
	}

	private String createSql(String param_value_type, String curValue)
	{
			 if(param_value_type.equalsIgnoreCase("metadata")) return "select mname,mcname from metadata where upper(mname)  like upper('"+curValue +"')||'%' order by mname asc";
			 else if(param_value_type.equalsIgnoreCase("entity")) return "select eid,ename from MSGELEMENT where type='CPX' and upper(ename)  like upper('"+curValue +"')||'%' order by ename asc";
			 else return null;
	}
	
	
}
