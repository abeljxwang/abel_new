//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\DBHandler.java

package org.abel.service.base.db;

import java.sql.*;
import java.io.*;



/**
 * @author Abel Wang, Wang Xi
 * Change to current vertion:
 * 1: rewrite the method query
     * 2: add new method cmfunction, service all three function:update,delete,insert;
 * Sample:
 * 1:
 * DBHandler dh = new DBHandler();
 * EIPResultSet eresult = dh.query("select * from CONTACTLIST");
 * dh.close();
 * 2:
 * dh.insert("insert into CONTACTLIST (name) values('wjx')");
 * dh.close();
 */
public class DBHandler {
	boolean autocf = false;
	static String objencode = "GBK";
	static String oriencode = "GBK";
	/**
	 * Constructor for DBHandler.
	 */
	DBConnection dbc = null;

	/**
	 * @param aurocnnflg
	 * @roseuid 3F569F6B0242
	 */
	public DBHandler(boolean aurocnnflg) {
		dbc = createVirtualDbConn(aurocnnflg);
	}

	/**
	 * @roseuid 3F4DB318012B
	 */
	public DBHandler() {
		dbc = createVirtualDbConn(false);

	}

	/**
	 * @param sql
	 * @return com.kmtc.eip.db.EIPResultSet
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB318012C
	 */
	public EIPResultSet query(String sql)
		throws SQLException, UnsupportedEncodingException {
		String sqltmp = encode(sql);
		if (autocf) {
			dbc = createDbConnection();
		}

		DBQuery dbq = createDbQuery();

		EIPResultSet rs = dbq.execute(dbc, sqltmp);
		if (autocf) {
			close();
		}
		return rs;
	}

	public EIPResultSet query(String sql,int number)
		throws SQLException, UnsupportedEncodingException {
		String sqltmp = encode(sql);
		if (autocf) {
			dbc = createDbConnection();
		}

		DBQuery dbq = createDbQuery();

		EIPResultSet rs = dbq.execute(dbc, sqltmp,number);
		if (autocf) {
			close();
		}
		return rs;
	}

	/**
	 * @param sql
	 * @return java.sql.ResultSet
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB3180168
	 */
	public ResultSet stdquery(String sql)
		throws SQLException, UnsupportedEncodingException {
		String sqltmp = encode(sql);
		ResultSet rs = null;
		if (dbc != null) {
			Statement stmt = dbc.createStatement();
			//		System.out.println(sql);
			rs = stmt.executeQuery(sqltmp);
			//close();
		}
		return rs;
	}

	/**
	 * @param sql
	 * @return boolean
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB31801A6
	 */
	public boolean update(String sql)
		throws SQLException, UnsupportedEncodingException {
		return cmfunction(sql);
	}

	/**
	 * @param sql
	 * @return boolean
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB31801E5
	 */
	public boolean delete(String sql)
		throws SQLException, UnsupportedEncodingException {

		return cmfunction(sql);
	}

	/**
	 * @param sql
	 * @return boolean
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB3180222
	 */
	public boolean insert(String sql)
		throws SQLException, UnsupportedEncodingException {
		return cmfunction(sql);
	}

	/**
	 * @param sql
	 * @return boolean
	 * @roseuid 3F4DB3180252
	 */
	public boolean cmfunction(String sql) throws UnsupportedEncodingException,SQLException{
		//System.out.println(sql);
		String sqltmp = encode(sql);
		//System.out.println(sqltmp);
		boolean rt = false;
		DBFunction dbf = new DBFunction();
		if (autocf) {
			//dbc = new DBConnection();
			//modified by Paul..2003/10/14 18:14
			try {
				dbc = createDbConnection();
				
				dbf.execute(dbc, sqltmp);

				close();
				rt = true;
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				if (dbc != null)
					close();
				dbc = null;
				dbf=null;
			}

		}
		else {
			try{
				dbf.execute(dbc, sqltmp);
				rt = true;
			}catch (SQLException sqle) {
				throw sqle;
			}
				
		}

		

		return rt;
	}

	/**
	 * @roseuid 3F4DB3180262
	 */
	public void close() {
		try {
			dbc.close();
		} catch (SQLException sqle) {
			dbc = null;
			System.out.println(
				"DB Connection close error: " + sqle.getMessage());
		}
	}

	/**
	 * Just for testing
	 * @param args
	 * @roseuid 3F570F4C01A5
	 */

	public String encode(String orgstr) throws UnsupportedEncodingException {
		String resulttemp = null;
		byte[] bta = orgstr.getBytes(oriencode);
		resulttemp = new String(bta, objencode);
		return resulttemp;
	}

	public String decode(String orgstr) throws UnsupportedEncodingException {
		String resulttemp = null;
		byte[] bta = orgstr.getBytes(objencode);
		resulttemp = new String(bta, oriencode);
		return resulttemp;
	}

	/**
	 * @param sql
	 * @return java.sql.ResultSet
	 * @throws java.sql.SQLException
	 * @roseuid 3F5D6D1D038B
	 */
	public static void main(String[] args) {

		try {
			

			DBHandler dh = new DBHandler();
			//System.out.println(dh.encode("法"));
			//EIPResultSet eresult = dh.query("SELECT UNIT, PTZJSL,PTZJJG,PTCJ, PASJG,PASCJ, LDXSJG,LDXSQX, ISDNSL,ISDNJG,ISDNCJ, ADSLSL,ADSLJG,ADSLCJ, XNWSL,XNWJG,XNWCJ, JJYSL,JJYJG, TJSJ, OPRID,JH,DEVRPTID from IS_DEVRPT where TJQSJ='20030930' AND TJZSJ='20030930' AND DEVRPTTYPE='Day'");
			String sqq =
				"select a.KPIID as KPIID,a.KPINAME as KPINAME,d.DEPTNAME as DEPTNAME,b.KPIFTHVALUE as KPIFTHVALUE,"
					+ " b.KPIFTHSCORE as KPIFTHSCORE,a.KPISCALE as KPISCALE"
					+ " from KPI_KPIDEF a,KPI_COMACTVALUE b,COM_DEPT d "
					+ " where (b.KPISEQID in (select KPISEQID from KPI_KPIDEF where (KPIYEAR='2003') and (KPILEVEL=1) "
					+ " and (KPICURFLAG=1) and (KPINAME ='主要归口业务收入')))  "
					+ " and ((b.KPITIMEPARM='2')) and (b.KPIFTHYEAR='2003') and (b.KPISEQID=a.KPISEQID) "
					+ " and (b.KPISCORESTATE=2) and (b.KPIDATASTATE=5) and (a.KPIYEAR='2003') and (a.AUDITDEPTID=d.DEPTID)";

			String sqq2 =
				"select a.KPIID as KPIID,a.KPINAME as KPINAME,d.DEPTNAME as DEPTNAME,b.KPIFTHVALUE as KPIFTHVALUE,"
					+ "b.KPIFTHSCORE as KPIFTHSCORE,a.KPISCALE as KPISCALE "
					+ "from KPI_KPIDEF a,KPI_COMACTVALUE b,COM_DEPT d "
					+ "where (b.KPISEQID in (select KPISEQID from KPI_KPIDEF where (KPIYEAR='2003') and (KPILEVEL=1) "
					+ "and (KPICURFLAG=1) and ((KPICYCTYPE=4)) and (AUDITDEPTID = '01005')  )) "
					+ "and ((b.KPITIMEPARM='1')) and (b.KPIFTHYEAR='2003') and (b.KPISEQID=a.KPISEQID) and (b.KPISCORESTATE=2) and (b.KPIDATASTATE=5)"
					+ "and (a.KPIYEAR='2003') and (a.AUDITDEPTID=d.DEPTID)";

			String sql3 =
				"SELECT sum(PTZJJG),sum(PTCJ), sum(PASJG),sum(PASCJ), sum(LDXSJG),sum(ISDNJG),sum(ADSLSL),sum(ADSLJG),sum(XNWJG),sum(JJYJG) from IS_DEVRPT where TJQSJ>='20021221' AND DEVRPTTYPE='Day'";

			String sql4="SELECT DISTict(QUERYID,DEVRPTTYPE,TJQSJ) from IS_DEVRPT where DEVRPTTYPE='Day' GROUP BY QUERYID,DEVRPTTYPE";
			
			String sql5="SELECT UNIT,PTZJSL,PTZJJG,PTCJ, PASJG,PASCJ, LDXSJG,LDXSQX, ISDNSL,ISDNJG,ISDNCJ, ADSLSL,ADSLJG,ADSLCJ, XNWSL,XNWJG,XNWCJ,DMTZC,DMTZC, JJYSL,JJYJG, TJSJ, OPRID,JH,DEVRPTID,QUERYID  from IS_DEVRPT where TJQSJ='20031029' AND TJZSJ='20031125' AND DEVRPTTYPE='Day'";
			String sql6="SELECT UNIT,PTZJSL,PTZJJG,PTCJ, PASJG,PASCJ, LDXSJG,LDXSQX, ISDNSL,ISDNJG,ISDNCJ, ADSLSL,ADSLJG,ADSLCJ, XNWSL,XNWJG,XNWCJ,DMTZC,DMTZC, JJYSL,JJYJG, TJSJ, OPRID,JH, QUERYID, DEVRPTID from IS_DEVRPT where TJQSJ='20030921' AND TJZSJ='20031020' AND DEVRPTTYPE='Month'";
			String sql7="SELECT sum(PTZJJG),sum(PTCJ), sum(PASJG),sum(PASCJ), sum(LDXSJG),sum(ISDNJG),sum(ADSLSL),sum(ADSLJG),sum(XNWJG),sum(JJYJG) from IS_DEVRPT where TJQSJ>='20021221' AND DEVRPTTYPE='Day'";			
			String sql8="SELECT DEVRPTTYPE,TJQSJ,TJZSJ,PTZJSL,QUERYID from IS_DEVRPT where DEVRPTTYPE='Week'";
			String sql9="SELECT A.*,B.QUERYID FROM IS_DEVRPT2 A,IS_DEVRPT B WHERE A.QUERYID=B.QUERYID ";
			
			String sql10="SELECT UNIT,0,sum(PTZJJG),sum(PTCJ), sum(PASJG),sum(PASCJ), sum(XNWJG),sum(ISDNJG),sum(ADSLJG),sum(JJYJG),sum(DMTZC) from IS_DEVRPT where DEVRPTTYPE='Month' AND TJQSJ >='20021221' AND TJZSJ <= '20031221' GROUP BY UNIT";
			String sql11="SELECT UNIT,PTZJSL,PTZJJG,0,PTCJ,0, PASJG,0,LDXSJG,LDXSQX,XNWJG,0,ISDNJG,0,ADSLSL,ADSLJG,0,DMTZC,0,0,0,0, TJSJ, OPRID,JH,DEVRPTID, QUERYID from IS_DEVRPT where TJQSJ='20031103' AND TJZSJ='20031110' AND DEVRPTTYPE='Week'";
			String sql15="SELECT UNIT from IS_DEVRPT where DEVRPTTYPE='Day'";
			String sql16="SELECT * from metadata ";
			EIPResultSet eresult = dh.query(sql16);
			System.out.println(eresult.size());
			int sizea=eresult.getMD().getColumnCount();
			while (eresult.next()) {
				for(int r=1;r<=sizea;r++)
				System.out.print(dh.decode(eresult.getString(r)+"")+"--");

				System.out.println();
				//System.out.println(eresult.getString(1));

			}

			ResultSetMetaData mmm = eresult.getMD();
			int aaa = mmm.getColumnCount();
			for (int k = 1; k < aaa + 1; k++) {
				System.out.println(mmm.getColumnName(k));

			}
			//dh.insert("insert into tmp (name) values('网精细')");
			//dh.insert("insert into test values ('法','dd',1,CURRENT DATE) ");
			dh.close();
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

	//added by Paul.. [2003/9/28 16:04]  //to overrided..
	protected DBConnection createDbConnection() {
		return new DBConnection();
	}

	//added by Paul.. [2003/9/27 10:00]
	protected DBConnection createVirtualDbConn(boolean aurocnnflg) {
		DBConnection dbc = null;
		//note: just to copy Abel's codes in Constructor..
		if (aurocnnflg) {
			autocf = aurocnnflg;
		} else {
			dbc = new DBConnection();
			//dbc = createDbConnection();
			//System.out.print(dbc);
		}
		return dbc;
	}

	//added by Paul.. [2003/9/28 16:04]  //to overrided..
	protected DBQuery createDbQuery() {
		return new DBQuery();
	}

}
