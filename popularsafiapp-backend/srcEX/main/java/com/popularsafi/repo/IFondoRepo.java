package com.popularsafi.repo;
import com.popularsafi.model.Fondo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFondoRepo extends IGenericRepo<Fondo, String> {
    @Query(value = "select C_FONDO_ID,D_FONDO from EVA.MAE_FONDO WHERE E_ESTADO='01'",nativeQuery = true)
    List<Fondo> findAll();
}
