package com.popularsafi.service.impl;

import com.popularsafi.model.ReporteProvision;
import com.popularsafi.repository.IProvisionRepo;
import com.popularsafi.repository.IGenericRepo;
import com.popularsafi.service.IProvisionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvisionServiceImpl extends CRUDImpl<ReporteProvision, Long> implements IProvisionService {
    @PersistenceContext
    private EntityManager entityManager;
    private final IProvisionRepo repo;

    @Override
    protected IGenericRepo<ReporteProvision, Long> getRepo() {
        return null;
    }

    public List<ReporteProvision> findAll() {
        return repo.findAll();
    }

    private static final Logger logger= LoggerFactory.getLogger(ProvisionServiceImpl.class.getName());
    public String ejecutarFecha(String fechaParam) {
        logger.info("ejecutarFecha "+fechaParam+" ");
        DateFormat dateFormat0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("TimeUnitMillis "+dateFormat0.format(System.currentTimeMillis()));
        try {
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SACIF.Dias_genera_IC2");
            storedProcedure.registerStoredProcedureParameter("cFProceso", Date.class, ParameterMode.IN);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaProceso = dateFormat.parse(fechaParam);
            logger.info("fechaProceso: " + fechaProceso);
            storedProcedure.setParameter("cFProceso", fechaProceso);
            storedProcedure.execute();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /*@PersistenceContext
    private EntityManager entityManager;
        try {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SACIF.Dias_genera_IC");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        storedProcedure.registerStoredProcedureParameter("cFProceso", Date.class, ParameterMode.IN);
        storedProcedure.setParameter("cFProceso", dateFormat.parse(fechaProceso));
        storedProcedure.execute();
    } catch (Exception e) {
        e.printStackTrace();
    }*/
}