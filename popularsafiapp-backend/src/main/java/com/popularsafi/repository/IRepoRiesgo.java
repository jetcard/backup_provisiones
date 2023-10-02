package com.popularsafi.repository;

import com.popularsafi.model.ReporteRiesgo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepoRiesgo extends IGenericRepo<ReporteRiesgo, Long>  {
    ///List<ReporteRiesgo> obtenerReporteRiesgo(String fondo, Date fecha);

    @Query(value =
        "SELECT RTRIM(ALT.DVALOR_BV) as CODIGOTCHN " +
        ",ALT.CMONEDA AS CMONEDA  " + //CMONEDA " +
        ",doc.ndocumento  " + //Ndocumento " +
        ",per.tapaterno  " + //Tapaterno " +
        ",per.tamaterno  " + //Tamaterno " +
        ",per.tnombres  " + //Tnombres " +
        ",TO_CHAR(per.dnacimiento) as dnacimiento  " + //Dnacimiento " +
        ",inm.tdireccion  " + //Tdireccion " +
        ",inm.cubigeo  " + //Cubigeo " +
        ",TRIM(u.departamento) AS departamento  " + //Departamento " +
        ",TRIM(u.provincia) AS  provincia " + //Provincia " +
        ",TRIM(u.distrito) AS distrito " + //Distrito " +
        ",to_char(inm.cinmueble) cinmueble  " + //Cinmueble " +
        ",RTRIM(c.dciiu) as actividad  " + //Actividad " +
        ",to_char(tmp.saldo_actual) saldo_actual  " + //Saldo_actual " +
        ",to_char(SACIF.SF_NUMCUOATR_INVFEC(mi.ninversion,to_date(?1, 'DD/MM/YYYY'))) cuotasatrasdas  " + //Cuotasatrasdas " +
        ",to_char(SACIF.sf_estado_inversion(mi.NINVERSION,to_date(?1, 'DD/MM/YYYY'))) estado  " + //Estado " +
        ",to_char(SACIF.SF_MONTO_CUOTA_PAGO(mi.NINVERSION)) cuota  " + //Cuota " +
        ",to_char(Per.PROMINGRESO) SUELDO  " + //Sueldo " +
        ",to_char(alt.fcolocan) fdesembolso  " + //Fdesembolso " +
        ",TRIM(to_char(TG.DDESCRIPCION)) AS tipooperacion  " + //Tipooperacion " +
        ",PEC.S_INFOCORP as S_INFOCORP " + //S_INFOCORP " +
        ",to_char(V.NVALORIZACION) as NVALORIZACION  " + //NVALORIZACION " +
        ",to_char(V.MAPRECIACION_COMERCIAL) AS V_EDIFICACION  " + //V_EDIFICACION " +
        ",to_char(V.MVALOR_TERRENO) AS V_PROPIEDAD  " + //V_PROPIEDAD " +
        ",to_char(V.MVALOR_COMERCIAL) AS V_COMERCIAL  " + //V_COMERCIAL " +
        ",to_char(V.MVALOR_REALIZACION) AS V_REALIZACIONSOL  " + //V_REALIZACIONSOL " +
        ",to_char(V.MVALORDOL_REALIZA) AS V_REALIZACIONDOL  " + //V_REALIZACIONDOL " +
        ",to_char(V.DVALORIZACION) AS F_VALORIZACION  " + //F_VALORIZACION " +
        //",to_char(U.DUBIGEO ) distrito " +
                ", RTRIM(to_char(U.DUBIGEO )) ubigeo " +
        ",to_char(mi.ncuotas_generadas) as ncuotas_generadas  " + //Ncuotas_generadas " +
        ",to_char(SACIF.SF_NUMCUOATR_INVFEC(mi.ninversion,to_date(?1, 'DD/MM/YYYY')) ) cuotasatrasadas " +
        " " +
        "FROM SACIF.alternativa_inversion ALT " +
        "LEFT JOIN SACIF.MAESTRO_INVERSION MI " +
        "ON MI.DVALOR_BV = ALT.DVALOR_BV " +
        "LEFT JOIN SACIF.tmp_saldo_deudor sd " +
        "ON sd.DVALOR_BV = ALT.DVALOR_BV AND sd.fecha = to_date(?1, 'DD/MM/YYYY') " +
        "LEFT JOIN SACIF.VALORIZACION V " +
        "ON V.CINMUEBLE = ALT.CINMUEBLE AND dvalorizacion = ( " +
        "SELECT MAX(dvalorizacion) " +
        "FROM SACIF.VALORIZACION " +
        "WHERE cinmueble = v.cinmueble " +
        "AND dvalorizacion < to_date(?1, 'DD/MM/YYYY')) " +
        "LEFT JOIN SACIF.INMUEBLE I " +
        "ON I.CINMUEBLE = ALT.CINMUEBLE " +
        "LEFT JOIN SACIF.UBIGEO U " +
        "ON U.CUBIGEO = I.CUBIGEO " +
        "INNER JOIN SACIF.persona per " +
        "ON per.cpersona = alt.cpersona " +
        "INNER JOIN SACIF.documento doc " +
        "ON doc.cpersona = per.cpersona " +
        "INNER JOIN SACIF.inmueble inm " +
        "ON inm.cinmueble = alt.cinmueble " +
        "LEFT JOIN SACIF.TMP_SALDO_DEUDOR tmp " +
        "ON tmp.dvalor_bv = mi.dvalor_bv AND tmp.fecha = to_date(?1, 'DD/MM/YYYY') " +
        "LEFT JOIN spla.ubigeo_homologacion u " +
        "ON inm.cubigeo = u.ubigeo_saciv " +
        "LEFT JOIN SACIF.PERSONA_ILABORAL pi " +
        "ON pi.cpersona = per.cpersona " +
        "LEFT JOIN SACIF.ciiu c " +
        "ON pi.s_cod_act_econo = c.cciiu " +
        "LEFT JOIN SACIF.PERSONA_ECFINAN PEC " +
        "ON PEC.CPERSONA = Per.CPERSONA " +
        "LEFT JOIN SACIF.documento doc " +
        "ON per.cpersona = doc.cpersona " +
        "LEFT JOIN SACIF.tablas_generales TG " +
        "ON TG.CTABLA = '0608' AND alt.TIP_OPERACION = TG.CARGUM " +
        "WHERE ALt.dvalor_bv like '%TCHN%' " +
        "AND (NVL(FCANCELADO, 'N') = 'N' OR (NVL(FCANCELADO, 'N') = 'S' AND DCANCELADO >= to_date(?1, 'DD/MM/YYYY'))" +
                " and ROWNUM <= 5)" +
        "", nativeQuery = true)
    List<ReporteRiesgo> findAllByFecha(String fecha);//31/07/2023
}
