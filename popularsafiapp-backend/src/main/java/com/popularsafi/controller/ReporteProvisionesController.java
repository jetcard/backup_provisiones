package com.popularsafi.controller;

import com.popularsafi.dto.ProvisionDTO;
import com.popularsafi.model.ReporteProvision;
import com.popularsafi.service.IProvisionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/reporte-provisiones")
@RequiredArgsConstructor
public class ReporteProvisionesController {
    private static final Logger logger= LoggerFactory.getLogger(ReporteProvisionesController.class.getName());
    @Autowired
    private final IProvisionService serv;

    @Qualifier("provisionMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProvisionDTO>> obtenerProvisionIC(@RequestParam("fechaProceso") String fechaProceso) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("TimeUnitMillis1 "+dateFormat.format(System.currentTimeMillis()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        logger.info(LocalDate.parse(fechaProceso, formatter).format(formatter2));
        serv.ejecutarFecha(LocalDate.parse(fechaProceso, formatter).format(formatter2));
        List<ProvisionDTO> lista  = serv.findAll().parallelStream().map(this::convertToDto).collect(Collectors.toList());
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("TimeUnitMillis2 "+dateFormat2.format(System.currentTimeMillis()));
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    private ProvisionDTO convertToDto(ReporteProvision obj){
        return modelMapper.map(obj, ProvisionDTO.class);
    }

    private ReporteProvision convertToEntity(ProvisionDTO dto){
        return modelMapper.map(dto, ReporteProvision.class);
    }
}