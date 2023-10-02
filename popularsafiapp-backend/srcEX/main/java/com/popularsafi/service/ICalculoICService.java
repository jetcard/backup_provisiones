package com.popularsafi.service;

import com.popularsafi.model.CalculoIC;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICalculoICService extends ICRUD<CalculoIC, Long>{
    String ejecutarFecha(String fechaParam);

    //public ResponseEntity<ProvisionResponseRest> imprimirReporteFecha();
    List<CalculoIC> findAll();
}
