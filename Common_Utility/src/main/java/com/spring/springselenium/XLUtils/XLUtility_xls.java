package com.spring.springselenium.XLUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

import com.spring.springselenium.Configuraion.annotation.Page;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.fail;

@Page
public class XLUtility_xls {
  private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumUtils.class);
  private static final String SPINNER_XPATH = "//app-block-ui/div/p-blockui";
  public static final String ERROR_MSG = "Some error has occurred while performing operation::{}";
  public FileInputStream fi;
  public FileOutputStream fo;
  public HSSFWorkbook workbook;
  public HSSFSheet sheet;
  public HSSFRow row;
  public HSSFCell cell;
  public CellStyle style;
  String path;
  private Map<String, Integer>  columns = new HashMap<>();

  public XLUtility_xls() {
  }

  public XLUtility_xls(String path) {
    this.path = path;
  }

  public int getRowCount(String sheetName) throws IOException {
    fi = new FileInputStream(path);
    workbook = new HSSFWorkbook(fi);
    sheet = workbook.getSheet(sheetName);
    int rowcount = sheet.getLastRowNum();
    workbook.close();
    fi.close();
    return rowcount;
  }


  public int getCellCount(String sheetName, int rownum) throws IOException {
    fi = new FileInputStream(path);
    workbook = new HSSFWorkbook(fi);
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(rownum);
    int cellcount = row.getLastCellNum();
    workbook.close();
    fi.close();
    return cellcount;
  }

  public Map<String, Integer> getColumns(String ExcelPath, String SheetName) throws IOException {
    File f = new File(ExcelPath);
    if (!f.exists()) {
      f.createNewFile();
      System.out.println("File doesn't exist, so created!");
    }
    fi = new FileInputStream(ExcelPath);
    workbook = new HSSFWorkbook(fi);
    sheet = workbook.getSheet(SheetName);
    if (sheet == null) {
      sheet = workbook.createSheet(SheetName);
    }
    // adding all the column header names to the map 'columns'
    sheet.getRow(0).forEach(cell -> {
      columns.put(cell.getStringCellValue(), cell.getColumnIndex());
    });
    return columns;
  }

  public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
    fi = new FileInputStream(path);
    workbook = new HSSFWorkbook(fi);
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(rownum);
    cell = row.getCell(colnum);

    DataFormatter formatter = new DataFormatter();
    String data;
    try {
      data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String
                                              // regardless of the cell type.
    } catch (Exception e) {
      data = "";
    }
    workbook.close();
    fi.close();
    return data;
  }

  public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
    File xlfile = new File(path);
    if (!xlfile.exists()) // If file not exists then create new file
    {
      workbook = new HSSFWorkbook();
      fo = new FileOutputStream(path);
      workbook.write(fo);
    }
    fi = new FileInputStream(path);
    workbook = new HSSFWorkbook(fi);
    if (workbook.getSheetIndex(sheetName) == -1) {
      workbook.createSheet(sheetName);
    }
    sheet = workbook.getSheet(sheetName);
    if (sheet.getRow(rownum) == null) {
      sheet.createRow(rownum);
    }
    row = sheet.getRow(rownum);
    cell = row.createCell(colnum);
    cell.setCellValue(data);
    fo = new FileOutputStream(path);
    workbook.write(fo);
    workbook.close();
    fi.close();
    fo.close();
  }


  public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
    fi = new FileInputStream(path);
    workbook = new HSSFWorkbook(fi);
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(rownum);
    cell = row.getCell(colnum);
    style = workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cell.setCellStyle(style);
    workbook.write(fo);
    workbook.close();
    fi.close();
    fo.close();
  }


  public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
    fi = new FileInputStream(path);
    workbook = new HSSFWorkbook(fi);
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(rownum);
    cell = row.getCell(colnum);
    style = workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.RED.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cell.setCellStyle(style);
    workbook.write(fo);
    workbook.close();
    fi.close();
    fo.close();
  }


  /**
   * -------------------------------Excel Reader--------------------------------------
   *
   * @throws IOException
   */
  public XSSFSheet excelRead(String fileName, int sheetNum) throws IOException {
    FileInputStream fis = new FileInputStream(fileName);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheetAt(sheetNum);
    fis.close();
    return sheet;
  }

  /**
   * -------------------------------Excel Row Count--------------------------------------
   */
  public int getRowCnt(XSSFWorkbook workbook, String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1) {
      return 0;
    } else {
      XSSFSheet sheet = workbook.getSheetAt(index);
      return sheet.getLastRowNum() + 1;
    }
  }

  /**
   * -------------------------------Excel Getting one cell data
   * --------------------------------------
   */
  public XSSFCell getCellData(XSSFSheet sheet, int colNum, int rowNum) {
    XSSFRow row = sheet.getRow(rowNum - 1);
    XSSFCell cell = row.getCell(colNum);
    return cell;
  }

  /*
   * -------------------------------Excel formatting cell data based on value
   * --------------------------------------
   */
  public static Object getCellValue(Cell cell) {
    if (cell.getCellType() == CellType.STRING) {
      return cell.getStringCellValue();
    } else if (cell.getCellType() == CellType.BOOLEAN) {
      return cell.getBooleanCellValue();
    } else if (cell.getCellType() == CellType.NUMERIC) {
      return cell.getNumericCellValue();
    } else if (cell.getCellType() == CellType.FORMULA) {
      return String.valueOf(cell.getNumericCellValue());
    } else {
      return null;
    }

  }

  /**
   * -------------------------------Excel entire column data of one row
   * --------------------------------------
   */
  public List getColumnData(XSSFSheet sheet, int row) {
    List colDataList = new ArrayList<>();
    for (int i = 1; i <= sheet.getLastRowNum() + 1; i++) {
      XSSFCell cellVal = getCellData(sheet, row, i);
      colDataList.add(getCellValue(cellVal));
    }
    return colDataList;
  }

  public static List<String> getCSVColumnData(String filePath, int colNum) {
    try {
      Reader reader;
      reader = Files.newBufferedReader(Paths.get(filePath));
      CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
      List<String> colValList = new ArrayList<>();

      for (CSVRecord csvr : csvParser) {
        colValList.add(csvr.get(colNum));
      }
      csvParser.close();
      return colValList;
    } catch (IOException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }

    return null;
  }

  public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws InvalidFormatException, IOException {
    Sheet sheet = WorkbookFactory.create(new File(excelFilePath)).getSheet(sheetName);
    Row row;
    int totalRow = sheet.getPhysicalNumberOfRows();
    List<Map<String, String>> excelRows = new ArrayList<>();
    int totalColumn = sheet.getRow(0).getLastCellNum();
    int setCurrentRow = 1;
    for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
      row = sheet.getRow(sheet.getFirstRowNum() + currentRow);
      LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
      for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
        columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
      }
      excelRows.add(columnMapdata);
    }
    return excelRows;
  }

  private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
    LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
    Cell cell;
    if (row == null) {
      if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
        String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();
        columnMapdata.put(columnHeaderName, "");
      }
    } else {
      cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
      if (cell.getCellType() == CellType.STRING) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, cell.getStringCellValue());
        }
      } else if (cell.getCellType() == CellType.NUMERIC) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
        }
      } else if (cell.getCellType() == CellType.BLANK) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, "");
        }
      } else if (cell.getCellType() == CellType.BOOLEAN) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
        }
      } else if (cell.getCellType() == CellType.ERROR) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
        }
      }
    }
    return columnMapdata;
  }
}
