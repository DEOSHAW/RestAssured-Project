package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import jxl.read.biff.BiffException;

public class Utilities {
	
	
	@DataProvider(name="DataSupplierWithPoi")
	public Object[][] GetDataForLogin() throws BiffException, IOException
	{
		
		
		int i,j;
		int row=0;
		int col=0;
       

        //HashMap<String[],Object[][]> datamap=new HashMap<String[],Object[][]>();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+File.separator+"Payload.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sh=wb.getSheetAt(0);
        row=sh.getLastRowNum();
        row=row+1;
        col=sh.getRow(0).getLastCellNum();
        
        Object[][] data=new Object[row][col];
        Object[][] data1=new Object[row-1][col];
       System.out.println("Values are: ");
        for(i=0;i<row;i++) {
            for (j = 0; j < col; j++) {
            	try
            	{
                data[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
            	}
            	catch(IllegalStateException e)
            	{
            		data[i][j] = String.valueOf(sh.getRow(i).getCell(j).getNumericCellValue());
            	}
                System.out.println(data[i][j]);
            }
        }
        for(i=0;i<row-1;i++)
            for(j=0;j<col;j++)
                data1[i][j]=data[i+1][j];

        for(i=0;i<row-1;i++)
            for(j=0;j<col;j++)
                System.out.println(data1[i][j]);

        //return data1;
        return data1;


		
	}
	
	
	

}
