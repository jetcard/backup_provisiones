package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NamedStoredProcedureQuery(
        name = "ReporteRiesgo.procedureinfo",
        procedureName = "PKG_RIESGOS.SP_REPORTE_RIESGO",
        parameters = {
                @StoredProcedureParameter(name = "PD_fecha", mode = ParameterMode.IN, type = Date.class),
                @StoredProcedureParameter(name = "PO_CURSOR_RESULTADO", mode = ParameterMode.REF_CURSOR, type = Void.class )
        }
)
public class ReporteRiesgo {


    @Id
    @Column(name = "CODIGOTCHN")
    private String CODIGOTCHN;

    @Column(name = "CMONEDA")
    private String CMONEDA;

    @Column(name = "ndocumento")
    private String ndocumento;

    @Column(name = "tapaterno")
    private String tapaterno;

    @Column(name = "tamaterno")
    private String tamaterno;

    @Column(name = "tnombres")
    private String tnombres;

    @Column(name = "dnacimiento")
    private String dnacimiento;

    @Column(name = "tdireccion")
    private String tdireccion;

    @Column(name = "cubigeo")
    private String cubigeo;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "cinmueble")
    private String cinmueble;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "saldo_actual")
    private String saldo_actual;

    @Column(name = "cuotasatrasdas")
    private String cuotasatrasdas;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cuota")
    private String cuota;

    @Column(name = "SUELDO")
    private String sueldo;

    @Column(name = "fdesembolso")
    private String fdesembolso;

    @Column(name = "tipooperacion")
    private String tipooperacion;

    @Column(name = "S_INFOCORP")
    private String S_INFOCORP;

    @Column(name = "NVALORIZACION")
    private String NVALORIZACION;

    @Column(name = "V_EDIFICACION")
    private String V_EDIFICACION;

    @Column(name = "V_PROPIEDAD")
    private String V_PROPIEDAD;

    @Column(name = "V_COMERCIAL")
    private String V_COMERCIAL;

    @Column(name = "V_REALIZACIONSOL")
    private String V_REALIZACIONSOL;

    @Column(name = "V_REALIZACIONDOL")
    private String V_REALIZACIONDOL;

    @Column(name = "F_VALORIZACION")
    private String F_VALORIZACION;

    @Column(name = "ncuotas_generadas")
    private String ncuotas_generadas;

}
