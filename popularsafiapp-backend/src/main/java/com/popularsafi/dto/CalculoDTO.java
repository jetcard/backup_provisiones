package com.popularsafi.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculoDTO {
    private String DVALOR_BV;
    private Integer NCUOTA;
    private String FDESEMBOLSO;
    private String MONTO;
    private String FPAGO;
    private String TEA;
    private String DIASTRANS;
    private String INTERES;
    private String INTERESPROV;
    private String IGV;
    private String TOTAL;
}
