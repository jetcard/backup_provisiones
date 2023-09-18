package com.popularsafi.repo;

import com.popularsafi.model.CalculoIC;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ICalculoICRepo extends IGenericRepo<CalculoIC, Long> {
    @Query(value =
            "select DVALOR_BV, DECODE(CMONEDA, '0001', 'S/.', '0002', '$.') AS CMONEDA, " +
            " NCUOTA, " +
            "TO_CHAR(FDESEMBOLSO, 'DD/MM/YYYY') AS  FDESEMBOLSO," +
            " TO_CHAR(MONTO, 'FM9999999999990.00') AS MONTO, " +
            "TO_CHAR(FPAGO, 'DD/MM/YYYY') AS FPAGO," +
            " TO_CHAR(TEA, 'FM9990.00') AS TEA, TO_CHAR(DIASTRANS, 'FM9990.00') AS DIASTRANS, INTERES, INTERESPROV,"+
            " ROUND((INTERESPROV*0.18),2) as IGV, " +
            " ROUND(INTERESPROV+(INTERESPROV*0.18),2) AS TOTAL" +
            //" from SACIF.TMP_CALCULO_IC WHERE INTERESPROV != 0 ", nativeQuery = true)
    " from SACIF.TMP_CALCULO_IC WHERE INTERESPROV != 0 and ROWNUM <= 5 ", nativeQuery = true)
    List<CalculoIC> findAll();
}
