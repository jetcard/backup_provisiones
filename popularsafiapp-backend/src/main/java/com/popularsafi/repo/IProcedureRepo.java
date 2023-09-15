package com.popularsafi.repo;

import com.popularsafi.model.CalculoIC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/* Cuando el SP no maneja parámetros
public interface IProcedureRepo extends JpaRepository<CalculoIC, Long> {
    @Procedure(procedureName = "SACIF.Dias_genera_IC")
    String callProcedure(@Param("cFProceso") String fechaProceso);
}
*/