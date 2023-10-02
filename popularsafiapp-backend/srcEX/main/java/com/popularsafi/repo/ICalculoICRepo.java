package com.popularsafi.repo;

import com.popularsafi.model.CalculoIC;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ICalculoICRepo extends IGenericRepo<CalculoIC, Long> {
    @Query(value =
            "SELECT DVALOR_BV, DECODE(CMONEDA, '0001', 'S/.', '0002', '$.') AS CMONEDA, " +
            " NCUOTA, " +
            " TO_CHAR(FDESEMBOLSO, 'DD/MM/YYYY') AS  FDESEMBOLSO, " +
            " TO_CHAR(MONTO, 'FM9999999999990.00') AS MONTO, " +
            " TO_CHAR(FPAGO, 'DD/MM/YYYY') AS FPAGO," +
            " TO_CHAR(TEA, 'FM9990.00') AS TEA," +
            " TO_CHAR(DIASTRANS, 'FM9990.00') AS DIASTRANS, " +
            " TO_CHAR(INTERES, 'FM9990.00') AS INTERES, "+
            " TO_CHAR(INTERESPROV, 'FM9990.00') AS INTERESPROV, "+
            " TO_CHAR(ROUND((INTERESPROV*0.18),2), 'FM9990.00') AS IGV, "+
            " TO_CHAR(ROUND(INTERESPROV+(INTERESPROV*0.18),2), 'FM9990.00') AS TOTAL "+
            " FROM SACIF.TMP_CALCULO_IC WHERE INTERESPROV != 0 ", nativeQuery = true)
    //" from SACIF.TMP_CALCULO_IC WHERE INTERESPROV != 0 and ROWNUM <= 5 ", nativeQuery = true)
    List<CalculoIC> findAll();
}
