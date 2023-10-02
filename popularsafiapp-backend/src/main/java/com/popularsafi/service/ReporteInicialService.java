package com.popularsafi.service;

import com.popularsafi.model.ReporteProvision;

import java.util.List;

public interface ReporteInicialService extends ICRUD<ReporteProvision, Long>{
    String ejecutarFecha(String fechaParam);

    List<ReporteProvision> findAll();
}
