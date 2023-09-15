package com.popularsafi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FondoDTO {
    @NotNull
    private String C_FONDO_ID;
    @NotNull
    private String D_FONDO;
}
