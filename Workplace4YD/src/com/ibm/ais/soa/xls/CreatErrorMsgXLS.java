package com.ibm.ais.soa.xls;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This example demonstrates opening a workbook, modifying it and writing
 * the results back out.
 *
 * @author Glen Stampoultzis (glens at apache.org)
 */
public class CreatErrorMsgXLS
{
    public static void main(String[] args)  throws IOException{
    	
    	CreatErrorMsgXLS nnn=new CreatErrorMsgXLS();
    	nnn.prepareExcel();
    	
    }
    
    public void prepareExcel()
        throws IOException
    {
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;

        try
        {
        	
        	fileIn = new FileInputStream("c:\\Development\\template.xls");
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
            fileOut = new FileOutputStream("c:\\Development\\workbookout2.xls");
            wb.write(fileOut);
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
