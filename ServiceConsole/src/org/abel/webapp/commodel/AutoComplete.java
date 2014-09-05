package org.abel.webapp.commodel;

import org.abel.webapp.configure.*;
import org.abel.webapp.db.DBHandler;
import org.abel.webapp.db.EIPResultSet;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

public class AutoComplete extends ModelBase {

	public ActionForward execute(Request request, Response response,
			ActionCfg conf) {

		String curString = null;
		curString = request.getParameter("key_a");
		try {
			String result = processT(curString);
			response.setRResult("result_au", result);
		} catch (Exception ee) {
			ee.printStackTrace();

		}

		return conf.getActionFword(); // afd;

	}

	public static void main(String[] args) throws Exception {

		AutoComplete ap = new AutoComplete();
		ap.processT("well");

	}

	public String processT(String curString) throws Exception {
		String rData = null;
		DBHandler dbh = new DBHandler();
		EIPResultSet resultSet = null;

		try {
			// 得到数据集
			resultSet = dbh.query(createSql("M", curString));
			dbh.close();
			// 解析数据集,
			rData = parseResultSet(resultSet, "M");
			resultSet = null;
			// response.setContentType("text/html;charset=UTF-8");
			// PrintWriter out = response.getWriter();
			System.out.print(rData);
			// out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rData;

	}


	private String parseResultSet(EIPResultSet result, String sType) {
		if (result == null) {
			return "";
		}
		String data = "";
		for (int i = 0; i < result.get().size(); i++) {
			data = data + ";"
					+ parseResultSetRecord(result.get().get(i).toString());
		}

		return data.startsWith(";") ? data.substring(1) : data;
	}

	private String parseResultSetRecord(String record) {
		String item = record.substring(1, record.length() - 1);
		return item.split(",")[0] + "(" + item.split(",")[1] + ")";
	}

	private String createSql(String sType, String curValue) {
		if (sType.equalsIgnoreCase("M")) {
			// System.out.println("select mname,mcname from metadata where mname
			// like '"+ curValue +"%'");
			return "select mname,mcname from metadata where upper(mname)  like upper('"
					+ curValue + "')||'%' order by mname asc";
		} else if (sType.equalsIgnoreCase("E")) {
			// System.out.println("select eid,ename from msgelement a where
			// upper(a.eid) like upper('"+curValue +"')||'%'");
			return "select eid,ename from msgelement a where upper(a.eid) like upper('"
					+ curValue + "')||'%' order by a.eid asc";
		} else {
			return null;
		}

	}
}
