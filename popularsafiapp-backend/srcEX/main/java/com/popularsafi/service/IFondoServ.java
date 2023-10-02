package com.popularsafi.service;


import com.popularsafi.model.Fondo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IFondoServ extends ICRUD<Fondo,String>  {
    List<Fondo> findAll();
}
