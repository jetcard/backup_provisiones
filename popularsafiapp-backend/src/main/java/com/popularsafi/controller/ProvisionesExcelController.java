package com.popularsafi.controller;

import com.popularsafi.model.CalculoIC;
import com.popularsafi.service.ICalculoICService;
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
@CrossOrigin(origins = {"http://localhost:4250, https://localhost:4250, https://popularsafi.examensolucion.com, http://34.70.2.42, https://34.70.2.42"})
@RestController
@RequestMapping("/reporte-excel")
@RequiredArgsConstructor
public class ProvisionesExcelController {
    private static final Logger logger= LoggerFactory.getLogger(ProvisionesExcelController.class.getName());
    @Autowired
    private final ICalculoICService serv;
    /**
     * export to excel file
     * @param response
     * @throws IOException
     */
    @GetMapping("/provision/export/excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("fechaExcel") String fechaExcel) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerKey, headerValue);
        ResponseEntity<ProvisionResponseRest> responseEntity = imprimirReporteFecha(fechaExcel);
        CategoryExcelExporter excelExporter = new CategoryExcelExporter(
                responseEntity.getBody().getProvisionResponse().getProvision());
        excelExporter.export(response, LocalDate.parse(fechaExcel, formatter).format(formatter2));
    }

    //@Override
    public ResponseEntity<ProvisionResponseRest> imprimirReporteFecha(String fechaParam) {
        ProvisionResponseRest response = new ProvisionResponseRest();
        try {
            List<CalculoIC> lista  = serv.findAll();
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
