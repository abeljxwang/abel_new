/*
 * �������� 2006-12-11
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.soamodel;

// import com.ibm.ais.base.message.MsgObject;

import java.util.*;
import java.io.*;

import javax.servlet.http.HttpServletRequest;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import org.abel.service.base.common.*;
import org.abel.service.soapengine.core.ModelBase;

import java.io.FilenameFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author administrator
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class FileList extends ModelBase {

	public EIPResultSet execute(OperationCfg opcfg, HttpServletRequest req) {

		EIPResultSet eresult = new EIPResultSet();
		// File Location;
		String file_location = "C:\\temp";
		if (opcfg.getParameter("filelocation") != null)
			file_location = opcfg.getParameter("filelocation");
		System.out.println(file_location + "------------");
		File rootDir = new File(file_location);

		FilenameFilter fileFilter = new SuffixFileFilter(".xls");
		File[] txtFiles = rootDir.listFiles(fileFilter);
		// System.out.println( ArrayUtils.toString( txtFiles ) );
		
		
		
		for (int i = 0; i < txtFiles.length; i++) {
			Vector rec = new Vector();
			rec.add(i+"");
			rec.add(txtFiles[i].getName());
			rec.add(txtFiles[i].length()/1024+"k");
			java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
 
			Date dd=new Date(txtFiles[i].lastModified());
			rec.add(sdf.format(dd));
			eresult.put(i, rec);

		}
		// eresult.put(sn, vrecord)
		return eresult;

	}

	public static void main(String[] aa) {

		String file_location = "C:\\temp";
		File rootDir = new File(file_location);
		FilenameFilter fileFilter = new SuffixFileFilter(".xls");
		String[] txtFiles = rootDir.list(fileFilter);
		System.out.println(ArrayUtils.toString(txtFiles));

	}

}
