package com.popularsafi.controller;

import com.popularsafi.model.ReporteProvision;
import com.popularsafi.service.IProvisionService;
import com.popularsafi.service.ProvisionResponseRest;
import com.popularsafi.util.CategoryExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/reporte-provisiones-excel")
@RequiredArgsConstructor
public class ReporteProvisionesExcelController {
    private static final Logger logger= LoggerFactory.getLogger(ReporteProvisionesExcelController.class.getName());
    @Autowired
    private final IProvisionService serv;
    /**
     * export to excel file
     * @param response
     * @throws IOException
     */
    @GetMapping("/exportar-provisiones/excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("fechaProceso") String fechaProceso) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerKey, headerValue);
        ResponseEntity<ProvisionResponseRest> responseEntity = imprimirReporteFecha();
        CategoryExcelExporter excelExporter = new CategoryExcelExporter(
                responseEntity.getBody().getProvisionResponse().getProvision());
        excelExporter.export(response, LocalDate.parse(fechaProceso, formatter).format(formatter2));
    }

    //@Override
    public ResponseEntity<ProvisionResponseRest> imprimirReporteFecha() {
        ProvisionResponseRest response = new ProvisionResponseRest();
        try {
            List<ReporteProvision> lista  = serv.findAll();
            response.getProvisionResponse().setProvision(lista);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<ProvisionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProvisionResponseRest>(response, HttpStatus.OK);
    }

}
