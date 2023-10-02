package com.popularsafi.service.impl;

import com.popularsafi.model.ReporteRiesgo;

import com.popularsafi.repository.IGenericRepo;
import com.popularsafi.repository.IRepoRiesgo;
import com.popularsafi.service.IReporteRiesgoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
///@Transactional(readOnly = true)

@RequiredArgsConstructor
public class ReporteRiesgoServiceImp extends CRUDImpl<ReporteRiesgo, Long> implements IReporteRiesgoService {

    private final IRepoRiesgo repo;

    @Override
    protected IGenericRepo<ReporteRiesgo, Long> getRepo() {
        return null;
    }

    @Override
    public List<ReporteRiesgo> obtenerReportesPorFecha(String fecha) {
        return repo.findAllByFecha(fecha);
    }
    /*@Override
    public List<ReporteRiesgo> obtenerReporteRiesgo(String fondo, Date fecha) {
           return repo.obtenerReporteRiesgo(fondo,fecha);
    }*/

/*
    @Transactional
    @Override
    protected IGenericRepo<ReporteRiesgo, Integer> getRepo() {
        return null;
    }*/
}
