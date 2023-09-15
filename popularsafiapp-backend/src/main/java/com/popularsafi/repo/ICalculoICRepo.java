package com.popularsafi.repo;

import com.popularsafi.model.CalculoIC;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ICalculoICRepo extends IGenericRepo<CalculoIC, Long> {
    @Query(value =
            "select DVALOR_BV," +
            " NCUOTA, " +
            "TO_CHAR(FDESEMBOLSO, 'DD/MM/YYYY') AS  FDESEMBOLSO," +
            " MONTO, " +
            "TO_CHAR(FPAGO, 'DD/MM/YYYY') AS FPAGO," +
            " TEA, DIASTRANS, INTERES, INTERESPROV,"+
            " ROUND((INTERESPROV*0.18),2) as IGV, " +
            " ROUND(INTERESPROV+(INTERESPROV*0.18),2) AS TOTAL" +
            " from SACIF.TMP_CALCULO_IC WHERE INTERESPROV != 0 ", nativeQuery = true)
    //" from SACIF.TMP_CALCULO_IC WHERE INTERESPROV != 0 and ROWNUM <= 10 ", nativeQuery = true)
    List<CalculoIC> findAll();
}
