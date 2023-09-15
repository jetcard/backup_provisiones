package com.popularsafi.controller;

import com.popularsafi.dto.CalculoDTO;
import com.popularsafi.model.CalculoIC;
import com.popularsafi.service.ICalculoICService;
import com.popularsafi.service.ProvisionResponseRest;
import com.popularsafi.util.CategoryExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4250, https://localhost:4250, https://popularsafi.examensolucion.com, http://34.70.2.42, https://34.70.2.42"})
@RestController
@RequestMapping("/reporte-calculo")
@RequiredArgsConstructor
public class CalculoController {
    private static final Logger logger= LoggerFactory.getLogger(CalculoController.class.getName());
    @Autowired
    private final ICalculoICService serv;

    @Qualifier("calculoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CalculoDTO>> obtenerCalculoIC(@RequestParam("fechaProceso") String fechaProceso) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("TimeUnitMillis1 "+dateFormat.format(System.currentTimeMillis()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        logger.info(LocalDate.parse(fechaProceso, formatter).format(formatter2));
        serv.ejecutarFecha(LocalDate.parse(fechaProceso, formatter).format(formatter2));
        List<CalculoDTO> lista  = serv.findAll().parallelStream().map(this::convertToDto).collect(Collectors.toList());
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("TimeUnitMillis2 "+dateFormat2.format(System.currentTimeMillis()));
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    private CalculoDTO convertToDto(CalculoIC obj){
        return modelMapper.map(obj, CalculoDTO.class);
    }

    private CalculoIC convertToEntity(CalculoDTO dto){
        return modelMapper.map(dto, CalculoIC.class);
    }


    /*@Autowired
    private ICategoryService service;
*/

    /**
     * get all the categories
     * @return
     */
    /*@GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories() {
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }*/

    /**
     * get categories by id
     * @param id
     * @return
     */
    /*@GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }*/


    /**
     * export to excel file
     * @param response
     * @throws IOException
     */
    @GetMapping("/provision/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response.setHeader(headerKey, headerValue);
        ResponseEntity<ProvisionResponseRest> responseEntity = imprimirReporteFecha("String fechaParam");
        CategoryExcelExporter excelExporter = new CategoryExcelExporter(
                responseEntity.getBody().getProvisionResponse().getProvision());
        excelExporter.export(response);
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