package com.popularsafi.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.popularsafi.model.CalculoIC;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CalculoIC> calculoIC;


    public CategoryExcelExporter (List<CalculoIC> listaCalculos) {
        this.calculoIC = listaCalculos;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Reporte de códigos de cuotas generadas al  ");

        CellRangeAddress mergedRegion = new CellRangeAddress(0,0,1,12);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,1,1);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,2,2);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,3,3);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,4,4);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,5,5);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,6,6);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,7,7);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,8,8);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,9,9);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,2,11,12);
        sheet.addMergedRegion(mergedRegion);
        mergedRegion = new CellRangeAddress(2,3,13,13);
        sheet.addMergedRegion(mergedRegion);

        Row row0 = sheet.createRow(0);
        CellStyle styleTop = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        styleTop.setFont(font);
        styleTop.setAlignment(HorizontalAlignment.CENTER);
        createCell(row0, 1, "Reporte de códigos de cuotas generadas al  31/07/2023", styleTop);

        Row row = sheet.createRow(2);

        CellStyle styleHeaders = workbook.createCellStyle();
        styleHeaders.setBorderTop(BorderStyle.THIN);
        styleHeaders.setBorderBottom(BorderStyle.THIN);
        styleHeaders.setBorderLeft(BorderStyle.THIN);
        styleHeaders.setBorderRight(BorderStyle.THIN);
        styleHeaders.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeaders.setAlignment(HorizontalAlignment.CENTER);
        styleHeaders.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeaders.setFont(font);

        CellStyle styleHeaderIdx = workbook.createCellStyle();
        styleHeaderIdx.setBorderTop(BorderStyle.THIN);
        styleHeaderIdx.setBorderBottom(BorderStyle.THIN);
        styleHeaderIdx.setBorderLeft(BorderStyle.THIN);
        styleHeaderIdx.setBorderRight(BorderStyle.THIN);
        styleHeaderIdx.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeaderIdx.setAlignment(HorizontalAlignment.CENTER);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeaderIdx.setFont(font);

        CellStyle styleHeaderYellow = workbook.createCellStyle();
        styleHeaderYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        styleHeaderYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleHeaderYellow.setAlignment(HorizontalAlignment.RIGHT);
        styleHeaderYellow.setBorderTop(BorderStyle.THIN);
        styleHeaderYellow.setBorderBottom(BorderStyle.THIN);
        styleHeaderYellow.setBorderLeft(BorderStyle.THIN);
        styleHeaderYellow.setBorderRight(BorderStyle.THIN);
        styleHeaderYellow.setAlignment(HorizontalAlignment.CENTER);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeaderYellow.setFont(font);

        createCell(row, 1, "N°", styleHeaderIdx);
        createCell(row, 2, "CÓDIGO", styleHeaders);
        createCell(row, 3, "Moneda", styleHeaders);
        createCell(row, 4, "N° CUOTA", styleHeaders);
        createCell(row, 5, "FECHA DESEMBOLSO", styleHeaders);
        createCell(row, 6, "FECHA DE PAGO", styleHeaders);
        createCell(row, 7, "CAPITAL ADEUDADO CRONOGRAMA", styleHeaders);
        createCell(row, 8, "TEA", styleHeaders);
        createCell(row, 9, "DÍAS TRANSC AL CIERRE", styleHeaders);
        createCell(row, 10, "CUOTA", styleHeaders);
        createCell(row, 11, "PROVISIÓN", styleHeaders);
        createCell(row, 13, "TOTAL", styleHeaders);

        Row row3 = sheet.createRow(3);
        createCell(row3, 10, "INTERÉS COMPENSATORIO", styleHeaders);
        createCell(row3, 11, "INTERÉS PROVISIÓN", styleHeaderYellow);
        createCell(row3, 12, "IGV", styleHeaders);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if(value instanceof Date) {
            cell.setCellValue(new Date());
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);

    }


    private void writeDataLines() {

        int rowCount = 4;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.RIGHT);
        /*CellRangeAddress region = new CellRangeAddress(7, 8, 1, 5);
        RegionUtil.setTopBorderColor(IndexedColors.RED.index, region, sheet);
        RegionUtil.setBottomBorderColor(IndexedColors.GREEN.index, region, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLUE.index, region, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.VIOLET.index, region, sheet);*/

        CellStyle styleDate = workbook.createCellStyle();
        XSSFCreationHelper createHelper = workbook.getCreationHelper();
        styleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
        styleDate.setBorderTop(BorderStyle.THIN);
        styleDate.setBorderBottom(BorderStyle.THIN);
        styleDate.setBorderLeft(BorderStyle.THIN);
        styleDate.setBorderRight(BorderStyle.THIN);
        styleDate.setAlignment(HorizontalAlignment.CENTER);

        CellStyle styleYellow = workbook.createCellStyle();
        styleYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        styleYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleYellow.setAlignment(HorizontalAlignment.RIGHT);
        styleYellow.setBorderTop(BorderStyle.THIN);
        styleYellow.setBorderBottom(BorderStyle.THIN);
        styleYellow.setBorderLeft(BorderStyle.THIN);
        styleYellow.setBorderRight(BorderStyle.THIN);
        styleYellow.setAlignment(HorizontalAlignment.RIGHT);

        int contadorRegistros=0;
        for( CalculoIC result: calculoIC) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 1;
            createCell(row, columnCount++, ++contadorRegistros, style);
            createCell(row, columnCount++, result.getDVALOR_BV(), style);
            createCell(row, columnCount++, "S/.", style);
            createCell(row, columnCount++, result.getNCUOTA(), style);
            createCell(row, columnCount++, result.getFDESEMBOLSO(), styleDate);
            createCell(row, columnCount++, result.getFPAGO(), styleDate);
            createCell(row, columnCount++, result.getMONTO(), style);
            createCell(row, columnCount++, result.getTEA(), style);
            createCell(row, columnCount++, result.getDIASTRANS(), style);
            createCell(row, columnCount++, result.getINTERES(), style);
            createCell(row, columnCount++, result.getINTERESPROV(), styleYellow);
            createCell(row, columnCount++, result.getIGV(), style);
            createCell(row, columnCount++, result.getTOTAL(), style);
        }
    }


    public void export(HttpServletResponse response) throws IOException {

        writeHeaderLine(); //write the header
        writeDataLines(); //write the data

        ServletOutputStream servletOutput = response.getOutputStream();
        workbook.write(servletOutput);
        workbook.close();

        servletOutput.close();


    }
}
