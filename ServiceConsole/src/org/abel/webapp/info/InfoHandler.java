/*
 * �������� 2004-4-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.info;

import java.io.*;
import java.util.*;
//import javax.sql.DataSource;
import java.sql.*;

//import org.abel.webapp.data.*;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class InfoHandler {

	Connection con = null;

	static String dbdri = "COM.ibm.db2.jdbc.app.DB2Driver";
	static String url = "jdbc:db2:ABELTEST";
	static String userid = "db2admin";
	static String passwd = "db2admin";
	static {
		try {

			Class.forName(dbdri).newInstance();
		} catch (Exception e) {

		}
	}
	/**
	 * 
	 */
	public InfoHandler() {
		super();

	}

	public void init(String cfgfilein) throws Exception {
		String cfgfile = null;
		cfgfile = cfgfilein;

		Properties commonwfcfg = new Properties();
		try {

			commonwfcfg.load(new FileInputStream(cfgfile));
		} catch (FileNotFoundException enfe) {
			throw new Exception("config file:" + cfgfile + " not found");
		}
	}

	//CREATE A PROCESS

	public Information getInfo(String infoid) throws Exception {
		Information info = new Information();
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			Statement stmt = con.createStatement();
			String sel = "select * from eipinfo where infoid=" + infoid;
			ResultSet rs = stmt.executeQuery(sel);

			while (rs.next()) {

				//System.out.println(rs.getString(1) + " | " + rs.getString(2));
				//System.out.println(rs.getString(3) + " | " + rs.getString(4));
				//System.out.println(rs.getString(5) + " | " + rs.getString(6));
				info.setTitle(rs.getString(2));
				info.setContent(rs.getString(3));
				info.setCreateTime((java.util.Date) rs.getTimestamp(4));

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return info;
	}

	public Vector getInfos(boolean storedinbusflag) throws Exception {
		Vector infobox = new Vector();
		Information info = new Information();
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			Statement stmt = con.createStatement();
			String sel = "select * from eipinfo where"; // infoid="+infoid;
			ResultSet rs = stmt.executeQuery(sel);

			while (rs.next()) {

				//System.out.println(rs.getString(1) + " | " + rs.getString(2));
				//System.out.println(rs.getString(3) + " | " + rs.getString(4));
				//System.out.println(rs.getString(5) + " | " + rs.getString(6));
				info.setTitle(rs.getString(2));
				info.setContent(rs.getString(3));
				info.setCreateTime((java.util.Date) rs.getTimestamp(4));
				infobox.add(info);

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return infobox;
	}

	public void addInfo(Information info) throws Exception {
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			Statement stmt = con.createStatement();
			//INFOID               INFOTITLE                                INFOCONTEN
			String sel =
				"insert into eipinfo (infotitle,infocontent,infocate) values ("
					+ info.getTitle()
					+ ",'"
					+ info.getContent()
					+ "',"
					+ "2"
					+ ")";
			stmt.executeUpdate(sel);

			stmt.close();
			con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		InfoHandler fm = new InfoHandler();
		//fm.setStatus("0","n002","0");
		//fm.setRole("Manager Asistant","Just a testing role");
		//Information info=fm.getInfo("1");
		//		System.out.println("Title: "+info.getTitle());
		//		System.out.println("Content: "+info.getContent());
		//		System.out.println("CreateDate: "+info.getCreatetime());
		//DOBase dbase=new DOBase();
		//dbase.set("Test value");

		//fm.setData(dbase,"IN",0);
		//fm.createProcess("p001","abelwang");
		//System.out.print(fm.getData("2").get());
		//fm.getProcessState("");
		//fm.getStatus("");

		Vector infobox = fm.getInfos(false);
		for (int i = 0; i < infobox.size(); i++)
			System.out.println(
				"Info No."
					+ i
					+ "\t"
					+ ((Information) infobox.get(i)).getTitle()
					+ "\t"
					+ ((Information) infobox.get(i)).getContent());

	}

}
