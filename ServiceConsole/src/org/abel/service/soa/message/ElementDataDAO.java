/*
 * 创建日期 2005-4-28
 * CCB EAIH Project team
 */
package org.abel.service.soa.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.abel.webapp.db.DBHandler;




/**
 * @author daijun@cn.ibm.com
 *
 * 
 */
public class ElementDataDAO {

	/**
	 * 
	 */

	private String dbName = "meta_data";

	public ElementDataDAO() {
		super();
	}

	public static ElementDesc  getMeta(String msg_ele_nid) throws Exception {
		ElementDesc  result = new ElementDesc();
		MetaDesc md=null;

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select A.EID, C.ENAME,A.MNAME,A.SN,A.SUBEID,A.SUBFLAG,A.RPTTIME, A.DF_VALUE, B.MCNAME,B.MTYPE");
			sqlString.append(" FROM (MSGELEMENT AS C RIGHT OUTER JOIN METADATA AS B RIGHT OUTER JOIN MSGELEMENTSTRUCTURE AS A  ON B.MNAME = A.MNAME ON C.EID = A.EID)");
			sqlString.append(" where A.EID='"+msg_ele_nid+"' ORDER BY A.SN ASC");
			
			/*
			 * SELECT A.EID, A.SN, C.ENAME, A.MNAME, A.SUBFLAG, A.SUBEID, A.RPTTIME, A.DF_VALUE, B.MCNAME, B.MTYPE
   FROM (MSGELEMENT AS C RIGHT OUTER JOIN METADATA AS B RIGHT OUTER JOIN MSGELEMENTSTRUCTURE AS A  ON B.MNAME = A.MNAME ON C.EID
= A.SUBEID)
   WHERE A.EID = 'E00004'
   ORDER BY A.SN ASC   
			 * */

			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sqlString.toString());
			String tmpstr=null;
			result.setID(msg_ele_nid);
			
			while(rs.next()) {
				result.setName(rs.getString("ENAME"));
				md = new MetaDesc();
				if(rs.getInt("SUBFLAG")==0){
				md.setName(rs.getString("MNAME"));
				md.setCN_Name(rs.getString("MCNAME"));
				tmpstr=rs.getString("DF_VALUE");
				if(rs.getString("MTYPE")!=null) md.setType(rs.getString("MTYPE"));
				if(tmpstr!=null&&tmpstr.length()>0) md.setDF_Value(rs.getString("DF_VALUE"));
				}
				else if (rs.getInt("SUBFLAG")==1){
					md.setName(rs.getString("SUBEID"));
					md.setSubElementFlag(true);
					md.setType("E");
				}

				if(rs.getInt("RPTTIME")>1) md.setRpt(rs.getInt("RPTTIME"));				

				result.AddMeta(md);
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}


	
	public static void main(String[] a) throws Exception{
		
		ElementDataDAO sdao = new ElementDataDAO();

		 ElementDesc h = sdao.getMeta("E00004");
		
		
		System.out.println(h);

		
	}

}
