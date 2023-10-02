package com.popularsafi.service;

import com.popularsafi.model.ReporteRiesgo;

import java.util.Date;
import java.util.List;

public interface IReporteRiesgoService extends ICRUD<ReporteRiesgo,Integer> {

    List<ReporteRiesgo> obtenerReporteRiesgo(String fondo, Date fecha);

}
