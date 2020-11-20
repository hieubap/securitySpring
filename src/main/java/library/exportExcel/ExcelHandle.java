package library.exportExcel;

import library.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandle {
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void excelTest() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");

        List<Student> list = new ArrayList<>();
        list.add(new Student((long)1,"hieu","2018"));
        list.add(new Student((long)2,"hieu","2018"));
        list.add(new Student((long)3,"hieu","2018"));


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Name");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("mssv");
        cell.setCellStyle(style);

        // Data
        for (Student emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(emp.getId());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getName());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getMssv());
        }
        File file = new File("D:/exportExcel.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }
}
