package com.popularsafi.service.impl;

import com.popularsafi.model.Fondo;
import com.popularsafi.repository.IFondoRepo;
import com.popularsafi.repository.IGenericRepo;
import com.popularsafi.service.IFondoServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FondoImpl extends CRUDImpl<Fondo, String> implements IFondoServ {

    private final IFondoRepo repo;

    @Override
    protected IGenericRepo<Fondo, String> getRepo() {
        return null;
    }

    public List<Fondo> findAll() {
         return repo.findAll();
    }
}