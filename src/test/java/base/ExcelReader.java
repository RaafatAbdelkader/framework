package base;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    static String f_path=PropReader.getProp("ExcelFilePath").toString();
    public static String getCellValue( String sheetName,int row,int col){
        XSSFWorkbook xssfWorkbook = null;
        String value=null;
        try {
            FileInputStream fileInputStream=new FileInputStream(new File(f_path));
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet =xssfWorkbook.getSheet(sheetName);
            Cell cell =sheet.getRow(row-1).getCell(col-1);
            if (cell.getCellType()==CellType.STRING)
                value=cell.getStringCellValue();
            else if (cell.getCellType()== CellType.NUMERIC)
                value=NumberToTextConverter.toText(cell.getNumericCellValue());
            else if (cell.getCellType()==CellType.BLANK)
                value="";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
//
//    public  Map<String,String> getRegisterTestData(String username)  {
//        Map<String,String>data=new HashMap<>();
//        FileInputStream fis= null;
//        XSSFWorkbook workbook=null;
//        try {
//            fis = new FileInputStream(file);
//            workbook= new XSSFWorkbook(fis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        XSSFSheet sheet = workbook.getSheet(sheetName);
//
//        Row headerRow=sheet.getRow(sheet.getFirstRowNum());
//        Iterator<Cell>headers=headerRow.cellIterator();
//        while (headers.hasNext()){
//            String headerValue= headers.next().getStringCellValue();
//            data.put(headerValue,null);
//        }
//        boolean userFound= false;
//        Iterator<Row> rows=sheet.rowIterator();
//        while (rows.hasNext()){
//            Row row=rows.next();
//            String firstCellValue=row.getCell(row.getFirstCellNum()).getStringCellValue();
//            if (firstCellValue.contains(username)){
//                userFound=true;
//                Iterator<Cell> cells=row.cellIterator();
//                while (cells.hasNext()){
//                    Cell cell= cells.next();
//                    String  cellValue =null;
//                    if (cell.getCellType()== CellType.STRING){
//                        cellValue= cell.getStringCellValue();
//                    }else if (cell.getCellType()== CellType.NUMERIC){
//                        cellValue= NumberToTextConverter.toText(cell.getNumericCellValue());
//                    }
//                    else {
//                        cellValue=null;
//                    }
//                    String relatedHeaderCellValue=headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
//                    data.replace(relatedHeaderCellValue,null,cellValue);
//                }
//            }
//        }
//        if (!userFound){
//            System.out.println("Username "+username+" not found");
//            return null;
//        }else{
//            return data;
//        }
//
//    }
//
//
//    public Object[][] getInvalidPersonalData() throws IOException {
//        sheetName="invalidPersonalData";
//        FileInputStream fis=new FileInputStream(file);
//        XSSFWorkbook workbook =new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet(sheetName);
//        int rowsNum=sheet.getLastRowNum();
//        int columnNum=sheet.getRow(sheet.getFirstRowNum()).getLastCellNum()-1;
//        Object[][] data= new Object[rowsNum][columnNum];
//        for (int i = 0;i<rowsNum; i++) {
//            Row row= sheet.getRow(i+1);                                              //+1 to skip the first row
//            for (int j = 0; j <columnNum; j++) {
//                Cell cell= row.getCell(j+1);                                        //+1 to skip the first column
//                if (cell.getCellType()==CellType.BLANK)
//                    data[i][j]="";
//                else if (cell.getCellType()==CellType.STRING)
//                    data[i][j]=cell.getStringCellValue();
//                else if (cell.getCellType()== CellType.NUMERIC)
//                    data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
//                else
//                    data[i][j]=null;
//            }
//        }
//        return data;
//    }
}