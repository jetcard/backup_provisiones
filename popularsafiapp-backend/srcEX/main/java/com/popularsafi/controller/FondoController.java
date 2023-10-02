package com.popularsafi.controller;

import com.popularsafi.dto.FondoDTO;
import com.popularsafi.model.Fondo;
import com.popularsafi.service.IFondoServ;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fondo")
@RequiredArgsConstructor
public class FondoController {

    @Autowired
    private final IFondoServ serv;

    @Qualifier("fondoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FondoDTO>> obtenerFondo() {
        List<FondoDTO> lstfondo  = serv.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lstfondo,HttpStatus.OK);
    }


    private FondoDTO convertToDto(Fondo obj){
        return modelMapper.map(obj, FondoDTO.class);
    }

    private Fondo convertToEntity(FondoDTO dto){
        return modelMapper.map(dto, Fondo.class);
    }
}
