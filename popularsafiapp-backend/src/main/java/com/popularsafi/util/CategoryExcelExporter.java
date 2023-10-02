package com.popularsafi.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.popularsafi.model.ReporteProvision;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
    private List<ReporteProvision> reporteProvision;


    public CategoryExcelExporter (List<ReporteProvision> listaProvisions) {
        this.reporteProvision = listaProvisions;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(String fechaProceso) {
        sheet = workbook.createSheet("Reporte de códigos de cuotas generadas al "+fechaProceso);
        sheet.setDisplayGridlines(false);

        CellRangeAddress mergedRegion0 = new CellRangeAddress(0,0,1,12);
        sheet.addMergedRegion(mergedRegion0);
        CellRangeAddress mergedRegion1 = new CellRangeAddress(2,3,1,1);
        sheet.addMergedRegion(mergedRegion1);


        //sheet.setColumnWidth(1, 75 * 256);


        CellRangeAddress mergedRegion2 = new CellRangeAddress(2,3,2,2);
        sheet.addMergedRegion(mergedRegion2);
        CellRangeAddress mergedRegion3 = new CellRangeAddress(2,3,3,3);
        sheet.addMergedRegion(mergedRegion3);
        CellRangeAddress mergedRegion4 = new CellRangeAddress(2,3,4,4);
        sheet.addMergedRegion(mergedRegion4);
        CellRangeAddress mergedRegion5 = new CellRangeAddress(2,3,5,5);
        sheet.addMergedRegion(mergedRegion5);
        CellRangeAddress mergedRegion6 = new CellRangeAddress(2,3,6,6);
        sheet.addMergedRegion(mergedRegion6);
        CellRangeAddress mergedRegion7 = new CellRangeAddress(2,3,7,7);
        sheet.addMergedRegion(mergedRegion7);
        CellRangeAddress mergedRegion8 = new CellRangeAddress(2,3,8,8);
        sheet.addMergedRegion(mergedRegion8);
        CellRangeAddress mergedRegion9 = new CellRangeAddress(2,3,9,9);
        sheet.addMergedRegion(mergedRegion9);
        CellRangeAddress mergedRegion10 = new CellRangeAddress(2,2,11,12);
        sheet.addMergedRegion(mergedRegion10);
        CellRangeAddress mergedRegion11 = new CellRangeAddress(2,3,13,13);
        sheet.addMergedRegion(mergedRegion11);

        Row row0 = sheet.createRow(0);
        CellStyle styleTop = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        styleTop.setFont(font);
        styleTop.setAlignment(HorizontalAlignment.CENTER);
        createCell(row0, 1, "Reporte de códigos de cuotas generadas al "+fechaProceso, styleTop);

        Row row = sheet.createRow(2);

        CellStyle styleHeaders = workbook.createCellStyle();
        styleHeaders.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeaders.setAlignment(HorizontalAlignment.CENTER);
        styleHeaders.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeaders.setFont(font);

        CellStyle styleHeadersCuotasIC = workbook.createCellStyle();
        styleHeadersCuotasIC.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeadersCuotasIC.setAlignment(HorizontalAlignment.CENTER);
        styleHeadersCuotasIC.setBorderTop(BorderStyle.THIN);
        styleHeadersCuotasIC.setBorderBottom(BorderStyle.THIN);
        styleHeadersCuotasIC.setBorderLeft(BorderStyle.THIN);
        styleHeadersCuotasIC.setBorderRight(BorderStyle.THIN);
        styleHeadersCuotasIC.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeadersCuotasIC.setFont(font);

        CellStyle styleHeaderYellow = workbook.createCellStyle();
        styleHeaderYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        styleHeaderYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleHeaderYellow.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeaderYellow.setAlignment(HorizontalAlignment.CENTER);
        styleHeaderYellow.setBorderTop(BorderStyle.THIN);
        styleHeaderYellow.setBorderBottom(BorderStyle.THIN);
        styleHeaderYellow.setBorderLeft(BorderStyle.THIN);
        styleHeaderYellow.setBorderRight(BorderStyle.THIN);
        styleHeaderYellow.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(11);
        styleHeaderYellow.setFont(font);

        createCell(row, 1, "N°", styleHeaders);
        createCell(row, 2, "CÓDIGO", styleHeaders);
        createCell(row, 3, "Moneda", styleHeaders);
        createCell(row, 4, "N° CUOTA", styleHeaders);
        createCell(row, 5, "FECHA DESEMBOLSO", styleHeaders);
        createCell(row, 6, "FECHA DE PAGO", styleHeaders);
        createCell(row, 7, "CAPITAL ADEUDADO CRONOGRAMA", styleHeaders);
        createCell(row, 8, "TEA", styleHeaders);
        createCell(row, 9, "DÍAS TRANSC AL CIERRE", styleHeaders);
        createCell(row, 10, "CUOTA", styleHeadersCuotasIC);
        createCell(row, 11, "PROVISIÓN", styleHeaders);
        createCell(row, 13, "TOTAL", styleHeaders);

        Row row3 = sheet.createRow(3);
        createCell(row3, 10, "INTERÉS COMPENSATORIO", styleHeadersCuotasIC);
        createCell(row3, 11, "INTERÉS PROVISIÓN", styleHeaderYellow);
        createCell(row3, 12, "IGV", styleHeaders);

        setBordersToMergedCells(sheet, mergedRegion1);
        setBordersToMergedCells(sheet, mergedRegion2);

        setBordersToMergedCells(sheet, mergedRegion3);
        setBordersToMergedCells(sheet, mergedRegion4);
        setBordersToMergedCells(sheet, mergedRegion5);
        setBordersToMergedCells(sheet, mergedRegion6);
        setBordersToMergedCells(sheet, mergedRegion7);
        setBordersToMergedCells(sheet, mergedRegion8);
        setBordersToMergedCells(sheet, mergedRegion9);
        setBordersToMergedCells(sheet, mergedRegion10);
        setBordersToMergedCells(sheet, mergedRegion11);

        sheet.setColumnWidth(0, PixelUtil.pixel2WidthUnits(67) );
        sheet.setColumnWidth(1, PixelUtil.pixel2WidthUnits(62) );//169*77%
        sheet.setColumnWidth(2, PixelUtil.pixel2WidthUnits(88) );//283
        sheet.setColumnWidth(3, PixelUtil.pixel2WidthUnits(88) );//397
        sheet.setColumnWidth(4, PixelUtil.pixel2WidthUnits(66) );//483
        sheet.setColumnWidth(5, PixelUtil.pixel2WidthUnits(95) );//603 //92
        sheet.setColumnWidth(6, PixelUtil.pixel2WidthUnits(103) );//735
        sheet.setColumnWidth(7, PixelUtil.pixel2WidthUnits(103) );//867
        sheet.setColumnWidth(8, PixelUtil.pixel2WidthUnits(52) );//934
        sheet.setColumnWidth(9, PixelUtil.pixel2WidthUnits(151) );//1126 //148
        sheet.setColumnWidth(10, PixelUtil.pixel2WidthUnits(115) );//1275
        sheet.setColumnWidth(11, PixelUtil.pixel2WidthUnits(117) );//1425
        sheet.setColumnWidth(12, PixelUtil.pixel2WidthUnits(102) );//1556
        sheet.setColumnWidth(13, PixelUtil.pixel2WidthUnits(71) );//1648
/*
        sheet.setColumnWidth(0, PixelUtil.pixel2WidthUnits(88) );
        sheet.setColumnWidth(1, PixelUtil.pixel2WidthUnits(81) );//169
        sheet.setColumnWidth(2, PixelUtil.pixel2WidthUnits(114) );//283
        sheet.setColumnWidth(3, PixelUtil.pixel2WidthUnits(114) );//397
        sheet.setColumnWidth(4, PixelUtil.pixel2WidthUnits(86) );//483
        sheet.setColumnWidth(5, PixelUtil.pixel2WidthUnits(120) );//603
        sheet.setColumnWidth(6, PixelUtil.pixel2WidthUnits(132) );//735
        sheet.setColumnWidth(7, PixelUtil.pixel2WidthUnits(100) );
        sheet.setColumnWidth(8, PixelUtil.pixel2WidthUnits(100) );
        sheet.setColumnWidth(9, PixelUtil.pixel2WidthUnits(100) );
*/

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        //sheet.autoSizeColumn(columnCount);

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

        CellStyle styleLeft = workbook.createCellStyle();
        styleLeft.setBorderTop(BorderStyle.THIN);
        styleLeft.setBorderBottom(BorderStyle.THIN);
        styleLeft.setBorderLeft(BorderStyle.THIN);
        styleLeft.setBorderRight(BorderStyle.THIN);
        styleLeft.setAlignment(HorizontalAlignment.LEFT);

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
        for( ReporteProvision result: reporteProvision) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 1;
            createCell(row, columnCount++, ++contadorRegistros, style);
            createCell(row, columnCount++, result.getDVALOR_BV(), styleLeft);
            createCell(row, columnCount++, result.getCMONEDA(), styleLeft);
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


    public void export(HttpServletResponse response, String fechaExcel) throws IOException {

        writeHeaderLine(fechaExcel); //write the header
        writeDataLines(); //write the data

        ServletOutputStream servletOutput = response.getOutputStream();
        workbook.write(servletOutput);
        workbook.close();
        servletOutput.close();
    }

    protected void setBordersToMergedCells(Sheet sheet, CellRangeAddress rangeAddress) {
        RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
    }
}
