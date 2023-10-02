package com.popularsafi.repo;

import com.popularsafi.model.ReporteRiesgo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RiesgoImpl implements  IRepoRiesgo{
    @PersistenceContext
    EntityManager em = null;

    public List<ReporteRiesgo> obtenerReporteRiesgo(String fondo, Date fecha) {
        List<ReporteRiesgo> ListReporteRiesgo = null;

        try {
            String lsProc = "";
            switch (fondo) {
                case "1":
                    lsProc = "SACIF.PKG_RIESGOS.SP_REPORTE_RIESGO";
                    break;
                case "2":
                    lsProc = "SACIF_POP.PKG_RIESGOS.SP_REPORTE_RIESGO";
                    break;
                case "3":
                    lsProc = "SACIF_MYP.PKG_RIESGOS.SP_REPORTE_RIESGO";
                    break;
                case "4":
                    lsProc = "SACIF_PRH.PKG_RIESGOS.SP_REPORTE_RIESGO";
                    break;
                default:
                    lsProc = "SACIF.PKG_RIESGOS.SP_REPORTE_RIESGO";

                    // Default secuencia de sentencias.
            }

            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery(lsProc);
            storedProcedureQuery.registerStoredProcedureParameter("PD_fecha", Date.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("PO_CURSOR_RESULTADO", Void.class,
                    ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter("PD_fecha", fecha);

            List<Object[]> rows = storedProcedureQuery.getResultList();

            ListReporteRiesgo = new ArrayList<>(rows.size());

            for (Object[] row : rows) {
                ListReporteRiesgo.add(new ReporteRiesgo((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4], (String) row[5],
                        (String) row[6], (String) row[7], (String) row[8],
                        (String) row[9], (String) row[10], (String) row[11], (String) row[12], (String) row[13],
                        (String) row[14], (String) row[15], (String) row[16], (String) row[17], (String) row[18], (String) row[19],
                        (String) row[20], (String) row[21], (String) row[22],
                        (String) row[23], (String) row[24], (String) row[25], (String) row[26], (String) row[27],
                        (String) row[28], (String) row[29]));
            }
            System.out.println("se ejecuto bien" + ListReporteRiesgo.size());
            return ListReporteRiesgo;
        } catch (Exception ex) {


            return ListReporteRiesgo;
        }
    }
}
