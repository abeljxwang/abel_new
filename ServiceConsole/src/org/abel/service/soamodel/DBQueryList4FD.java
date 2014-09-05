/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.soamodel;

// import com.ibm.ais.base.message.MsgObject;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import org.abel.service.base.common.*;
import org.abel.service.soapengine.core.RRModelBase;
import org.abel.service.soapengine.core.ServiceMessage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.sql.*;

/**
 * @author administrator
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DBQueryList4FD extends RRModelBase {

	public EIPResultSet execute(OperationCfg opcfg, HttpServletRequest req,
			HttpServletResponse res) {

		EIPResultSet eresult1 = null;
		try {
			//String opname = opcfg.getPropertie("Name");
			Vector ERROR_INFO = new Vector(); // null;
			String sql = null;
			String sqlbase = null, sqlfield = null;

			ParameterParser ps = new ParameterParser();
			Properties ppkv = new Properties();
			// sqlbase
			if (opcfg.getParameter("sqlbase") != null)
				sqlbase = opcfg.getParameter("sqlbase");

			// sqlfield
			Vector fieldv = new Vector();
			if (opcfg.getParameter("sqlfield") != null) {
				sqlfield = opcfg.getParameter("sqlfield");
				fieldv = ps.getparameters(sqlfield);
				String tmp1 = null;
				for (int k = 0; k < fieldv.size(); k++) {
					tmp1 = fieldv.elementAt(k) + "";
					if (req.getParameter(tmp1) != null
							&& req.getParameter(tmp1).trim().length() > 0)
						ppkv.setProperty(tmp1, req.getParameter(tmp1));
				}

			}

			FileInputStream fileIn = null;
			String fpath=req.getAttribute("fpath")+"";
			fileIn = new FileInputStream(fpath);
			// sqlfield
			Vector resultkeyv = new Vector();
			if (opcfg.getParameter("responsekey") != null) {
				sqlfield = opcfg.getParameter("responsekey");
				resultkeyv = ps.getparameters(sqlfield);
			}

			String listkey = "listinfo";
			if (opcfg.getParameter("listkey") != null) {
				listkey = opcfg.getParameter("listkey");
			}

			sql = sqlbase;
			sql = ps.complete(sql, ppkv);
			DBHandler dbh = new DBHandler();

			ServletOutputStream servletOS = res.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(fileIn));

			HSSFSheet sheet = wb.getSheetAt(0);
			
			HSSFCell cell;
			HSSFCell cell1;
			// for(short cols=1;cols<9;cols++){
			int rows = 7;
			HSSFRow row;
			HSSFRow row1;
			ResultSet eresult=null;
			try {
				eresult= dbh.stdquery(sql);
				ResultSetMetaData rsmd=eresult.getMetaData();
				while (eresult.next()) {
					if(rows==8)
						sheet.getRow(2).getCell((short)2).setCellValue(new HSSFRichTextString(eresult.getString(1)+" 响应码/错误码定义"));

					row = sheet.getRow(rows);
					if (row == null)						row = sheet.createRow(rows);
					if(rows<=8) 
						row1 = sheet.getRow(rows);
					
					else {

						row1 = sheet.getRow(rows-1);
						}

					//row.
					for (short cols = 1; cols < rsmd.getColumnCount(); cols++) {
						cell = row.getCell(cols);
						cell1=row1.getCell(cols);
						if (cell == null) cell = row.createCell(cols);
						cell.setCellStyle(cell1.getCellStyle());
						prepareCellContent(cell, eresult.getString(cols));
						
					}
					//prepareCellContent(cell, eresult.getString(cols));
					rows++;
				}
				eresult.close();
				eresult=null;
				dbh.close();

				wb.write(servletOS);

				dbh = null;

			} catch (Exception se) {
				se.printStackTrace();
				ERROR_INFO.add("数据库查询操作处理错误");

			} finally {
				if (dbh != null)
					dbh.close();
				fileIn.close();
				servletOS.flush();
				servletOS.close();
				wb=null;
				if(eresult!=null) eresult.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return eresult1;

	}

	void prepareResult(ServiceMessage mo, EIPResultSet eresult) {

	}

	void prepareCellContent(HSSFCell cell, String value) {
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(value));
	}

}
