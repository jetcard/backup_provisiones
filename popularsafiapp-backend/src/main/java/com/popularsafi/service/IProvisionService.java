package com.popularsafi.service;

import com.popularsafi.model.ReporteProvision;

import java.util.List;

public interface IProvisionService extends ICRUD<ReporteProvision, Long>{
    String ejecutarFecha(String fechaParam);
    List<ReporteProvision> findAll();
}
