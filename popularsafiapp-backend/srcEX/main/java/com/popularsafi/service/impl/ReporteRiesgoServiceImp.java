package com.popularsafi.service.impl;

import com.popularsafi.model.ReporteRiesgo;

import com.popularsafi.repo.IGenericRepo;
import com.popularsafi.repo.IRepoRiesgo;
import com.popularsafi.service.IReporteRiesgoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)

@RequiredArgsConstructor
public class ReporteRiesgoServiceImp extends CRUDImpl<ReporteRiesgo, Integer> implements IReporteRiesgoService {


    private final IRepoRiesgo repo;
    @Override
    public List<ReporteRiesgo> obtenerReporteRiesgo(String fondo, Date fecha) {
           return repo.obtenerReporteRiesgo(fondo,fecha);
    }


    @Transactional
    @Override
    protected IGenericRepo<ReporteRiesgo, Integer> getRepo() {
        return null;
    }
}
