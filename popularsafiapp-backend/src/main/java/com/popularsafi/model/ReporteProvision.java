package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor

public class ReporteProvision {
    @Id
    @Column(name="DVALOR_BV")
    private String DVALOR_BV;
    @Column(name="CMONEDA")
    private String CMONEDA;
    @Column(name="NCUOTA")
    private Integer NCUOTA;
    @Column(name="FDESEMBOLSO")
    private String FDESEMBOLSO;
    @Column(name="MONTO")
    private String MONTO;
    @Column(name="FPAGO")
    private String FPAGO;
    @Column(name="TEA")
    private String TEA;
    @Column(name="DIASTRANS")
    private String DIASTRANS;
    @Column(name="INTERES")
    private String INTERES;
    @Column(name="INTERESPROV")
    private String INTERESPROV;
    @Column(name="IGV")
    private String IGV;
    @Column(name="TOTAL")
    private String TOTAL;

}

