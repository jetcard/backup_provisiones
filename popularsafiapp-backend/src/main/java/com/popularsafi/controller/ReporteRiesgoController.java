package com.popularsafi.controller;

import com.popularsafi.dto.ReporteRiesgoDTO;
import com.popularsafi.model.ReporteRiesgo;
import com.popularsafi.service.IReporteRiesgoService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/reporte-riesgos")
@RequiredArgsConstructor
public class ReporteRiesgoController {

    private static final Logger logger= LoggerFactory.getLogger(ReporteRiesgoController.class.getName());

    @Autowired
    private final IReporteRiesgoService iService;
    //public IReporteRiesgoService iService;


    @Qualifier("riesgoMapper")
    private final ModelMapper modelMapper;

    @GetMapping (produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReporteRiesgoDTO>> obtenerReporteRiesgo(@RequestParam("fechaProceso") String fechaProceso) {
        List<ReporteRiesgoDTO> ListReporteRiesgo = iService.obtenerReportesPorFecha(fechaProceso).stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(ListReporteRiesgo,HttpStatus.OK);
    }

/*
    @GetMapping(path = "/{pfondo}/{pfecha}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReporteRiesgoDTO>> obtenerReporteRiesgo(@PathVariable("pfondo") String pfondo,@PathVariable("pfecha") String pfecha) {
        // Convertir la fecha a Date
        System.out.println("fecha"+pfecha);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = new Date();
        try {
            fecha = format.parse(pfecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<ReporteRiesgoDTO> ListReporteRiesgo=iService.obtenerReporteRiesgo(pfondo,fecha).stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(ListReporteRiesgo,HttpStatus.OK);
    }
*/
    private ReporteRiesgoDTO convertToDto(ReporteRiesgo obj){
        return modelMapper.map(obj, ReporteRiesgoDTO.class);
    }

    private ReporteRiesgo convertToEntity(ReporteRiesgoDTO dto){
        return modelMapper.map(dto, ReporteRiesgo.class);
    }

}
