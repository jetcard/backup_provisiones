package com.popularsafi.dto;

/*import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;*/
import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReporteRiesgoDTO {
    @EqualsAndHashCode.Include
    private String CODIGOTCHN;
    //@NotNull
    //@NotEmpty
    private String CMONEDA;
    //@NotNull
    //@NotEmpty
    private String ndocumento;
    //@NotNull
    //@NotEmpty
    private String tapaterno;
    //@NotNull
    //@NotEmpty
    private String tamaterno;
    //@NotNull
    //@NotEmpty
    private String tnombres;
    //@NotNull
    //@NotEmpty
    private String dnacimiento;
    //@NotNull
    //@NotEmpty
    private String tdireccion;
    //@NotNull
    //@NotEmpty
    private String cubigeo;
    //@NotNull
    //@NotEmpty
    private String departamento;
    //@NotNull
    //@NotEmpty
    private String provincia;
    //@NotNull
    //@NotEmpty
    private String distrito;
    //@NotNull
    //@NotEmpty
    private String cinmueble;
    //@NotNull
    //@NotEmpty
    private String actividad;
    //@NotNull
    //@NotEmpty
    private String saldo_actual;
    //@NotNull
    //@NotEmpty
    private String cuotasatrasdas;
    //@NotNull
    //@NotEmpty
    private String estado;
    //@NotNull
    //@NotEmpty
    private String cuota;
    //@NotNull
    //@NotEmpty
    private String sueldo;
    //@NotNull
    //@NotEmpty
    private String fdesembolso;
    //@NotNull
    //@NotEmpty
    private String tipooperacion;
    //@NotNull
    //@NotEmpty
    private String S_INFOCORP;
    //@NotNull
    //@NotEmpty
    private String NVALORIZACION;
    //@NotNull
    //@NotEmpty
    private String V_EDIFICACION;
    //@NotNull
    //@NotEmpty
    private String V_PROPIEDAD;
    //@NotNull
    //@NotEmpty
    private String V_COMERCIAL;
    //@NotNull
    //@NotEmpty
    private String V_REALIZACIONSOL;
    //@NotNull
    //@NotEmpty
    private String V_REALIZACIONDOL;

    //@NotNull
    //@NotEmpty
    private String F_VALORIZACION;

    private String ubigeo;

    //@NotNull
    //@NotEmpty
    private String ncuotas_generadas;

}
