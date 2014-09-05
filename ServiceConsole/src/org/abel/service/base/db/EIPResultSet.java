//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\EIPResultSet.java

package org.abel.service.base.db;

import java.util.*;
import java.sql.*;


/**
 * @author Abel Wang
 * For small size data object.
 */
public class EIPResultSet {
	Vector<Vector<String>> result = new Vector<Vector<String>>();
	ResultSetMetaData rmd=null;
	Vector<Object> currentrecord = new Vector<Object>();
	int colsnumber = 0;
	int currentroenum = -1;

	/**
	 * @param sn
	 * @param vrecord
	 * @roseuid 3F4DB3180002
	 */
	public void put(int sn, Vector<String> vrecord) {
		result.add(sn, vrecord);
	}

	public void putMD(ResultSetMetaData rmdin) {
		rmd=rmdin;
	}

	public ResultSetMetaData getMD() {
		return rmd;
	}

	/**
	 * @return int
	 * @roseuid 3F4DB3180011
	 */
	public int size() {
		return result.size();
	}

	/**
	 * @param sn
	 * @return java.lang.String
	 * @roseuid 3F4DB318001F
	 */
	public String getString(int sn) {
		String rt = null;
		if (sn > 0 && sn <= currentrecord.size()) {
			if (currentrecord.elementAt(sn - 1) != null) {
				Object oo = currentrecord.elementAt(sn - 1);
				rt = oo.toString();
			}
		}
		return rt;
	}

	/**
	 * @param sn
	 * @return int
	 * @roseuid 3F4DB3180021
	 */
	public int getInt(int sn) {
		int rltint = -1;
		try {
			Integer rti = new Integer(getString(sn));
			rltint = rti.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rltint;
	}

	/**
	 * @return boolean
	 * @roseuid 3F4DB318002F
	 */
	public boolean isLast() {
		boolean rt = false;
		if (currentroenum == (result.size() - 1)) {
			rt = true;
		}
		return rt;
	}

	/**
	 * @return boolean
	 * @roseuid 3F4DB3180030
	 */
	public boolean next() {
		boolean rt = false;
		currentroenum++;
		if ((result != null) && (currentroenum < result.size())) {
			currentrecord = (Vector) result.elementAt(currentroenum);
			rt = true;
		}

		return rt;
	}

	/**
	 * @return java.util.Vector
	 * @roseuid 3F4DB318003E
	 */
	public Vector<Vector<String>> get() {
		return result;
	}

	/**
	 * added by Paul Gu..(2003/9/2)
	 * @return boolean
	 * @roseuid 3F569F6C003E
	 */
	public boolean first() { //throws SQLException;
		boolean valid = false;
		if (result != null) {
			currentroenum = 0;
			valid = true;
		}
		return valid;
	}

	/**
	 * added by Paul Gu..(2003/9/2)
	 * @return boolean
	 * @roseuid 3F569F6C004E
	 */
	public boolean last() { //throws SQLException;
		boolean valid = false;
		if (result != null) {
			currentroenum = result.size() - 1;
			valid = true;
		}
		return valid;
	}

	/**
	 * added by Paul Gu..(2003/9/2)
	 * @return int
	 * @roseuid 3F569F6C005D
	 */
	public int getRow() { //throws SQLException;
		return currentroenum;
	}

	/**
	 * added by Paul Gu..(2003/9/2)
	 * @param row
	 * @return boolean
	 * @roseuid 3F569F6C006D
	 */
	public boolean absolute(int row) { //throws SQLException;
		boolean valid = false;
		if (null == result) {
			return valid;
		}
		if (0 == row) { //????��??��?��??..
			return true;
		} else if (row > 0) {
			if (row > result.size()) {
				return false;
			}
			valid = true;
			currentroenum = row - 1;
		} else {
			if (result.size() < -row) {
				return false;
			}
			valid = true;
			currentroenum = result.size() + row + 1;
		}
		return valid;
	}

	/**
	 * added by Paul..((2003/9/3))
	 * @roseuid 3F569F6C007E
	 */
	public void beforeFirst() { //throws SQLException;
		currentroenum = -1;
	}

	/**
	 * @param args
	 * @roseuid 3F570F4C01E4
	 */
	public static void main(String[] args) {

		EIPResultSet er = new EIPResultSet();
		Vector<String> vvv = new Vector<String>();
		vvv.add("a");
		vvv.add("b");
		er.put(0, vvv);
		er.put(1, vvv);
		er.put(2, vvv);
		er.put(3, vvv);

		while (er.next()) {
			String a = er.getString(1);
			String str = er.getString(2);
			System.out.print("\nid=" + a);
			System.out.print("  ==============> name=" + str);
			System.out.print("");
			System.out.print(er.isLast());
		}
		//rs.close();    
	}
}
