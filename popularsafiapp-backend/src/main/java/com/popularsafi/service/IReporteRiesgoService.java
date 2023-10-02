package com.popularsafi.service;

import com.popularsafi.model.ReporteRiesgo;

import java.util.Date;
import java.util.List;

public interface IReporteRiesgoService extends ICRUD<ReporteRiesgo,Long> {

    //List<ReporteRiesgo> obtenerReporteRiesgo(String fondo, Date fecha);

    List<ReporteRiesgo> obtenerReportesPorFecha(String fecha);

}
