package com.ibm.ais.soa.xls;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;

/**
 * Servlet implementation class for Servlet: FileDownLoad
 *
 */
 public class FileDownLoad extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FileDownLoad() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		prepareFile(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		prepareFile(request, response);
	}   
	
	protected void prepareFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		byte[] buf = new byte[4];
		File serverF = new File("");
		
		//response.setHeader("Content-length", ""+(int)serverF.length());
		
		
		FileInputStream fileIn = null;
        FileOutputStream fileOut = null;

        try
        {
        	File f=new File("a.txt");
        	System.out.print(getServletContext().getRealPath("/")+"--------------------------");
        	
        	fileIn = new FileInputStream("c:\\Development\\template.xls");
        	
        	response.setContentType("application/xls");
    		response.setHeader("Content-disposition","attachment; filename=\"ErrorCode.xls\"");
    		response.setHeader( "Pragma", "public" );
    		response.setHeader("Cache-control", "must-revalidate");
        	
        	
        	HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(fileIn));
        	
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(7);
            
            if (row == null) row = sheet.createRow(7);
            
            HSSFCell cell;
            for(short cols=1;cols<9;cols++){
            	cell = row.getCell(cols);
                if (cell == null) cell = row.createCell(cols);
                prepareCellContent(cell,"EAIH"+cols);
            	
            }
 
            // Write the output to a file
            //fileOut = new FileOutputStream("c:\\Development\\workbookout2.xls");
            wb.write(response.getOutputStream());
        }
        catch(Exception e){
        	
        	e.printStackTrace();
        }
        finally
        {
            if (fileOut != null)
                fileOut.close();
            if (fileIn != null)
                fileIn.close();
        }
		
		
		
	}

	   

        
   
    
    void prepareCellContent(HSSFCell cell,String value){
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString(value));   	
    }
	
}