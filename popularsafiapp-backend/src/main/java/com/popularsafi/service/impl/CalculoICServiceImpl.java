package com.popularsafi.service.impl;

import com.popularsafi.dto.CalculoDTO;
import com.popularsafi.model.CalculoIC;
import com.popularsafi.repo.ICalculoICRepo;
import com.popularsafi.repo.IGenericRepo;
import com.popularsafi.service.ICalculoICService;
import com.popularsafi.service.ProvisionResponseRest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculoICServiceImpl extends CRUDImpl<CalculoIC, Long> implements ICalculoICService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ICalculoICRepo repo;

    @Override
    protected IGenericRepo<CalculoIC, Long> getRepo() {
        return null;
    }

    public List<CalculoIC> findAll() {
        return repo.findAll();
    }

    private static final Logger logger= LoggerFactory.getLogger(CalculoICServiceImpl.class.getName());
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